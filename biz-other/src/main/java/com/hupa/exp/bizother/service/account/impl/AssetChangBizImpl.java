package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetChangBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAssetChangeSymbolMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAssetChangeAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundAssetChangeSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcAssetChangeAssetMongoPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetChangBizImpl implements IAssetChangBiz {
    @Autowired
    private IPcAssetChangeAssetMongoDao iPcAssertChangeDao;
    @Autowired
    private IFundAssetChangeSymbolMongoDao iFundAssertChangeDao;
    @Override
    public FundAssetChangeMongoPageBizBo queryFundAssetPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundAssetChangeSymbolMongoPo> pageData=iFundAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        FundAssetChangeMongoPageBizBo pageBizBo=new FundAssetChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<FundAssetChangeMongoBizBo> boList=new ArrayList<>();
        for(FundAssetChangeSymbolMongoPo po:pageData.getRows())
        {
            FundAssetChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssetChangeMongoBizBo.class);
            boList.add(bo);
        }
        pageBizBo.setRows(boList);
        pageBizBo.setTotal(pageData.getTotalCount());
        return pageBizBo;
    }

    @Override
    public FundAssetChangeMongoBizBo queryFundAssetChangePoById(long id, String symbol) {
        FundAssetChangeSymbolMongoPo po= iFundAssertChangeDao.selectPoById(id,symbol);
        FundAssetChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssetChangeMongoBizBo.class);
        return bo;
    }

    @Override
    public PcAssetChangeMongoPageBizBo queryPcAssetPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<PcAssetChangeAssetMongoPo> pageData=iPcAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        PcAssetChangeMongoPageBizBo pageBizBo=new PcAssetChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<PcAssetChangeMongoBizBo> boList=new ArrayList<>();
        for(PcAssetChangeAssetMongoPo po:pageData.getRows())
        {
            PcAssetChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
            boList.add(bo);
        }
        pageBizBo.setRows(boList);
        pageBizBo.setTotal(pageData.getTotalCount());
        return pageBizBo;
    }

    @Override
    public PcAssetChangeMongoBizBo selectPcAssetChangePoById(long id, String symbol) {
        PcAssetChangeAssetMongoPo po= iPcAssertChangeDao.selectPoById(id,symbol);
        PcAssetChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
        return bo;
    }

    private PcAssetChangeMongoPageBizBo getBizBo(MongoPage<PcAssetChangeAssetMongoPo> mongoPagePo ){

        PcAssetChangeMongoPageBizBo bizBo = new PcAssetChangeMongoPageBizBo();

        List<PcAssetChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssetChangeAssetMongoPo po :  mongoPagePo.getRows()){
            PcAssetChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(mongoPagePo.getTotalCount());
        bizBo.setPageSize(mongoPagePo.getPageSize());
        return bizBo;
    }

    public PcAssetChangeMongoPageBizBo getBizBo(List<PcAssetChangeAssetMongoPo> pos){


        PcAssetChangeMongoPageBizBo bizBo = new PcAssetChangeMongoPageBizBo();

        List<PcAssetChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssetChangeAssetMongoPo po :  pos){

            PcAssetChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(pos.size());
        bizBo.setPageSize(pos.size());
        return bizBo;

    }
}
