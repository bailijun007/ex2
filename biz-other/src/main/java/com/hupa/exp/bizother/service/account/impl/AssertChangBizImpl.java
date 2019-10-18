package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssertChangBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAssetChangeSymbolMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAssertChangeAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundAssetChangeSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcAssetChangeAssetMongoPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssertChangBizImpl implements IAssertChangBiz {
    @Autowired
    private IPcAssertChangeAssetMongoDao iPcAssertChangeDao;
    @Autowired
    private IFundAssetChangeSymbolMongoDao iFundAssertChangeDao;
    @Override
    public FundAssertChangeMongoPageBizBo queryFundAssetPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundAssetChangeSymbolMongoPo> pageData=iFundAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        FundAssertChangeMongoPageBizBo pageBizBo=new FundAssertChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<FundAssertChangeMongoBizBo> boList=new ArrayList<>();
        for(FundAssetChangeSymbolMongoPo po:pageData.getRows())
        {
            FundAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssertChangeMongoBizBo.class);
            boList.add(bo);
        }
        pageBizBo.setRows(boList);
        pageBizBo.setTotal(pageData.getTotalCount());
        return pageBizBo;
    }

    @Override
    public FundAssertChangeMongoBizBo queryFundAssetChangePoById(long id, String symbol) {
        FundAssetChangeSymbolMongoPo po= iFundAssertChangeDao.selectPoById(id,symbol);
        FundAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssertChangeMongoBizBo.class);
        return bo;
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryPcAssertPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<PcAssetChangeAssetMongoPo> pageData=iPcAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        PcAssertChangeMongoPageBizBo pageBizBo=new PcAssertChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<PcAssertChangeMongoBizBo> boList=new ArrayList<>();
        for(PcAssetChangeAssetMongoPo po:pageData.getRows())
        {
            PcAssertChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
            boList.add(bo);
        }
        pageBizBo.setRows(boList);
        pageBizBo.setTotal(pageData.getTotalCount());
        return pageBizBo;
    }

    @Override
    public PcAssertChangeMongoBizBo selectPcAssertChangePoById(long id, String symbol) {
        PcAssetChangeAssetMongoPo po= iPcAssertChangeDao.selectPoById(id,symbol);
        PcAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
        return bo;
    }

    private PcAssertChangeMongoPageBizBo getBizBo(MongoPage<PcAssetChangeAssetMongoPo> mongoPagePo ){

        PcAssertChangeMongoPageBizBo bizBo = new PcAssertChangeMongoPageBizBo();

        List<PcAssertChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssetChangeAssetMongoPo po :  mongoPagePo.getRows()){
            PcAssertChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(mongoPagePo.getTotalCount());
        bizBo.setPageSize(mongoPagePo.getPageSize());
        return bizBo;
    }

    public PcAssertChangeMongoPageBizBo getBizBo(List<PcAssetChangeAssetMongoPo> pos){


        PcAssertChangeMongoPageBizBo bizBo = new PcAssertChangeMongoPageBizBo();

        List<PcAssertChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssetChangeAssetMongoPo po :  pos){

            PcAssertChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(pos.size());
        bizBo.setPageSize(pos.size());
        return bizBo;

    }
}
