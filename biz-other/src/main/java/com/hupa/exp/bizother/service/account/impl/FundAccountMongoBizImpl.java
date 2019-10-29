package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAccountLogMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAccountLogMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IFundAccountMongoBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAccountAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundAccountLogSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundAccountMongoBizImpl implements IFundAccountMongoBiz {

    @Autowired
    private IFundAccountAssetMongoDao iFundAccountMongoDao;

    @Override
    public FundAccountLogMongoPageBizBo selectFundAccountLogPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundAccountLogSymbolMongoPo> pageData=iFundAccountMongoDao.selectFundAccountLogPos(
                symbol, id, currentPage, pageSize);
        FundAccountLogMongoPageBizBo pageBizBo=new FundAccountLogMongoPageBizBo();
        pageBizBo.setTotal(pageData.getTotalCount());
        pageBizBo.setPageSize(pageSize);
        List<FundAccountLogMongoBizBo> bizBoList=new ArrayList<>();
        for(FundAccountLogSymbolMongoPo po:pageData.getRows())
        {
            FundAccountLogMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAccountLogMongoBizBo.class);
            bizBoList.add(bo);
        }
        pageBizBo.setRows(bizBoList);
        return pageBizBo;
    }
}
