package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoPageBizBo;


/**
 * 资产变动
 */
public interface IAssetChangBiz {

    FundAssetChangeMongoPageBizBo queryFundAssetPageData(String symbol, long id, long currentPage, int pageSize);

    FundAssetChangeMongoBizBo queryFundAssetChangePoById(long id, String symbol);

    PcAssetChangeMongoPageBizBo queryPcAssetPageData(String symbol, long id, long currentPage, int pageSize);

    PcAssetChangeMongoBizBo selectPcAssetChangePoById(long id, String symbol);
}
