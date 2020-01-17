package com.hupa.exp.servermng.entity.c2config;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/1/13.
 */
public class C2cConfigInputDto extends BaseInputDto {

    /**
     *  主键id
     */
    private long id;

    /**
     * 币种
     */
    private String asset;

    private BigDecimal minInMoney;

    private BigDecimal minOutMoney;

    /**
     * 创建时间
     */
    private long ctime;
    /**
     * 修改时间
     */
    private long mtime;

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

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }
}
