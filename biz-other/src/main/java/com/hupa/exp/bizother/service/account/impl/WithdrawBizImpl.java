package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IWithdrawBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IFundWithdrawAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundWithdrawAssetMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawBizImpl implements IWithdrawBiz {
    @Autowired
    private IFundWithdrawAssetMongoDao iFundWithdrawSymbolDao;

    @Override
    public FundWithdrawMongoPageBizBo selectFundWithdrawPageData(Long accountId, String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundWithdrawAssetMongoPo> pageData=iFundWithdrawSymbolDao.pageFundWithdrawPos(
                accountId,  symbol, id, currentPage, pageSize);
        FundWithdrawMongoPageBizBo pageBizBo=new FundWithdrawMongoPageBizBo();
        pageBizBo.setTotal(pageData.getTotalCount());
        pageBizBo.setPageSize(pageSize);
        List<FundWithdrawMongoBizBo> bizBoList=new ArrayList<>();
        for(FundWithdrawAssetMongoPo po:pageData.getRows())
        {
            FundWithdrawMongoBizBo bo= ConventObjectUtil.conventObject(po,FundWithdrawMongoBizBo.class);
            bizBoList.add(bo);
        }
        pageBizBo.setRows(bizBoList);
        return pageBizBo;
    }

    @Override
    public FundWithdrawMongoBizBo selectFundWithdrawById(long id, String symbol) {
        FundWithdrawAssetMongoPo po= iFundWithdrawSymbolDao.selectFundWithdrawPoById(id,symbol);
        FundWithdrawMongoBizBo bo=ConventObjectUtil.conventObject(po,FundWithdrawMongoBizBo.class);
        return bo;
    }




}
