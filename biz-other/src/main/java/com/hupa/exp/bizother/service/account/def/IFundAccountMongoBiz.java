package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAccountLogMongoPageBizBo;

public interface IFundAccountMongoBiz {

    FundAccountLogMongoPageBizBo selectFundAccountLogPageData(String symbol, long id, long currentPage, int pageSize);
}