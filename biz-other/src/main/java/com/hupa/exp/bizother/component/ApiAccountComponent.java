package com.hupa.exp.bizother.component;//package com.hupa.exp.bizaccount.component;
//
//import com.hupa.exp.bizticker.entity.PcTickerBizBo;
//import com.hupa.exp.bizticker.service.def.ITickerBiz;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//
//@Component
//public class ApiAccountComponent {
//
//
//    @Autowired
//    private ITickerBiz<PcTickerBizBo> iTickerBiz;
//
////    public BigDecimal calcAssetValuationBtc(String symbol){
////
////
////        String pairByBtc="BTC_USD";
////        String pairByOther = symbol+"_USD";
////
////        //String mappingSymbol="BTC";
////
////        PcTickerBizBo tickerBoByBtc = iTickerBiz.get(pairByBtc);
////        PcTickerBizBo tickerBoByOther = iTickerBiz.get(pairByOther);
////
////        if(tickerBoByBtc==null)
////            return BigDecimal.ZERO;
////        if(tickerBoByOther==null)
////            return BigDecimal.ZERO;
////
////        BigDecimal temp = tickerBoByOther.getLastPrice().divide(tickerBoByBtc.getLastPrice(), 16, BigDecimal.ROUND_DOWN);
////        return temp;
////    }
//
//
//    public BigDecimal calcAssetValuationBtc(String symbolByMapping, String symbol,BigDecimal assetVol){
//
//
//        String pairByMapping=symbolByMapping+"_USD";
//        String pairByOther = symbol+"_USD";
//
//        //String symbolByMapping="BTC";
//
//        PcTickerBizBo tickerBoByMapping = iTickerBiz.get(pairByMapping);
//        PcTickerBizBo tickerBoByOther = iTickerBiz.get(pairByOther);
//
//        if(tickerBoByMapping==null)
//            return BigDecimal.ZERO;
//        if(tickerBoByOther==null)
//            return BigDecimal.ZERO;
//
//
//        BigDecimal temp;
//
//        if(symbolByMapping.equalsIgnoreCase(symbol)){
//
//            temp = tickerBoByMapping.getLastPrice().divide(tickerBoByMapping.getLastPrice(), 16, BigDecimal.ROUND_DOWN);
//
//        }else {
//
//            temp = tickerBoByOther.getLastPrice().divide(tickerBoByMapping.getLastPrice(), 16, BigDecimal.ROUND_DOWN);
//        }
//
//
//        temp = temp.multiply(assetVol);
//
//        return temp;
//    }
//}
