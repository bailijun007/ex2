package com.hupa.exp.servermng.entity.pcorder;

import com.hupa.exp.base.entity.bo.pc.PcOrderBo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class PcOrderInfo implements Serializable   { //extends PcOrderBo

    /**
     * 委托id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 委托状态
     */
    private Integer status;
    /**
     * 手续费
     */
    private BigDecimal fee;
    /**
     * 委托价
     */
    private BigDecimal price;
    /**
     * 量
     */
    private BigDecimal qty;
    /**
     * 是否：1-多仓，0-空仓
     */
    private Integer longFlag;
    /**
     * 杠杆
     */
    private BigDecimal leverage;
    /**
     * 资产
     */
    private String asset;
    /**
     * 交易对
     */
    private String symol;
    /**
     * 委托创建时间
     */
    private Long ctime;
    /**
     * 平均价
     */
    private BigDecimal avgPrice;
    /**
     * 已成交量
     */
    private BigDecimal filledQty;
    /**
     * 是否:1-平仓,0-开
     */
    private BigDecimal closeFlag;
    /**
     * 成交比例
     */
    private BigDecimal tradeRatio;
    /**
     * 委托保证金
     */
    private BigDecimal orderMargin;
    /**
     * 委托类型
     */
    private Integer orderType;
    /**
     * 收益
     */
    private BigDecimal realisedPnl;
    /**
     * 客户端id
     */
    private String clientOid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Integer getLongFlag() {
        return longFlag;
    }

    public void setLongFlag(Integer longFlag) {
        this.longFlag = longFlag;
    }

    public BigDecimal getLeverage() {
        return leverage;
    }

    public void setLeverage(BigDecimal leverage) {
        this.leverage = leverage;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymol() {
        return symol;
    }

    public void setSymol(String symol) {
        this.symol = symol;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getFilledQty() {
        return filledQty;
    }

    public void setFilledQty(BigDecimal filledQty) {
        this.filledQty = filledQty;
    }

    public BigDecimal getCloseFlag() {
        return closeFlag;
    }

    public void setCloseFlag(BigDecimal closeFlag) {
        this.closeFlag = closeFlag;
    }

    public BigDecimal getTradeRatio() {
        return tradeRatio;
    }

    public void setTradeRatio(BigDecimal tradeRatio) {
        this.tradeRatio = tradeRatio;
    }

    public BigDecimal getOrderMargin() {
        return orderMargin;
    }

    public void setOrderMargin(BigDecimal orderMargin) {
        this.orderMargin = orderMargin;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getRealisedPnl() {
        return realisedPnl;
    }

    public void setRealisedPnl(BigDecimal realisedPnl) {
        this.realisedPnl = realisedPnl;
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }
}
