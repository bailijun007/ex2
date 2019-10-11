package com.hupa.exp.bizother.service.price.def;

import java.math.BigDecimal;

public interface ILastPriceBiz {

    BigDecimal get(String pair);

    void save(String pair, BigDecimal price);

    BigDecimal calc(String pair);
}
