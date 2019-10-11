package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.base.helper.other.OtherHelper;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssertChangBiz;
import com.hupa.exp.daoex2.dao.expv2.def.IFundAssertChangeMongoDao;
import com.hupa.exp.daoex2.dao.expv2.def.IPcAssertChangeMongoDao;
import com.hupa.exp.daoex2.entity.po.expv2mongo.FundAssertChangeMongoPo;
import com.hupa.exp.daoex2.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daoex2.entity.po.expv2mongo.PcAssertChangeMongoPo;
import com.hupa.exp.daoex2.enums.SortEnum;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AssertChangBizImpl implements IAssertChangBiz {
    @Autowired
    private IPcAssertChangeMongoDao iPcAssertChangeDao;
    @Autowired
    private IFundAssertChangeMongoDao iFundAssertChangeDao;
    @Override
    public FundAssertChangeMongoPageBizBo queryFundAssertPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<FundAssertChangeMongoPo> pageData=iFundAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        FundAssertChangeMongoPageBizBo pageBizBo=new FundAssertChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<FundAssertChangeMongoBizBo> boList=new ArrayList<>();
        for(FundAssertChangeMongoPo po:pageData.getRows())
        {
            FundAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssertChangeMongoBizBo.class);
            boList.add(bo);
        }
        pageBizBo.setRows(boList);
        pageBizBo.setTotal(pageData.getTotalCount());
        return pageBizBo;
    }

    @Override
    public FundAssertChangeMongoBizBo queryFundAssertChangePoById(long id, String symbol) {
        FundAssertChangeMongoPo po= iFundAssertChangeDao.selectPoById(id,symbol);
        FundAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,FundAssertChangeMongoBizBo.class);
        return bo;
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByOrderId(Long userId, long orderId, String pair) {

        List<PcAssertChangeMongoPo> pos = iPcAssertChangeDao.selectPosByOrderId(userId, orderId, OtherHelper.getSymbol(pair));

        return getBizBo(pos);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByTrade(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Integer bidFlag, Integer closeFlag, long startDate, long endDate, long currentPage, int pageSize) {
        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,pair,symbol,tradeTypeArray,bidFlag,closeFlag,startDate,endDate,currentPage,pageSize, SortEnum.desc
        );

        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByTrade(long userId, String pair,String symbol, int tradeType, int bidFlag, int closeFlag, long startDate, long endDate, long currentPage, int pageSize) {
        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,pair,symbol,tradeType,bidFlag,closeFlag,startDate,endDate,currentPage,pageSize, SortEnum.desc
        );
        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByTrans(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Boolean transVolumeMore, long startDate, long endDate, long currentPage, int pageSize) {
        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,pair,symbol,tradeTypeArray,transVolumeMore,startDate,endDate,currentPage,pageSize, SortEnum.desc
        );

        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByTrans(long userId, String pair,String symbol, int tradeType, Boolean transVolumeMore, long startDate, long endDate, long currentPage, int pageSize) {

        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,pair,symbol,tradeType,transVolumeMore,startDate,endDate,currentPage,pageSize, SortEnum.desc
        );

        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByLiq(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Integer longFlag, long startDate, long endDate, long currentPage, int pageSize) {


        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,
                pair,
                symbol,
                tradeTypeArray,
                longFlag,
                startDate, endDate,currentPage, pageSize, SortEnum.desc);


        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByLiq(long userId, String pair, String symbol, int tradeType, int longFlag, long startDate, long endDate, long currentPage, int pageSize) {
        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(
                userId,
                pair,
                symbol,
                tradeType,
                longFlag,
                startDate, endDate,currentPage, pageSize, SortEnum.desc);

        return getBizBo(mongoPagePo);
    }

    @Override
    public PcAssertChangeMongoPageBizBo queryBizBoByAll(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, long startDate, long endDate, long currentPage, int pageSize) {

        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(userId, pair, symbol,tradeTypeArray,  startDate, endDate,currentPage, pageSize, SortEnum.desc);
        return getBizBo(mongoPagePo);

    }

//    @Override
//    public PcAssertChangeMongoPageBizBo queryBizBoByAll(long userId, String pair,String symbol, long startDate, long endDate, long currentPage, int pageSize) {
//
//        MongoPage<PcAssertChangeMongoPo> mongoPagePo = iPcAssertChangeDao.pagePosByParam(userId, pair, symbol, startDate, endDate,currentPage, pageSize, SortEnum.desc);
//        return getBizBo(mongoPagePo);
//    }





    @Override
    public PcAssertChangeMongoPageBizBo queryPcAssertPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<PcAssertChangeMongoPo> pageData=iPcAssertChangeDao.pagePosByParam(symbol,id,
                currentPage,pageSize
        );
        PcAssertChangeMongoPageBizBo pageBizBo=new PcAssertChangeMongoPageBizBo();
        pageBizBo.setPageSize(pageSize);
        List<PcAssertChangeMongoBizBo> boList=new ArrayList<>();
        for(PcAssertChangeMongoPo po:pageData.getRows())
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
        PcAssertChangeMongoPo po= iPcAssertChangeDao.selectPoById(id,symbol);
        PcAssertChangeMongoBizBo bo= ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
        return bo;
    }


    private PcAssertChangeMongoPageBizBo getBizBo(MongoPage<PcAssertChangeMongoPo> mongoPagePo ){

        PcAssertChangeMongoPageBizBo bizBo = new PcAssertChangeMongoPageBizBo();

        List<PcAssertChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssertChangeMongoPo po :  mongoPagePo.getRows()){
            PcAssertChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(mongoPagePo.getTotalCount());
        bizBo.setPageSize(mongoPagePo.getPageSize());
        return bizBo;
    }


    public PcAssertChangeMongoPageBizBo getBizBo(List<PcAssertChangeMongoPo> pos){


        PcAssertChangeMongoPageBizBo bizBo = new PcAssertChangeMongoPageBizBo();

        List<PcAssertChangeMongoBizBo> boList = new ArrayList<>();

        for(PcAssertChangeMongoPo po :  pos){

            PcAssertChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssertChangeMongoBizBo.class);
            boList.add(bo);
        }

        bizBo.setRows(boList);
        bizBo.setTotal(pos.size());
        bizBo.setPageSize(pos.size());
        return bizBo;

    }
}
