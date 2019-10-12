package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.CoinListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface ICoinBiz {


    long createCoin(CoinBizBo expUserBo) throws BizException;

    long editCoin(CoinBizBo expUserBo) throws BizException;

    CoinPageListBizBo queryCoinList(long currentPage, long pageSize);

    CoinListBizBo queryCoinNameList();

    CoinBizBo queryCoinById(long id);

    boolean checkHasCoin(String symbol);
}
