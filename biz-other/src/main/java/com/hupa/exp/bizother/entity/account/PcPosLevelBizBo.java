package com.hupa.exp.bizother.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PcPosLevelBizBo {
    private Long id;
    private String asset;
    private String symbol;
    private Integer gear;
    private Long minAmt;
    private Long maxAmt;
    private BigDecimal maxLeverage;
    @JsonProperty("posHoldMarginRatio")
    private BigDecimal posHoldMarginRatio;
    private BigDecimal minHoldMarginRatio;
    private Long ctime;
    private Long mtime;

    public BigDecimal getMinHoldMarginRatio() {
        return minHoldMarginRatio;
    }

    public void setMinHoldMarginRatio(BigDecimal minHoldMarginRatio) {
        this.minHoldMarginRatio = minHoldMarginRatio;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Long getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(Long minAmt) {
        this.minAmt = minAmt;
    }

    public Long getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(Long maxAmt) {
        this.maxAmt = maxAmt;
    }

    public BigDecimal getMaxLeverage() {
        return maxLeverage;
    }

    public void setMaxLeverage(BigDecimal maxLeverage) {
        this.maxLeverage = maxLeverage;
    }

    public BigDecimal getPosHoldMarginRatio() {
        return posHoldMarginRatio;
    }

    public void setPosHoldMarginRatio(BigDecimal posHoldMarginRatio) {
        this.posHoldMarginRatio = posHoldMarginRatio;
    }
}
