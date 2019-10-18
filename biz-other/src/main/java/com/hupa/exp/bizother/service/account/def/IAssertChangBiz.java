package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoPageBizBo;

import java.util.Collection;


/**
 * 资产变动
 */
public interface IAssertChangBiz {

    FundAssertChangeMongoPageBizBo queryFundAssetPageData(String symbol, long id, long currentPage, int pageSize);

    FundAssertChangeMongoBizBo queryFundAssetChangePoById(long id, String symbol);

    PcAssertChangeMongoPageBizBo queryPcAssertPageData(String symbol, long id, long currentPage, int pageSize);

    PcAssertChangeMongoBizBo selectPcAssertChangePoById(long id, String symbol);
}
