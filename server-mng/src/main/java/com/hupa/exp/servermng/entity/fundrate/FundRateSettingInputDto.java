package com.hupa.exp.servermng.entity.fundrate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class FundRateSettingInputDto extends BaseInputDto {
    private Long id;
    private Integer collectNum;
    private Long collectFirstTime;
    private BigDecimal maxFundRate;
    private BigDecimal minFundRate;
    private Integer status;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Long getCollectFirstTime() {
        return collectFirstTime;
    }

    public void setCollectFirstTime(Long collectFirstTime) {
        this.collectFirstTime = collectFirstTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public BigDecimal getMaxFundRate() {
        return maxFundRate;
    }

    public void setMaxFundRate(BigDecimal maxFundRate) {
        this.maxFundRate = maxFundRate;
    }

    public BigDecimal getMinFundRate() {
        return minFundRate;
    }

    public void setMinFundRate(BigDecimal minFundRate) {
        this.minFundRate = minFundRate;
    }
}
