package com.hupa.exp.bizother.service.account.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.CoinListBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.service.account.def.ICoinBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.ICoinDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.CoinPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinBizImpl implements ICoinBiz {
    @Autowired
    private ICoinDao iCoinDao;

    @Autowired
    private IExpDicDao dicDao;


    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public long createCoin(CoinBizBo coinBizBo) throws BizException {
        CoinPo po= ConventObjectUtil.conventObject(coinBizBo,CoinPo.class);
        po.setCtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("CoinRedisKey");
        if(iCoinDao.insert(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getCoinName(), JSON.toJSONString(coinBizBo));
            }
        }
        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return po.getId();
    }

    @Override
    public long editCoin(CoinBizBo coinBizBo) throws BizException {
        CoinPo po= ConventObjectUtil.conventObject(coinBizBo,CoinPo.class);
        po.setMtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("CoinRedisKey");
        if(iCoinDao.updateById(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getCoinName(), JSON.toJSONString(coinBizBo));
            }
        }
        else
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        return 1;
    }

    @Override
    public CoinPageListBizBo queryCoinList(long currentPage, long pageSize)  {
        IPage<CoinPo> coinPoIPage=iCoinDao.selectCoinList(currentPage,pageSize);
        CoinPageListBizBo coinListBizBo=new CoinPageListBizBo();
        coinListBizBo.setTotal(coinPoIPage.getTotal());
        List<CoinBizBo> bizBoList=new ArrayList<>();
        for(CoinPo po:coinPoIPage.getRecords())
        {
            CoinBizBo bo=ConventObjectUtil.conventObject(po,CoinBizBo.class);
            bizBoList.add(bo);
        }
        coinListBizBo.setRows(bizBoList);
        return coinListBizBo;
    }

    @Override
    public CoinListBizBo queryCoinNameList() {
        List<CoinPo> poList=iCoinDao.selectList();
        List<String> coinList=new ArrayList<>();
        for(CoinPo po:poList)
        {
            coinList.add(po.getCoinName());
        }
        CoinListBizBo listBizBo=new CoinListBizBo();
        listBizBo.setCoinList(coinList);
        return listBizBo;
    }


    @Override
    public CoinBizBo queryCoinById(long id) {
        CoinPo po=iCoinDao.selectPoById(id);
        CoinBizBo bo=ConventObjectUtil.conventObject(po,CoinBizBo.class);
        return bo;
    }

    @Override
    public boolean checkHasCoin(String coinName) {
        return iCoinDao.checkHasCoin(coinName);
    }
}
