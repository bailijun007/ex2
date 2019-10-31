package com.hupa.exp.proc;

import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.impl.ExpDicDaoImpl;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ThreadPoolTest {

    @Autowired
    private IExpDicDao iExpDicDao;

    @PostConstruct
    private void stat(){

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<ExpDicPo> parentDicList=iExpDicDao.selectParentDic();
        parentDicList.forEach(expDicPo -> {
            //往线程池里塞任务
            pool.execute(new DoSomethingBo((int) expDicPo.getId()));
        });

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            final int index = i;
            System.out.println("task: " + (index+1));
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread start" + (index+1));
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread end" + (index+1));
                }
            };
            executor.execute(run);
        }
    }
    //构造一个内部内  为了能调用注入的对象
    public class DoSomethingBo implements Runnable {
        Integer parentId=0;
        public DoSomethingBo(Integer id) {
            parentId=id;
        }

        @Override
        public void run() {
            List<ExpDicPo> list= iExpDicDao.selectDicListByParentId(parentId);
            list.forEach(assetPo ->
            {
                System.out.println("线程："+Thread.currentThread().getName()+ " 父级："+parentId+ "  子集："+assetPo.getKey());
            });
        }

    }
}
