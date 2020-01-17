package com.hupa.exp.bizother.service.account.def;


import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoPageBizBo;

/**
 *
 */
public interface IWithdrawBiz {

    FundWithdrawMongoPageBizBo selectFundWithdrawPageData(Long accountId, String symbol, long id, long currentPage, int pageSize);


    FundWithdrawMongoBizBo selectFundWithdrawById(long id, String symbol);
}
