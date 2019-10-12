package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.entity.account.PcStoringLevelBizBo;
import com.hupa.exp.bizother.entity.account.StoringLevelPageListBizBo;
import com.hupa.exp.bizother.service.account.def.IStoringLevelBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcStoringLevelDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcStoringLevelPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoringLevelBizImpl implements IStoringLevelBiz {
    @Autowired
    private IPcStoringLevelDao iPcStoringLevelDao;

    @Autowired
    private IExpDicDao dicDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public long createStoringLevel(PcStoringLevelBizBo bo) throws BizException {
        PcStoringLevelPo po= ConventObjectUtil.conventObject(bo,PcStoringLevelPo.class);
        List<PcStoringLevelPo> pos=iPcStoringLevelDao.selectAllStoringLevelList(bo.getPair());
        ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
        if(iPcStoringLevelDao.insert(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getPair(), JsonUtil.toJsonString(pos));
            }
        }
        return po.getId();
    }

    @Override
    public long editStoringLevel(PcStoringLevelBizBo bo) throws BizException {
        PcStoringLevelPo po= ConventObjectUtil.conventObject(bo,PcStoringLevelPo.class);
        List<PcStoringLevelPo> pos=iPcStoringLevelDao.selectAllStoringLevelList(bo.getPair());
        ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
        if(iPcStoringLevelDao.updateById(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getPair(), JsonUtil.toJsonString(pos));
            }
        }
        return po.getId();
    }

    @Override
    public StoringLevelPageListBizBo queryStoringLevelList(String pair, long currentPage, long pageSize) throws BizException {
        IPage<PcStoringLevelPo> storingLevelPoIPage=iPcStoringLevelDao.selectStoringLevelListByPage(pair,currentPage,pageSize);
        StoringLevelPageListBizBo pageListBizBo=new StoringLevelPageListBizBo();
        pageListBizBo.setTotal(storingLevelPoIPage.getTotal());
        List<PcStoringLevelBizBo> bizBoList=new ArrayList<>();
        for(PcStoringLevelPo po:storingLevelPoIPage.getRecords())
        {
            PcStoringLevelBizBo bo=ConventObjectUtil.conventObject(po,PcStoringLevelBizBo.class);
            bizBoList.add(bo);
        }
        pageListBizBo.setRows(bizBoList);
        return pageListBizBo;
    }

    @Override
    public PcStoringLevelBizBo queryStoringLevelById(Serializable id) throws BizException {
        PcStoringLevelPo po=iPcStoringLevelDao.selectPoById(id);
        if(po!=null)
        {
            PcStoringLevelBizBo bo=ConventObjectUtil.conventObject(po,PcStoringLevelBizBo.class);
            return bo;
        }
        return null;
    }

    @Override
    public boolean checkHasStoringLevel(String pair, String gear) {

        return false;
    }
}
