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
import java.util.Comparator;
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

        if(iPcPosLevelDao.insert(po)>0)
        {
            List<PcPosLevelPo> pos= iPcPosLevelDao.selectAllPosLevelList(bo.getAsset(),bo.getSymbol());
            ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
            List<PcPosLevelBizBo> boList=new ArrayList<>();
            pos.forEach(pcPosLevelPo->{
                PcPosLevelBizBo posLevelBizBo=new PcPosLevelBizBo();
                posLevelBizBo.setId(pcPosLevelPo.getId());
                posLevelBizBo.setAsset(pcPosLevelPo.getAsset());
                posLevelBizBo.setSymbol(pcPosLevelPo.getSymbol()) ;
                posLevelBizBo.setGear(pcPosLevelPo.getGear());
                posLevelBizBo.setMinAmt(pcPosLevelPo.getMinAmt()) ;
                posLevelBizBo.setMaxAmt(pcPosLevelPo.getMaxAmt());
                posLevelBizBo.setMaxLeverage(pcPosLevelPo.getMaxLeverage());
                posLevelBizBo.setPosHoldMarginRatio(pcPosLevelPo.getPosHoldMarginRatio());//pos_hold_margin_ratio
                posLevelBizBo.setMinHoldMarginRatio(po.getMinHoldMarginRatio());
                posLevelBizBo.setCtime(pcPosLevelPo.getCtime());
                posLevelBizBo.setMtime(pcPosLevelPo.getMtime());
                boList.add(posLevelBizBo);
            });
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getAsset()+"__"+bo.getSymbol(), JsonUtil.toJsonString(boList));
            }
        }
        return po.getId();
    }

    @Override
    public long editPosLevel(PcPosLevelBizBo bo) throws BizException {
        PcPosLevelPo po= ConventObjectUtil.conventObject(bo,PcPosLevelPo.class);
        if(iPcPosLevelDao.updateById(po)>0)
        {
            ExpDicPo dicPo= dicDao.selectDicByKey("PosLevel");
            List<PcPosLevelPo> pos= iPcPosLevelDao.selectAllPosLevelList(bo.getAsset(),bo.getSymbol());//Long.MAX_VALUE
            List<PcPosLevelBizBo> boList=new ArrayList<>();
            pos.forEach(pcPosLevelPo->{
                PcPosLevelBizBo posLevelBizBo=new PcPosLevelBizBo();
                posLevelBizBo.setId(pcPosLevelPo.getId());
                posLevelBizBo.setAsset(pcPosLevelPo.getAsset());
                posLevelBizBo.setSymbol(pcPosLevelPo.getSymbol()) ;
                posLevelBizBo.setGear(pcPosLevelPo.getGear());
                posLevelBizBo.setMinAmt(pcPosLevelPo.getMinAmt()) ;
                posLevelBizBo.setMaxAmt(pcPosLevelPo.getMaxAmt());
                posLevelBizBo.setMaxLeverage(pcPosLevelPo.getMaxLeverage());
                posLevelBizBo.setPosHoldMarginRatio(pcPosLevelPo.getPosHoldMarginRatio());
                posLevelBizBo.setMinHoldMarginRatio(pcPosLevelPo.getMinHoldMarginRatio());
                posLevelBizBo.setCtime(pcPosLevelPo.getCtime());
                posLevelBizBo.setMtime(pcPosLevelPo.getMtime());
                boList.add(posLevelBizBo);
            });
            //每一个货币对，集合中最大张数，它的最大值必须是Long.MAX_VALUE
            boList.sort(Comparator.comparing(PcPosLevelBizBo::getMaxAmt).reversed());
            PcPosLevelBizBo posLevelBizBo=boList.get(0);
            posLevelBizBo.setMaxAmt(Long.MAX_VALUE);
            boList.remove(boList.get(0));
            boList.add(posLevelBizBo);
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),bo.getAsset()+"__"+bo.getSymbol(), JsonUtil.toJsonString(boList));
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
        storingLevelPoIPage.getRecords().forEach(pcPosLevelPo->{
            PcPosLevelBizBo posLevelBizBo=new PcPosLevelBizBo();
            posLevelBizBo.setId(pcPosLevelPo.getId());
            posLevelBizBo.setAsset(pcPosLevelPo.getAsset());
            posLevelBizBo.setSymbol(pcPosLevelPo.getSymbol()) ;
            posLevelBizBo.setGear(pcPosLevelPo.getGear());
            posLevelBizBo.setMinAmt(pcPosLevelPo.getMinAmt()) ;
            posLevelBizBo.setMaxAmt(pcPosLevelPo.getMaxAmt());
            posLevelBizBo.setMaxLeverage(pcPosLevelPo.getMaxLeverage());
            posLevelBizBo.setPosHoldMarginRatio(pcPosLevelPo.getPosHoldMarginRatio());
            posLevelBizBo.setMinHoldMarginRatio(pcPosLevelPo.getMinHoldMarginRatio());
            posLevelBizBo.setCtime(pcPosLevelPo.getCtime());
            posLevelBizBo.setMtime(pcPosLevelPo.getMtime());
            bizBoList.add(posLevelBizBo);
        });
        pageListBizBo.setRows(bizBoList);
        return pageListBizBo;
    }

    @Override
    public PcPosLevelBizBo queryPosLevelById(Serializable id) throws BizException {
        PcPosLevelPo po= iPcPosLevelDao.selectPoById(id);
        if(po!=null)
        {
            PcPosLevelBizBo posLevelBizBo=new PcPosLevelBizBo();
            posLevelBizBo.setId(po.getId());
            posLevelBizBo.setAsset(po.getAsset());
            posLevelBizBo.setSymbol(po.getSymbol()) ;
            posLevelBizBo.setGear(po.getGear());
            posLevelBizBo.setMinAmt(po.getMinAmt()) ;
            posLevelBizBo.setMaxAmt(po.getMaxAmt());
            posLevelBizBo.setMaxLeverage(po.getMaxLeverage());
            posLevelBizBo.setPosHoldMarginRatio(po.getPosHoldMarginRatio());

            posLevelBizBo.setMinHoldMarginRatio(po.getMinHoldMarginRatio());
            posLevelBizBo.setCtime(po.getCtime());
            posLevelBizBo.setMtime(po.getMtime());
            return posLevelBizBo;
        }
        return null;
    }

    @Override
    public boolean checkHasStoringLevel(String asset,String symbol,String gear) {

        return false;
    }
}
