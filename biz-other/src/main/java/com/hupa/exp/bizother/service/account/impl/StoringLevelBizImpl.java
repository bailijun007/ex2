package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.entity.account.PcPosLevelBizBo;
import com.hupa.exp.bizother.entity.account.PosLevelPageListBizBo;
import com.hupa.exp.bizother.service.account.def.IPosLevelBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcPosLevelDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcPosLevelPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoringLevelBizImpl implements IPosLevelBiz {
    @Autowired
    private IPcPosLevelDao iPcPosLevelDao;

    @Autowired
    private IExpDicDao dicDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public long createPosLevel(PcPosLevelBizBo bo) throws BizException {
        PcPosLevelPo po= ConventObjectUtil.conventObject(bo,PcPosLevelPo.class);
        List<PcPosLevelPo> pos= iPcPosLevelDao.selectAllPosLevelList(bo.getAsset(),bo.getSymbol());
        ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
        if(iPcPosLevelDao.insert(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getAsset()+"__"+bo.getSymbol(), JsonUtil.toJsonString(pos));
            }
        }
        return po.getId();
    }

    @Override
    public long editPosLevel(PcPosLevelBizBo bo) throws BizException {
        PcPosLevelPo po= ConventObjectUtil.conventObject(bo,PcPosLevelPo.class);
        List<PcPosLevelPo> pos= iPcPosLevelDao.selectAllPosLevelList(bo.getAsset(),bo.getSymbol());
        ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
        if(iPcPosLevelDao.updateById(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getAsset()+"__"+bo.getSymbol(), JsonUtil.toJsonString(pos));
            }
        }
        return po.getId();
    }

    @Override
    public PosLevelPageListBizBo queryPosLevelList(String asset,String symbol, long currentPage, long pageSize) throws BizException {
        IPage<PcPosLevelPo> storingLevelPoIPage= iPcPosLevelDao.selectPosLevelListByPage(asset,symbol,currentPage,pageSize);
        PosLevelPageListBizBo pageListBizBo=new PosLevelPageListBizBo();
        pageListBizBo.setTotal(storingLevelPoIPage.getTotal());
        List<PcPosLevelBizBo> bizBoList=new ArrayList<>();
        for(PcPosLevelPo po:storingLevelPoIPage.getRecords())
        {
            PcPosLevelBizBo bo=ConventObjectUtil.conventObject(po,PcPosLevelBizBo.class);
            bizBoList.add(bo);
        }
        pageListBizBo.setRows(bizBoList);
        return pageListBizBo;
    }

    @Override
    public PcPosLevelBizBo queryPosLevelById(Serializable id) throws BizException {
        PcPosLevelPo po= iPcPosLevelDao.selectPoById(id);
        if(po!=null)
        {
            PcPosLevelBizBo bo=ConventObjectUtil.conventObject(po,PcPosLevelBizBo.class);
            return bo;
        }
        return null;
    }

    @Override
    public boolean checkHasStoringLevel(String asset,String symbol,String gear) {

        return false;
    }
}
