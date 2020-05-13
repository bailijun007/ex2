package com.hupa.exp.servermng.entity.robotmarketdetailconfig;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author BaiLiJun  on 2020/5/12
 */
public class RobotMarketDetailConfigInfo implements Serializable {
    private Long id;

    private Long bidUserId;

    private Long askUserId;

    private String asset;

    private String symbol;

    private Integer expAreaType;

    private BigDecimal minOrderNumber;

    private BigDecimal maxOrderNumber;

    private Integer maxOrderBookSize;

    private String orderBookBidNumber;

    private String orderBookAskNumber;

    private Integer  marketEnable;

    private Integer minFrequency;

    private Integer maxFrequency;

    private Long ctime;

    private Long mtime;

    public RobotMarketDetailConfigInfo() {
    }

    @Override
    public String toString() {
        return "RobotMarketDetailConfigInfo{" +
                "id=" + id +
                ", bidUserId=" + bidUserId +
                ", askUserId=" + askUserId +
                ", asset='" + asset + '\'' +
                ", symbol='" + symbol + '\'' +
                ", expAreaType=" + expAreaType +
                ", minOrderNumber=" + minOrderNumber +
                ", maxOrderNumber=" + maxOrderNumber +
                ", maxOrderBookSize=" + maxOrderBookSize +
                ", orderBookBidNumber='" + orderBookBidNumber + '\'' +
                ", orderBookAskNumber='" + orderBookAskNumber + '\'' +
                ", marketEnable=" + marketEnable +
                ", minFrequency=" + minFrequency +
                ", maxFrequency=" + maxFrequency +
                ", ctime=" + ctime +
                ", mtime=" + mtime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBidUserId() {
        return bidUserId;
    }

    public void setBidUserId(Long bidUserId) {
        this.bidUserId = bidUserId;
    }

    public Long getAskUserId() {
        return askUserId;
    }

    public void setAskUserId(Long askUserId) {
        this.askUserId = askUserId;
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

    public Integer getExpAreaType() {
        return expAreaType;
    }

    public void setExpAreaType(Integer expAreaType) {
        this.expAreaType = expAreaType;
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

    public Integer getMaxOrderBookSize() {
        return maxOrderBookSize;
    }

    public void setMaxOrderBookSize(Integer maxOrderBookSize) {
        this.maxOrderBookSize = maxOrderBookSize;
    }

    public String getOrderBookBidNumber() {
        return orderBookBidNumber;
    }

    public void setOrderBookBidNumber(String orderBookBidNumber) {
        this.orderBookBidNumber = orderBookBidNumber;
    }

    public String getOrderBookAskNumber() {
        return orderBookAskNumber;
    }

    public void setOrderBookAskNumber(String orderBookAskNumber) {
        this.orderBookAskNumber = orderBookAskNumber;
    }

    public Integer getMarketEnable() {
        return marketEnable;
    }

    public void setMarketEnable(Integer marketEnable) {
        this.marketEnable = marketEnable;
    }

    public Integer getMinFrequency() {
        return minFrequency;
    }

    public void setMinFrequency(Integer minFrequency) {
        this.minFrequency = minFrequency;
    }

    public Integer getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(Integer maxFrequency) {
        this.maxFrequency = maxFrequency;
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
