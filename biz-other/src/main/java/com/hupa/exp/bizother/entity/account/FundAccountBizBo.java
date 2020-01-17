package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;

public class FundAccountBizBo {

    /**
     * 总资产
     */
    private BigDecimal total;
    /**
     * 可用资产
     */
    private BigDecimal available;
    /**
     * 货币
     */
    private String asset;
    /**
     * 冻结资产
     */
    private BigDecimal lock;
    /**
     * 账户id
     */
    private Long accountId;

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

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getLock() {
        return lock;
    }

    public void setLock(BigDecimal lock) {
        this.lock = lock;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
