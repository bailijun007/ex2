package com.hupa.exp.servermng.entity.poslevel;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class PosLevelInputDto extends BaseInputDto {
    private Long id;
    private String asset;
    private String symbol;
    private Integer gear;
    private Integer minAmt;
    private Integer maxAmt;
    private Integer maxLeverage;
    private BigDecimal posMatinMarginRatio;
    private Long ctime;
    private Long mtime;

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

    public Integer getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(Integer minAmt) {
        this.minAmt = minAmt;
    }

    public Integer getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(Integer maxAmt) {
        this.maxAmt = maxAmt;
    }

    public Integer getMaxLeverage() {
        return maxLeverage;
    }

    public void setMaxLeverage(Integer maxLeverage) {
        this.maxLeverage = maxLeverage;
    }

    public BigDecimal getPosMatinMarginRatio() {
        return posMatinMarginRatio;
    }

    public void setPosMatinMarginRatio(BigDecimal posMatinMarginRatio) {
        this.posMatinMarginRatio = posMatinMarginRatio;
    }
}
