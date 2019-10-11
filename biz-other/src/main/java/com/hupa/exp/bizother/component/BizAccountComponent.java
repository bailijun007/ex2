package com.hupa.exp.bizother.component;

import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
import com.hupa.exp.bizother.service.price.impl.PcLastPriceBizImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BizAccountComponent {

//
//    @Autowired
//    private ITickerBiz<PcTickerBizBo> iTickerBiz;

//    public BigDecimal calcAssetValuationBtc(String symbol){
//
//
//        String pairByBtc="BTC_USD";
//        String pairByOther = symbol+"_USD";
//
//        //String mappingSymbol="BTC";
//
//        PcTickerBizBo tickerBoByBtc = iTickerBiz.get(pairByBtc);
//        PcTickerBizBo tickerBoByOther = iTickerBiz.get(pairByOther);
//
//        if(tickerBoByBtc==null)
//            return BigDecimal.ZERO;
//        if(tickerBoByOther==null)
//            return BigDecimal.ZERO;
//
//        BigDecimal temp = tickerBoByOther.getLastPrice().divide(tickerBoByBtc.getLastPrice(), 16, BigDecimal.ROUND_DOWN);
//        return temp;
//    }

    @Autowired
    @Qualifier(PcLastPriceBizImpl.serviceName)
    private ILastPriceBiz iLastPriceBiz;


    /**
     * 换算成当前货币的资产
     * @param symbolByValuation 计价货币
     * @param symbolByCurrent 当前货币
     * @param assetVolume 量
     * @return
     */
    public BigDecimal calcAssetValuationBtc(String symbolByValuation, String symbolByCurrent, BigDecimal assetVolume){


        String pairByValuation=symbolByValuation+"_USD";
        String pairByOther = symbolByCurrent+"_USD";

        //String symbolByValuation="BTC";

        BigDecimal lastPriceByValuation = iLastPriceBiz.get(pairByValuation);
        BigDecimal lastPriceBoByOther = iLastPriceBiz.get(pairByOther);

        if(lastPriceByValuation==null)
            return BigDecimal.ZERO;
        if(lastPriceBoByOther==null)
            return BigDecimal.ZERO;


        BigDecimal temp;

        if(symbolByValuation.equalsIgnoreCase(symbolByCurrent)){

            temp = lastPriceByValuation.divide(lastPriceByValuation, 16, BigDecimal.ROUND_DOWN);

        }else {

            temp = lastPriceBoByOther.divide(lastPriceByValuation, 16, BigDecimal.ROUND_DOWN);
        }


        temp = temp.multiply(assetVolume);

        return temp;
    }
}
