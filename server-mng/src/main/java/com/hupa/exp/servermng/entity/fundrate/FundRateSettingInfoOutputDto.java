package com.hupa.exp.servermng.entity.fundrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class FundRateSettingInfoOutputDto extends BaseOutputDto {

    private String id;
    @JsonProperty("collect_num")
    private String collectNum;
    @JsonProperty("collect_first_time")
    private String collectFirstTime;
    @JsonProperty("max_fund_rate")
    private String maxFundRate;
    @JsonProperty("min_fund_rate")
    private String minFundRate;
    private String status;
    private String ctime;
    private String mtime;

    public String getMaxFundRate() {
        return maxFundRate;
    }

    public void setMaxFundRate(String maxFundRate) {
        this.maxFundRate = maxFundRate;
    }

    public String getMinFundRate() {
        return minFundRate;
    }

    public void setMinFundRate(String minFundRate) {
        this.minFundRate = minFundRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public String getCollectFirstTime() {
        return collectFirstTime;
    }

    public void setCollectFirstTime(String collectFirstTime) {
        this.collectFirstTime = collectFirstTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
