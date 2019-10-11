package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;

public class PcAccountBizBo {

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private BigDecimal total;

    private BigDecimal available;

    private String symbol;

    private BigDecimal orderMargin;

    public BigDecimal getOrderMargin() {
        return orderMargin;
    }

    public void setOrderMargin(BigDecimal orderMargin) {
        this.orderMargin = orderMargin;
    }

    public BigDecimal getPosMargin() {
        return posMargin;
    }

    public void setPosMargin(BigDecimal posMargin) {
        this.posMargin = posMargin;
    }

    private BigDecimal posMargin;

    public BigDecimal getMaxOpenAmt() {
        return maxOpenAmt;
    }

    public void setMaxOpenAmt(BigDecimal maxOpenAmt) {
        this.maxOpenAmt = maxOpenAmt;
    }

    //最大可开张数
    private BigDecimal maxOpenAmt;
}
