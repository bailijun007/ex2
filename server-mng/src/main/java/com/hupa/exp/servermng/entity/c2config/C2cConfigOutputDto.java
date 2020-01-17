package com.hupa.exp.servermng.entity.c2config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/1/13.
 */
public class C2cConfigOutputDto  extends BaseOutputDto {

    private long id;

    private String asset;
    @JsonProperty("min_in_money")
    private BigDecimal minInMoney;

    @JsonProperty("min_out_money")
    private BigDecimal minOutMoney;

    private String ctime;

    private String mtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getMinInMoney() {
        return minInMoney;
    }

    public void setMinInMoney(BigDecimal minInMoney) {
        this.minInMoney = minInMoney;
    }

    public BigDecimal getMinOutMoney() {
        return minOutMoney;
    }

    public void setMinOutMoney(BigDecimal minOutMoney) {
        this.minOutMoney = minOutMoney;
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
