package com.hupa.exp.bizother.service.account.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
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
    public long createCoin(AssetBizBo coinBizBo) throws BizException {
        AssetPo po= ConventObjectUtil.conventObject(coinBizBo,AssetPo.class);
        po.setCtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("AssetRedisKey");
        if(iAssetDao.insert(po)>0)
        {
            coinBizBo.setSymbol(coinBizBo.getRealName());
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getRealName(), JsonUtil.toJsonString(coinBizBo));
            }
        }

        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return po.getId();
    }

    @Override
    public long editCoin(AssetBizBo coinBizBo) throws BizException {
        AssetPo po= ConventObjectUtil.conventObject(coinBizBo,AssetPo.class);
        po.setMtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("AssetRedisKey");
        if(iAssetDao.updateById(po)>0)
        {
            coinBizBo.setSymbol(coinBizBo.getRealName());
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getRealName(), JsonUtil.toJsonString(coinBizBo));
            }
        }
        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return 1;
    }

    @Override
    public CoinPageListBizBo queryAssetList(String realName,long currentPage, long pageSize)  {
        IPage<AssetPo> coinPoIPage= iAssetDao.selectPosList(realName,currentPage,pageSize);
        CoinPageListBizBo coinListBizBo=new CoinPageListBizBo();
        coinListBizBo.setTotal(coinPoIPage.getTotal());
        List<AssetBizBo> bizBoList=new ArrayList<>();
        for(AssetPo po:coinPoIPage.getRecords())
        {
            AssetBizBo bo=ConventObjectUtil.conventObject(po,AssetBizBo.class);
            bizBoList.add(bo);
        }
        coinListBizBo.setRows(bizBoList);
        return coinListBizBo;
    }

    @Override
    public AssetListBizBo queryRealNameList() {
        List<AssetPo> poList= iAssetDao.selectList();
        List<String> coinList=new ArrayList<>();
        for(AssetPo po:poList)
        {
            coinList.add(po.getRealName());
        }
        AssetListBizBo listBizBo=new AssetListBizBo();
        listBizBo.setAssetList(coinList);
        return listBizBo;
    }


    @Override
    public AssetBizBo queryCoinById(long id) {
        AssetPo po= iAssetDao.selectPoById(id);
        AssetBizBo bo=ConventObjectUtil.conventObject(po,AssetBizBo.class);
        return bo;
    }

    @Override
    public boolean checkHasCoin(String coinName) {
        return iAssetDao.checkHasCoin(coinName);
    }
}
