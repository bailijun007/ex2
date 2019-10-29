package com.hupa.exp.bizother.service.price.def;

import java.math.BigDecimal;

public interface ILastPriceBiz {

    BigDecimal get(String asset, String symbol);

    void save(String asset,String symbol, BigDecimal price);

}
