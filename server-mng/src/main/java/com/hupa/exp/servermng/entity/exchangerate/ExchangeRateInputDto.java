package com.hupa.exp.servermng.entity.exchangerate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class ExchangeRateInputDto extends BaseInputDto {
    private Long id;
    private String sourceAsset;
    private String targetAsset;
    private BigDecimal exchangeRate;
    private boolean autoRefresh;
    private Long ctime;
    private Long mtime;

    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
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
