package com.hupa.exp.bizother.service.account.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.SymbolListBizBo;
import com.hupa.exp.bizother.service.account.def.ICoinBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.ICoinDao;
import com.hupa.exp.daoex2.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daoex2.entity.po.expv2.CoinPo;
import com.hupa.exp.daoex2.entity.po.expv2.ExpDicPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
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
    public boolean existBySymbol(String symbol) {
        if(StringUtils.isEmpty(symbol))
            return false;

        CoinPo coinPo = iCoinDao.selectOnePoBySymbol(symbol);
        if(coinPo==null)
            return false;

        return true;
    }

    @Override
    public long createCoin(CoinBizBo coinBizBo) throws BizException {
        CoinPo po= ConventObjectUtil.conventObject(coinBizBo,CoinPo.class);
        po.setCtime(System.currentTimeMillis());
        ExpDicPo dicPo= dicDao.selectDicByKey("CoinRedisKey");
        if(iCoinDao.insert(po)>0)
        {
            if(dicPo!=null)
            {
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getSymbol(), JSON.toJSONString(coinBizBo));
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
                redisUtilDb0.hset(dicPo.getValue(),coinBizBo.getSymbol(), JSON.toJSONString(coinBizBo));
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
    public SymbolListBizBo querySymbolList() {
        List<CoinPo> poList=iCoinDao.selectList();
        List<String> symbolList=new ArrayList<>();
        for(CoinPo po:poList)
        {
            symbolList.add(po.getSymbol());
        }
        SymbolListBizBo listBizBo=new SymbolListBizBo();
        listBizBo.setSymbolList(symbolList);
        return listBizBo;
    }

    @Override
    public SymbolListBizBo querySymbolByWithdraw(long userId) {
        return querySymbolList();
    }

    @Override
    public SymbolListBizBo querySymbolByDeposit(long userId) {
        return querySymbolList();
    }

    @Override
    public CoinBizBo queryCoinById(long id) {
        CoinPo po=iCoinDao.selectPoById(id);
        CoinBizBo bo=ConventObjectUtil.conventObject(po,CoinBizBo.class);
        return bo;
    }

    @Override
    public CoinBizBo queryCoinBySymbol(String symbol) {
        CoinPo po=iCoinDao.selectOnePoBySymbol(symbol);
        if(po == null )
            return null;

        CoinBizBo bo=ConventObjectUtil.conventObject(po,CoinBizBo.class);
        return bo;
    }

    @Override
    public CoinBizBo queryCoinByChainSymbolId(int chainSymbolId) {

        CoinPo coinPo = iCoinDao.selectOnePoByChainSymbolId(chainSymbolId);
        if(coinPo == null)
            return null;

        CoinBizBo bizBo = ConventObjectUtil.conventObject(coinPo,CoinBizBo.class);
        return bizBo;
    }

    @Override
    public boolean checkHasCoin(String symbol) {
        return iCoinDao.checkHasCoin(symbol);
    }
}
