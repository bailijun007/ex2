package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.entity.account.BbFeeBizBo;
import com.hupa.exp.bizother.entity.account.BbFeeListBizBo;
import com.hupa.exp.bizother.service.account.def.IBbFeeBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IBbFeeDao;
import com.hupa.exp.daomysql.entity.po.expv2.BbFeePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/6.
 */

@Service
public class BbFeeBizImpl implements IBbFeeBiz {

    @Autowired
    private IBbFeeDao iBbFeeDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public BbFeeBizBo getBbFeeById(long id) {
        BbFeePo po=iBbFeeDao.selectPoById(id);
        BbFeeBizBo bo= ConventObjectUtil.conventObject(po,BbFeeBizBo.class);
        return bo;
    }

    @Override
    public BbFeeListBizBo getBbFeePageData(long currentPage, long pageSize) {
        IPage<BbFeePo> pageData=iBbFeeDao.selectBbFeePageData(currentPage,pageSize);
        BbFeeListBizBo pageBizbo=new BbFeeListBizBo();
        pageBizbo.setTotal(pageData.getTotal());
        List<BbFeeBizBo> BbFeeBizBos=new ArrayList<>();
        for(BbFeePo po:pageData.getRecords())
        {
            BbFeeBizBo bo=ConventObjectUtil.conventObject(po,BbFeeBizBo.class);
            BbFeeBizBos.add(bo);
        }
        pageBizbo.setRows(BbFeeBizBos);
        return pageBizbo;
    }

    @Override
    public long createBbFee(BbFeeBizBo bo) {
        BbFeePo po=ConventObjectUtil.conventObject(bo,BbFeePo.class);
        long id=iBbFeeDao.insert(po);
        if(id>0)
            setBbFeeToRedis();
        return  id;
    }

    @Override
    public long editBbFee(BbFeeBizBo bo) {
        BbFeePo po=ConventObjectUtil.conventObject(bo,BbFeePo.class);
        int count=iBbFeeDao.updateById(po);
        if(count>0)
            setBbFeeToRedis();
        return  count;
    }

    @Override
    public List<BbFeeBizBo> getAllBbFee() {
        List<BbFeePo> pageData=iBbFeeDao.selectAllBbFeeData();
        List<BbFeeBizBo> bbFeeBizBos=new ArrayList<>();
        for(BbFeePo po:pageData)
        {
            BbFeeBizBo bo=ConventObjectUtil.conventObject(po,BbFeeBizBo.class);
            bbFeeBizBos.add(bo);
        }
        return bbFeeBizBos;

    }

    private void setBbFeeToRedis()
    {
        List<BbFeePo> bbFeePos=iBbFeeDao.selectAllBbFeeData();
        redisUtilDb0.hset("bb_fee","bb", JsonUtil.toJsonString(bbFeePos));
    }
}
