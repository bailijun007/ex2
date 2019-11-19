package com.hupa.exp.servermng.entity.expthirdapiconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class ExpThirdApiConfigInfoOutputDto extends BaseOutputDto {
    private String id;
    @JsonProperty("third_api_id")
    private String thirdApiId;
    @JsonProperty("third_api_name")
    private String thirdApiName;
    @JsonProperty("api_module")
    private String apiModule;
    @JsonProperty("time_unit")
    private String timeUnit;
    @JsonProperty("limit_time")
    private String limitTime;
    @JsonProperty("limit_count")
    private String limitCount;
    @JsonProperty("enable_flag")
    private String enableFlag;
    private String ctime;
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThirdApiId() {
        return thirdApiId;
    }

    public void setThirdApiId(String thirdApiId) {
        this.thirdApiId = thirdApiId;
    }

    public String getThirdApiName() {
        return thirdApiName;
    }

    public void setThirdApiName(String thirdApiName) {
        this.thirdApiName = thirdApiName;
    }

    public String getApiModule() {
        return apiModule;
    }

    public void setApiModule(String apiModule) {
        this.apiModule = apiModule;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(String limitCount) {
        this.limitCount = limitCount;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
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

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }
}
