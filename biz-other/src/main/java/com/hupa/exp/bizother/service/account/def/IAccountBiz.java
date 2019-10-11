package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.account.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;

public interface IAccountBiz {

    boolean existAccount(long userId, String symbol, int accountType);


    void createPcAccount(long userId, String symbol) throws PcAccountException;

    void createFundAccount(long userId, String symbol) throws FundAccountException;

    PcAccountBizBo getPcAccount(long userId, String symbol);

    FundAccountBizBo getFundAccount(long userId, String symbol);



}
