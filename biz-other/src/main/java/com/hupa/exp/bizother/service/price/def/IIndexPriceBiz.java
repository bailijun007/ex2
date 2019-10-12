package com.hupa.exp.bizother.service.price.def;


import java.math.BigDecimal;

/**
 * 指数价格
 */
public interface IIndexPriceBiz{

    BigDecimal get(String pair);

    void save(String pair, BigDecimal price);
}
