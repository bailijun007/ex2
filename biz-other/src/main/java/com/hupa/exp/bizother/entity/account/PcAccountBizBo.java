package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;

public class PcAccountBizBo {

    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 总额
     */
    private BigDecimal total;
    /**
     * 可用余额
     */
    private BigDecimal available;
    /**
     * 货币对
     */
    private String symbol;
    /**
     * 货币
     */
    private String asset;
    /**
     * 委托保证金
     */
    private BigDecimal orderMargin;
    /**
     * 仓位保证金
     */
    private BigDecimal posMargin;
    /**
     * 暂时不知道使用在什么地方
     * 最大可开张数
     */
    private BigDecimal maxOpenAmt;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

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

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

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

    public BigDecimal getMaxOpenAmt() {
        return maxOpenAmt;
    }

    public void setMaxOpenAmt(BigDecimal maxOpenAmt) {
        this.maxOpenAmt = maxOpenAmt;
    }
}
