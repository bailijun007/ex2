package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.bizother.entity.account.AssetPageListBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetBizImpl implements IAssetBiz {
    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private IExpDicDao dicDao;


    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public long createAsset(AssetBizBo assetBizBo) throws BizException {
        AssetPo po= ConventObjectUtil.conventObject(assetBizBo,AssetPo.class);
        po.setCtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("AssetRedisKey");
        if(iAssetDao.insert(po)>0)
        {
            assetBizBo.setSymbol(assetBizBo.getRealName());
            assetBizBo.setChainSymbolId(assetBizBo.getChainAppointId());
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),assetBizBo.getRealName(), JsonUtil.toJsonString(assetBizBo));
            }
        }

        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return po.getId();
    }

    @Override
    public long editAsset(AssetBizBo assetBizBo) throws BizException {
        AssetPo beforePo= iAssetDao.selectPoById(assetBizBo.getId());

        AssetPo po= ConventObjectUtil.conventObject(assetBizBo,AssetPo.class);
        po.setMtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("AssetRedisKey");
        if(iAssetDao.updateById(po)>0)
        {
            if(beforePo!=null)
                redisUtilDb0.hdel(dicPo.getValue(),beforePo.getRealName());
            assetBizBo.setSymbol(assetBizBo.getRealName());
            assetBizBo.setChainSymbolId(assetBizBo.getChainAppointId());
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),assetBizBo.getRealName(), JsonUtil.toJsonString(assetBizBo));
            }
        }
        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return 1;
    }

    @Override
    public AssetPageListBizBo queryAssetList(String realName, long currentPage, long pageSize)  {
        IPage<AssetPo> assetPoIPage= iAssetDao.selectPosList(realName,currentPage,pageSize);
        AssetPageListBizBo assetPageListBizBo=new AssetPageListBizBo();
        assetPageListBizBo.setTotal(assetPoIPage.getTotal());
        List<AssetBizBo> bizBoList=new ArrayList<>();
        for(AssetPo po:assetPoIPage.getRecords())
        {
            AssetBizBo bo=ConventObjectUtil.conventObject(po,AssetBizBo.class);
            bizBoList.add(bo);
        }
        assetPageListBizBo.setRows(bizBoList);
        return assetPageListBizBo;
    }

    @Override
    public AssetListBizBo queryRealNameList() {
        List<AssetPo> poList= iAssetDao.selectList();
        List<String> assetList=new ArrayList<>();
        for(AssetPo po:poList)
        {
            assetList.add(po.getRealName());
        }
        AssetListBizBo listBizBo=new AssetListBizBo();
        listBizBo.setAssetList(assetList);
        return listBizBo;
    }


    @Override
    public AssetBizBo queryAssetById(long id) {
        AssetPo po= iAssetDao.selectPoById(id);
        AssetBizBo bo=ConventObjectUtil.conventObject(po,AssetBizBo.class);
        return bo;
    }

    @Override
    public boolean checkHasAsset(String realName) {
        return iAssetDao.checkHasCoin(realName);
    }
}
