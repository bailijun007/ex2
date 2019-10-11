package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.base.enums.WithdrawStatusEnum;
import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.FundWithdrawAddressBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.QueryFundWithdrawAddressBizBo;
import com.hupa.exp.bizother.entity.account.QueryFundWithdrawHistoryBizBo;
import com.hupa.exp.bizother.service.account.def.ICoinBiz;
import com.hupa.exp.bizother.service.account.def.IWithdrawBiz;
import com.hupa.exp.daoex2.dao.expv2.def.IFundWithdrawAddressSymbolDao;
import com.hupa.exp.daoex2.dao.expv2.def.IFundWithdrawSymbolDao;
import com.hupa.exp.daoex2.entity.po.expv2mongo.FundWithdrawAddressSymbolMongoPo;
import com.hupa.exp.daoex2.entity.po.expv2mongo.FundWithdrawSymbolMongoPo;
import com.hupa.exp.daoex2.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daoex2.enums.SortEnum;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawBizImpl implements IWithdrawBiz {
    @Autowired
    private IFundWithdrawSymbolDao iFundWithdrawSymbolDao;

    @Override
    public FundWithdrawMongoPageBizBo selectFundWithdrawPageData(String account, String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundWithdrawSymbolMongoPo> pageData=iFundWithdrawSymbolDao.pageFundWithdrawPos(
                account,  symbol, id, currentPage, pageSize);
        FundWithdrawMongoPageBizBo pageBizBo=new FundWithdrawMongoPageBizBo();
        pageBizBo.setTotal(pageData.getTotalCount());
        pageBizBo.setPageSize(pageSize);
        List<FundWithdrawMongoBizBo> bizBoList=new ArrayList<>();
        for(FundWithdrawSymbolMongoPo po:pageData.getRows())
        {
            FundWithdrawMongoBizBo bo= ConventObjectUtil.conventObject(po,FundWithdrawMongoBizBo.class);
            bizBoList.add(bo);
        }
        pageBizBo.setRows(bizBoList);
        return pageBizBo;
    }

    @Override
    public FundWithdrawMongoBizBo selectFundWithdrawById(long id, String symbol) {
        FundWithdrawSymbolMongoPo po= iFundWithdrawSymbolDao.selectFundWithdrawPoById(id,symbol);
        FundWithdrawMongoBizBo bo=ConventObjectUtil.conventObject(po,FundWithdrawMongoBizBo.class);
        return bo;
    }




}