package com.hupa.exp.bizother.component;

import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
import com.hupa.exp.bizother.service.price.impl.PcLastPriceBizImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class BizAccountComponent {



    @Autowired
    @Qualifier(PcLastPriceBizImpl.serviceName)
    private ILastPriceBiz iLastPriceBiz;


    /**
     * 换算成当前货币的资产
     * @param assetByValuation 计价资产
     * @param assetByCurrent 当前资产
     * @param assetVolume 量
     * @return
     */
    public BigDecimal calcAssetValuationBtc(String assetByValuation, String assetByCurrent, BigDecimal assetVolume){


        String symbolByValuation= assetByValuation+"_USD";
        String symbolByCurrent = assetByCurrent+"_USD";

        //String assetByValuation="BTC";

        BigDecimal lastPriceByValuation = iLastPriceBiz.get(assetByValuation, symbolByValuation);
        BigDecimal lastPriceBoByOther = iLastPriceBiz.get(assetByCurrent, symbolByCurrent);

        if(lastPriceByValuation==null)
            return BigDecimal.ZERO;
        if(lastPriceBoByOther==null)
            return BigDecimal.ZERO;


        BigDecimal temp;

        if(assetByValuation.equalsIgnoreCase(assetByCurrent)){

            temp = lastPriceByValuation.divide(lastPriceByValuation, 16, BigDecimal.ROUND_DOWN);

        }else {

            temp = lastPriceBoByOther.divide(lastPriceByValuation, 16, BigDecimal.ROUND_DOWN);
        }


        temp = temp.multiply(assetVolume);

        return temp;
    }
}
