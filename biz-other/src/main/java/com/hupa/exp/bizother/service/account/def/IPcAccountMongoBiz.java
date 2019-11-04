package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.MongoBo.PcAccountLogMongoPageBizBo;

public interface IPcAccountMongoBiz {
    PcAccountLogMongoPageBizBo selectPcAccountLogPageData(String symbol, Long id, long currentPage, int pageSize);
}
