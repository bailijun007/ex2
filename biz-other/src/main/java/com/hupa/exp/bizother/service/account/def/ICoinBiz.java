package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.SymbolListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface ICoinBiz {


    boolean existBySymbol(String symbol);

    long createCoin(CoinBizBo expUserBo) throws BizException;

    long editCoin(CoinBizBo expUserBo) throws BizException;

    CoinPageListBizBo queryCoinList(long currentPage, long pageSize);

    SymbolListBizBo querySymbolList();

    /**
     * 获取有提币权限的币种
     * @return
     */
    SymbolListBizBo querySymbolByWithdraw(long userId);


    /**
     * 获取有充币权限的币种
     * @return
     */
    SymbolListBizBo querySymbolByDeposit(long userId);

    CoinBizBo queryCoinById(long id);

    CoinBizBo queryCoinBySymbol(String symbol);

    CoinBizBo queryCoinByChainSymbolId(int chainSymbolId);

    boolean checkHasCoin(String symbol);
}
