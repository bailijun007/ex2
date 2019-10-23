package com.hupa.exp.servermng.entity.collectfeesetting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CollectFeeSettingInfoOutputDto extends BaseOutputDto {

    private String id;
    @JsonProperty("collect_fee_num")
    private String collectFeeNum;
    @JsonProperty("collect_fee_time")
    private String collectFeeTime;
    private String status;
    private String ctime;
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectFeeNum() {
        return collectFeeNum;
    }

    public void setCollectFeeNum(String collectFeeNum) {
        this.collectFeeNum = collectFeeNum;
    }

    public String getCollectFeeTime() {
        return collectFeeTime;
    }

    public void setCollectFeeTime(String collectFeeTime) {
        this.collectFeeTime = collectFeeTime;
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
