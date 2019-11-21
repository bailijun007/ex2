package com.hupa.exp.servermng.entity.pcmakepricehistory;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class PcMakePriceHistoryInfoOutputDto{
    private String id;
    private String asset;
    private String symbol;
    private String price;
    @JsonProperty("fund_rate")
    private String fundRate;
    @JsonProperty("source_value")
    private String sourceValue;
    private String ctime;
    private String mtime;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFundRate() {
        return fundRate;
    }

    public void setFundRate(String fundRate) {
        this.fundRate = fundRate;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
