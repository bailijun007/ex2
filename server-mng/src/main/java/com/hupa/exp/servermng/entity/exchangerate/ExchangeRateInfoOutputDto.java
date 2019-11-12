package com.hupa.exp.servermng.entity.exchangerate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class ExchangeRateInfoOutputDto extends BaseOutputDto {
    private String id;
    @JsonProperty("source_asset")
    private String sourceAsset;
    @JsonProperty("target_asset")
    private String targetAsset;
    @JsonProperty("exchange_rate")
    private String exchangeRate;
    @JsonProperty("auto_refresh")
    private String autoRefresh;
    private String ctime;
    private String mtime;

    public String getAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(String autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceAsset() {
        return sourceAsset;
    }

    public void setSourceAsset(String sourceAsset) {
        this.sourceAsset = sourceAsset;
    }

    public String getTargetAsset() {
        return targetAsset;
    }

    public void setTargetAsset(String targetAsset) {
        this.targetAsset = targetAsset;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
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
