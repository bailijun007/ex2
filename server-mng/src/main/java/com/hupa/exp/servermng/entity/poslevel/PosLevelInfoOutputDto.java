package com.hupa.exp.servermng.entity.poslevel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

public class PosLevelInfoOutputDto extends BaseOutputDto {
    private String id;
    private String asset;
    private String symbol;
    private String gear;
    private String minAmt;
    private String maxAmt;
    private String maxLeverage;
    private String posHoldMarginRatio;
    private String minHoldMarginRatio;
    private Long ctime;
    private Long mtime;

    public String getMinHoldMarginRatio() {
        return minHoldMarginRatio;
    }

    public void setMinHoldMarginRatio(String minHoldMarginRatio) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(String minAmt) {
        this.minAmt = minAmt;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
    }

    public String getMaxLeverage() {
        return maxLeverage;
    }

    public void setMaxLeverage(String maxLeverage) {
        this.maxLeverage = maxLeverage;
    }

    public String getPosHoldMarginRatio() {
        return posHoldMarginRatio;
    }

    public void setPosHoldMarginRatio(String posHoldMarginRatio) {
        this.posHoldMarginRatio = posHoldMarginRatio;
    }
}
