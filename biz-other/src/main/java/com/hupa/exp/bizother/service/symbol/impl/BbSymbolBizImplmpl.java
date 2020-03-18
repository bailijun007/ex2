package com.hupa.exp.bizother.service.symbol.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.symbol.BbSymbolBizBo;
import com.hupa.exp.bizother.entity.symbol.BbSymbolListBizBo;
import com.hupa.exp.bizother.service.symbol.def.IBbSymbolBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IBbSymbolDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.BbSymbolPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/9.
 */
@Service
public class BbSymbolBizImplmpl implements IBbSymbolBiz {

    @Autowired
    private IExpDicDao dicDao;

    @Autowired
    private IBbSymbolDao iBbSymbolDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public BbSymbolBizBo get(String asset, String symbol) {
        BbSymbolPo po = iBbSymbolDao.selectOnePo(asset,symbol);
        if (po == null)
            return null;
        BbSymbolBizBo bizBo = ConventObjectUtil.conventObject(po, BbSymbolBizBo.class);
        return bizBo;
    }

    @Override
    public long createBbSymbol(BbSymbolBizBo bo) throws ValidateException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        BbSymbolPo po= ConventObjectUtil.conventObject(bo,BbSymbolPo.class);
        long id= iBbSymbolDao.insert(po);
        ExpDicPo dicPo= dicDao.selectDicByKey("BbSymbolRedisKey");
        if(dicPo!=null)
        {
            redisUtilDb0.hset(dicPo.getValue(),po.getAsset()+"__"+po.getSymbol(), JsonUtil.toJsonString(po));
        }
        ExpDicPo dicPoDisplay= dicDao.selectDicByKey("BbSymbolDisplayNameRedisKey");
        if(dicPoDisplay!=null)
        {

            redisUtilDb0.hset(dicPoDisplay.getValue(),po.getAsset()+"__"+po.getDisplayName(), JsonUtil.toJsonString(po));
        }
        return id;
    }

    @Override
    public long editBbSymbol(BbSymbolBizBo bo) throws ValidateException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        BbSymbolPo po= ConventObjectUtil.conventObject(bo,BbSymbolPo.class);
        BbSymbolPo oldPo = iBbSymbolDao.selectPoById(bo.getId());
        po.setCtime(oldPo.getCtime());
        long id= iBbSymbolDao.updateById(po);
        //List<BbSymbolPo> bbSymbolPos= iBbSymbolDao.selectPos();
        ExpDicPo dicPo= dicDao.selectDicByKey("BbSymbolRedisKey");
        if(dicPo!=null)
        {
            redisUtilDb0.hdel(dicPo.getValue(),oldPo.getAsset()+"__"+oldPo.getSymbol());
            redisUtilDb0.hset(dicPo.getValue(),po.getAsset()+"__"+po.getSymbol(), JsonUtil.toJsonString(po));
        }
        ExpDicPo dicPoDisplay= dicDao.selectDicByKey("BbSymbolDisplayNameRedisKey");
        if(dicPoDisplay!=null)
        {
            redisUtilDb0.hdel(dicPoDisplay.getValue(),oldPo.getAsset()+"__"+oldPo.getDisplayName());
            redisUtilDb0.hset(dicPoDisplay.getValue(),po.getAsset()+"__"+po.getDisplayName(), JsonUtil.toJsonString(po));
        }
        return id;
    }

    @Override
    public BbSymbolBizBo getBbSymbolById(long id) {
        BbSymbolPo po = iBbSymbolDao.selectPoById(id);
        if (po == null)
            return null;
        BbSymbolBizBo bo = ConventObjectUtil.conventObject(po, BbSymbolBizBo.class);
        return bo;
    }

    @Override
    public BbSymbolListBizBo selectPosPageByParam(String asset, String symbol, long currentPage, long pageSize) {
        IPage<BbSymbolPo> poList = iBbSymbolDao.selectPosPageByParam(asset,symbol, currentPage, pageSize);
        BbSymbolListBizBo listBizBo = new BbSymbolListBizBo();
        List<BbSymbolBizBo> boList = new ArrayList<>();
        for (BbSymbolPo po : poList.getRecords()) {
            BbSymbolBizBo bo = ConventObjectUtil.conventObject(po, BbSymbolBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(poList.getTotal());
        return listBizBo;
    }

    @Override
    public List<String> selectAllSymbol() {
        List<BbSymbolPo> listPo= iBbSymbolDao.selectPosByStatus(1);
        List<String> symbolList=new ArrayList<>();
        for(BbSymbolPo po:listPo) {
            symbolList.add(po.getSymbol());
        }
        return symbolList;
    }

    @Override
    public BbSymbolBizBo checkHasBbSymbol(String asset,String symbol,String displayName) {
        BbSymbolPo po = iBbSymbolDao.checkHasSymbol(asset,symbol,displayName);
        if (po == null)
            return null;
        BbSymbolBizBo bo = ConventObjectUtil.conventObject(po, BbSymbolBizBo.class);
        return bo;
    }



}
