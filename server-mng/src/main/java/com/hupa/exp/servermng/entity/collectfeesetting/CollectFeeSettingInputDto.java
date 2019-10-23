package com.hupa.exp.servermng.entity.collectfeesetting;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class CollectFeeSettingInputDto extends BaseInputDto {
    private Long id;
    private Integer collectFeeNum;
    private Long collectFeeTime;
    private Integer status;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCollectFeeNum() {
        return collectFeeNum;
    }

    public void setCollectFeeNum(Integer collectFeeNum) {
        this.collectFeeNum = collectFeeNum;
    }

    public Long getCollectFeeTime() {
        return collectFeeTime;
    }

    public void setCollectFeeTime(Long collectFeeTime) {
        this.collectFeeTime = collectFeeTime;
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
}
