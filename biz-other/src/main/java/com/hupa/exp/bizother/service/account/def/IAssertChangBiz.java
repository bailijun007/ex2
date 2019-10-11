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


    FundAssertChangeMongoPageBizBo queryFundAssertPageData(String symbol, long id, long currentPage, int pageSize);

    FundAssertChangeMongoBizBo queryFundAssertChangePoById(long id, String symbol);

    PcAssertChangeMongoPageBizBo queryBizBoByOrderId(Long userId, long orderId, String pair);

    PcAssertChangeMongoPageBizBo queryBizBoByTrade(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Integer bidFlag, Integer closeFlag, long startDate, long endDate, long currentPage, int pageSize);

    /**
     * 交易类
     * @param userId
     * @param pair
     * @param tradeType
     * @param bidFlag
     * @param closeFlag
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByTrade(long userId, String pair, String symbol, int tradeType, int bidFlag, int closeFlag, long startDate, long endDate, long currentPage, int pageSize);


    /**
     * 划转类
     * @param userId
     * @param pair
     * @param symbol
     * @param tradeTypeArray
     * @param transVolumeMore
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByTrans(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Boolean transVolumeMore, long startDate, long endDate, long currentPage, int pageSize);

    /**
     * 划转类
     * @param userId
     * @param pair
     * @param tradeType
     * @param transVolumeMore 正的为true,负的为false,null为正负都要
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByTrans(long userId, String pair, String symbol, int tradeType, Boolean transVolumeMore, long startDate, long endDate, long currentPage, int pageSize);


    /**
     * 强平类
     * @param userId
     * @param pair
     * @param symbol
     * @param longFlag
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByLiq(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, Integer longFlag, long startDate, long endDate, long currentPage, int pageSize);


    /**
     * 强平类
     * @param userId
     * @param pair
     * @param tradeType
     * @param longFlag 1.多仓,0.空仓
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByLiq(long userId, String pair, String symbol, int tradeType, int longFlag, long startDate, long endDate, long currentPage, int pageSize);


    /**
     * 所有tradeType
     * @param userId
     * @param pair
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    PcAssertChangeMongoPageBizBo queryBizBoByAll(long userId, String pair, String symbol, Collection<Integer> tradeTypeArray, long startDate, long endDate, long currentPage, int pageSize);


//    /**
//     * 所有tradeType
//     * @param userId
//     * @param pair
//     * @param startDate
//     * @param endDate
//     * @param currentPage
//     * @param pageSize
//     * @return
//     */
//    PcAssertChangeMongoPageBizBo queryBizBoByAll(long userId,String pair,String symbol, long startDate,long endDate,long currentPage, int pageSize);

    PcAssertChangeMongoPageBizBo queryPcAssertPageData(String symbol, long id, long currentPage, int pageSize);

    PcAssertChangeMongoBizBo selectPcAssertChangePoById(long id, String symbol);
}
