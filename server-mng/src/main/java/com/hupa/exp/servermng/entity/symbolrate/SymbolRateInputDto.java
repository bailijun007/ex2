package com.hupa.exp.servermng.entity.symbolrate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class SymbolRateInputDto extends BaseInputDto {
    private Long id;
    private String asset;
    private String symbol;
    private BigDecimal baseRate;
    private BigDecimal valuationRate;
    private Long rateTime;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(BigDecimal baseRate) {
        this.baseRate = baseRate;
    }

    public BigDecimal getValuationRate() {
        return valuationRate;
    }

    public void setValuationRate(BigDecimal valuationRate) {
        this.valuationRate = valuationRate;
    }

    public Long getRateTime() {
        return rateTime;
    }

    public void setRateTime(Long rateTime) {
        this.rateTime = rateTime;
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
}
