package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.entity.account.PcFeeBizBo;
import com.hupa.exp.bizother.entity.account.PcFeeListBizBo;
import com.hupa.exp.bizother.service.account.def.IPcFeeBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IPcFeeDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcFeePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PcFeeBizImpl implements IPcFeeBiz {
    @Autowired
    private IPcFeeDao iPcFeeDao;
    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public PcFeeBizBo getPcFeeById(long id) {
        PcFeePo po=iPcFeeDao.selectPoById(id);
        PcFeeBizBo bo= ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
        return bo;
    }

    @Override
    public PcFeeListBizBo getPcFeePageData(long currentPage, long pageSize) {
        IPage<PcFeePo> pageData=iPcFeeDao.selectPcFeePageData(currentPage,pageSize);
        PcFeeListBizBo pageBizbo=new PcFeeListBizBo();
        pageBizbo.setTotal(pageData.getTotal());
        List<PcFeeBizBo> pcFeeBizBos=new ArrayList<>();
        for(PcFeePo po:pageData.getRecords())
        {
            PcFeeBizBo bo=ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
            pcFeeBizBos.add(bo);
        }
        pageBizbo.setRows(pcFeeBizBos);
        return pageBizbo;
    }

    @Override
    public long createPcFee(PcFeeBizBo bo) {
        PcFeePo po=ConventObjectUtil.conventObject(bo,PcFeePo.class);
        long id=iPcFeeDao.insert(po);
        if(id>0)
            setPcFeeToRedis();
        return  id;
    }

    @Override
    public long editPcFee(PcFeeBizBo bo) {
        PcFeePo po=ConventObjectUtil.conventObject(bo,PcFeePo.class);
        int count=iPcFeeDao.updateById(po);
        if(count>0)
            setPcFeeToRedis();
        return  count;
    }

    @Override
    public List<PcFeeBizBo> getAllPcFee() {
        List<PcFeePo> pageData=iPcFeeDao.selectAllPcFeeData();
        List<PcFeeBizBo> pcFeeBizBos=new ArrayList<>();
        for(PcFeePo po:pageData)
        {
            PcFeeBizBo bo=ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
            pcFeeBizBos.add(bo);
        }
        return pcFeeBizBos;

    }

    private void setPcFeeToRedis()
    {
        List<PcFeePo> pcFeePos=iPcFeeDao.selectAllPcFeeData();
        redisUtilDb0.hset("pc_fee","pc", JsonUtil.toJsonString(pcFeePos));
    }
}
