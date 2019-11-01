package com.hupa.exp.servermng.entity.earningrate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class PcEarningRateInputDto extends BaseInputDto {
    private Long id;
    private String userName;
    private Integer sort;
    private BigDecimal earningRate;
    private Long earningRateTime;

    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(BigDecimal earningRate) {
        this.earningRate = earningRate;
    }

    public Long getEarningRateTime() {
        return earningRateTime;
    }

    public void setEarningRateTime(Long earningRateTime) {
        this.earningRateTime = earningRateTime;
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
