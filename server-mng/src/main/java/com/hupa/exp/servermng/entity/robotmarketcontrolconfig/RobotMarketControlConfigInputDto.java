package com.hupa.exp.servermng.entity.robotmarketcontrolconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/15.
 */
public class RobotMarketControlConfigInputDto extends BaseInputDto {
    private Long id;
    private String asset;
    private String symbol;
    private Integer pageNo;
    private Integer pageSize;
    private Integer expAreaType;
    private int controlEnable;

    private BigDecimal minSpace;

    private BigDecimal maxSpace;

    private BigDecimal minSwing;

    private BigDecimal maxSwing;

    private BigDecimal minOrderNumber;

    private BigDecimal maxOrderNumber;

    private int minFrequency;
    private int maxFrequency;

    private BigDecimal minBack;
    private BigDecimal maxBack;
    public String getAsset() {
        return asset;
    }

    public BigDecimal getMinBack() {
        return minBack;
    }

    public void setMinBack(BigDecimal minBack) {
        this.minBack = minBack;
    }

    public BigDecimal getMaxBack() {
        return maxBack;
    }

    public void setMaxBack(BigDecimal maxBack) {
        this.maxBack = maxBack;
    }

    public int getControlEnable() {
        return controlEnable;
    }

    public void setControlEnable(int controlEnable) {
        this.controlEnable = controlEnable;
    }

    public BigDecimal getMinSpace() {
        return minSpace;
    }

    public void setMinSpace(BigDecimal minSpace) {
        this.minSpace = minSpace;
    }

    public BigDecimal getMaxSpace() {
        return maxSpace;
    }

    public void setMaxSpace(BigDecimal maxSpace) {
        this.maxSpace = maxSpace;
    }

    public BigDecimal getMinSwing() {
        return minSwing;
    }

    public void setMinSwing(BigDecimal minSwing) {
        this.minSwing = minSwing;
    }

    public BigDecimal getMaxSwing() {
        return maxSwing;
    }

    public void setMaxSwing(BigDecimal maxSwing) {
        this.maxSwing = maxSwing;
    }

    public BigDecimal getMinOrderNumber() {
        return minOrderNumber;
    }

    public void setMinOrderNumber(BigDecimal minOrderNumber) {
        this.minOrderNumber = minOrderNumber;
    }

    public BigDecimal getMaxOrderNumber() {
        return maxOrderNumber;
    }

    public void setMaxOrderNumber(BigDecimal maxOrderNumber) {
        this.maxOrderNumber = maxOrderNumber;
    }

    public int getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(int minFrequency) {
        this.minFrequency = minFrequency;
    }

    public int getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(int maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getExpAreaType() {
        return expAreaType;
    }

    public void setExpAreaType(Integer expAreaType) {
        this.expAreaType = expAreaType;
    }
}
