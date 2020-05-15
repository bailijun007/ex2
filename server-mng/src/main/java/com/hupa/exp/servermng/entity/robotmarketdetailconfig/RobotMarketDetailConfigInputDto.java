package com.hupa.exp.servermng.entity.robotmarketdetailconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/15.
 */
public class RobotMarketDetailConfigInputDto extends BaseInputDto {
    private Long id;
    private String asset;
    private String symbol;
    private Integer pageNo;
    private Integer pageSize;
    private Integer expAreaType;
    private Long bidUserId;

    private Long askUserId;


    private BigDecimal minOrderNumber;

    private BigDecimal maxOrderNumber;

    private Integer maxOrderBookSize;

    private String orderBookBidNumber;

    private String orderBookAskNumber;

    private Integer  marketEnable;

    private Integer minFrequency;

    private Integer maxFrequency;

    public String getAsset() {
        return asset;
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
