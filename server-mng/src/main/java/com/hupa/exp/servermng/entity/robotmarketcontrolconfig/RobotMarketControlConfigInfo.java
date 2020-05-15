package com.hupa.exp.servermng.entity.robotmarketcontrolconfig;

import java.io.Serializable;
import java.math.BigDecimal;


public class RobotMarketControlConfigInfo implements Serializable {
    private Long id;


    private String asset;

    private String symbol;

    private int controlEnable;

    private BigDecimal minSpace;

    private BigDecimal maxSpace;

    private BigDecimal minSwing;

    private BigDecimal maxSwing;

    private BigDecimal minOrderNumber;

    private BigDecimal maxOrderNumber;

    private int minFrequency;
    private int maxFrequency;
    private long ctime;

    private long mtime;
    private int expAreaType;

    private BigDecimal minBack;

    private BigDecimal maxBack;


    public RobotMarketControlConfigInfo() {
    }

    @Override
    public String toString() {
        return "RobotMarketControlConfigInfo{" +
                "id=" + id +
                ", asset='" + asset + '\'' +
                ", symbol='" + symbol + '\'' +
                ", controlEnable=" + controlEnable +
                ", minSpace=" + minSpace +
                ", maxSpace=" + maxSpace +
                ", minSwing=" + minSwing +
                ", maxSwing=" + maxSwing +
                ", minOrderNumber=" + minOrderNumber +
                ", maxOrderNumber=" + maxOrderNumber +
                ", minFrequency=" + minFrequency +
                ", maxFrequency=" + maxFrequency +
                ", ctime=" + ctime +
                ", mtime=" + mtime +
                ", expAreaType=" + expAreaType +
                ", minBack=" + minBack +
                ", maxBack=" + maxBack +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
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

    public int getExpAreaType() {
        return expAreaType;
    }

    public void setExpAreaType(int expAreaType) {
        this.expAreaType = expAreaType;
    }
}
