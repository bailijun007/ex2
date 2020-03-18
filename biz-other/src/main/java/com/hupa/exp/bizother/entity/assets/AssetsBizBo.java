package com.hupa.exp.bizother.entity.assets;

import java.math.BigDecimal;

public class AssetsBizBo {
    private String asset;
    private BigDecimal fundAccountTotal;
    private BigDecimal fundAccountAvailable;
    private BigDecimal fundAccountLock;
    private BigDecimal pcAccountTotal;
    private BigDecimal pcAccountAvailable;
    private BigDecimal pcAccountLock;
    private BigDecimal pcOrderMargin;
    private BigDecimal pcPosMargin;
    /**
     * 币币账户余额
     */
    private BigDecimal bbBalance;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getFundAccountTotal() {
        return fundAccountTotal;
    }

    public void setFundAccountTotal(BigDecimal fundAccountTotal) {
        this.fundAccountTotal = fundAccountTotal;
    }

    public BigDecimal getFundAccountAvailable() {
        return fundAccountAvailable;
    }

    public void setFundAccountAvailable(BigDecimal fundAccountAvailable) {
        this.fundAccountAvailable = fundAccountAvailable;
    }

    public BigDecimal getFundAccountLock() {
        return fundAccountLock;
    }

    public void setFundAccountLock(BigDecimal fundAccountLock) {
        this.fundAccountLock = fundAccountLock;
    }

    public BigDecimal getPcAccountTotal() {
        return pcAccountTotal;
    }

    public void setPcAccountTotal(BigDecimal pcAccountTotal) {
        this.pcAccountTotal = pcAccountTotal;
    }

    public BigDecimal getPcAccountAvailable() {
        return pcAccountAvailable;
    }

    public void setPcAccountAvailable(BigDecimal pcAccountAvailable) {
        this.pcAccountAvailable = pcAccountAvailable;
    }

    public BigDecimal getPcAccountLock() {
        return pcAccountLock;
    }

    public void setPcAccountLock(BigDecimal pcAccountLock) {
        this.pcAccountLock = pcAccountLock;
    }

    public BigDecimal getPcOrderMargin() {
        return pcOrderMargin;
    }

    public void setPcOrderMargin(BigDecimal pcOrderMargin) {
        this.pcOrderMargin = pcOrderMargin;
    }

    public BigDecimal getPcPosMargin() {
        return pcPosMargin;
    }

    public void setPcPosMargin(BigDecimal pcPosMargin) {
        this.pcPosMargin = pcPosMargin;
    }

    public BigDecimal getBbBalance() {
        return bbBalance;
    }

    public void setBbBalance(BigDecimal bbBalance) {
        this.bbBalance = bbBalance;
    }
}
