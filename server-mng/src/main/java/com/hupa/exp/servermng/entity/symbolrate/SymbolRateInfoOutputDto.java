package com.hupa.exp.servermng.entity.symbolrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.math.BigDecimal;

public class SymbolRateInfoOutputDto extends BaseOutputDto {
    private Long id;
    private String asset;
    private String symbol;
    @JsonProperty("base_rate")
    private BigDecimal baseRate;
    @JsonProperty("valuation_rate")
    private BigDecimal valuationRate;
    @JsonProperty("rate_time")
    private Long rateTime;
    private Long ctime;
    private Long mtime;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
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

    public Long getId() {
        return id;
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
