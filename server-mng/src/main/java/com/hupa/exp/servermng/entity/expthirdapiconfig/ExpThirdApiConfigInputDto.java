package com.hupa.exp.servermng.entity.expthirdapiconfig;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ExpThirdApiConfigInputDto extends BaseInputDto {
    private Long id;
    private Integer thirdApiId;
    private String thirdApiName;
    private Integer apiModule;
    private String timeUnit;
    private Integer limitTime;
    private Integer limitCount;
    private Integer enableFlag;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getThirdApiId() {
        return thirdApiId;
    }

    public void setThirdApiId(Integer thirdApiId) {
        this.thirdApiId = thirdApiId;
    }

    public String getThirdApiName() {
        return thirdApiName;
    }

    public void setThirdApiName(String thirdApiName) {
        this.thirdApiName = thirdApiName;
    }

    public Integer getApiModule() {
        return apiModule;
    }

    public void setApiModule(Integer apiModule) {
        this.apiModule = apiModule;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
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
