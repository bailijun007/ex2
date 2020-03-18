package com.hupa.exp.servermng.entity.bbfee;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/6.
 */
public class BbFeeInputDto extends BaseInputDto {

    private Long id;
    private Integer tier;
    private String compare;
    private BigDecimal tradingVolume;
    private BigDecimal makerFee;
    private BigDecimal takerFee;
    private BigDecimal withdrawLimit;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public BigDecimal getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(BigDecimal tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public BigDecimal getMakerFee() {
        return makerFee;
    }

    public void setMakerFee(BigDecimal makerFee) {
        this.makerFee = makerFee;
    }

    public BigDecimal getTakerFee() {
        return takerFee;
    }

    public void setTakerFee(BigDecimal takerFee) {
        this.takerFee = takerFee;
    }

    public BigDecimal getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(BigDecimal withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
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
