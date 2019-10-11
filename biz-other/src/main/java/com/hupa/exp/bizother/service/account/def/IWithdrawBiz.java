package com.hupa.exp.bizother.service.account.def;


import com.hupa.exp.bizother.entity.account.FundWithdrawAddressBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.QueryFundWithdrawAddressBizBo;
import com.hupa.exp.bizother.entity.account.QueryFundWithdrawHistoryBizBo;

/**
 *
 */
public interface IWithdrawBiz {

    FundWithdrawMongoPageBizBo selectFundWithdrawPageData(String account, String symbol, long id, long currentPage, int pageSize);


    FundWithdrawMongoBizBo selectFundWithdrawById(long id, String symbol);
}
