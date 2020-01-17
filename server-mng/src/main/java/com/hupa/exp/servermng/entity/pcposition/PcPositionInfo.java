package com.hupa.exp.servermng.entity.pcposition;

import java.io.Serializable;
import java.math.BigDecimal;

public class PcPositionInfo implements Serializable { //extends PcPositionBo

    /**
     * 仓位id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 资产 币种
     */
    private String asset;
    /**
     * 货币对
     */
    private String symbol;
    /**
     * 1:全仓,2:逐仓
     */
    private Integer marginMode;
    /**
     * 可平数量
     */
    private BigDecimal availQty;
    /**
     * 平均开仓价
     */
    private BigDecimal entryPrice;
    /**
     * 杠杆
     */
    private BigDecimal leverage;
    /**
     * 预估强平价
     */
    private BigDecimal liquidationPrice;
    /**
     * 仓位保证金
     */
    private BigDecimal posMargin;
    /**
     * 保证金率
     */
    private BigDecimal posMarginRatio;
    /**
     * 维持保证金率
     */
    private BigDecimal maintMarginRatio;
    /**
     * 持仓量
     */
    private BigDecimal qty;
    /**
     * 收益率
     */
    private BigDecimal posPnlRatio;
    /**
     * 已实现盈亏
     */
    private BigDecimal realisedPnl;
    /**
     * 未实现盈亏
     */
    private BigDecimal pnl;
    /**
     * 1多,0空
     */
    private Integer bidFlag;
    /**
     * 打开自动追加保证金,0.关闭
     */
    private Integer autoIncreaseFlag;
    /**
     * 开仓时间
     */
    private Long ctime;

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

    public Integer getMarginMode() {
        return marginMode;
    }

    public void setMarginMode(Integer marginMode) {
        this.marginMode = marginMode;
    }

    public BigDecimal getAvailQty() {
        return availQty;
    }

    public void setAvailQty(BigDecimal availQty) {
        this.availQty = availQty;
    }

    public BigDecimal getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(BigDecimal entryPrice) {
        this.entryPrice = entryPrice;
    }

    public BigDecimal getLeverage() {
        return leverage;
    }

    public void setLeverage(BigDecimal leverage) {
        this.leverage = leverage;
    }

    public BigDecimal getLiquidationPrice() {
        return liquidationPrice;
    }

    public void setLiquidationPrice(BigDecimal liquidationPrice) {
        this.liquidationPrice = liquidationPrice;
    }

    public BigDecimal getPosMargin() {
        return posMargin;
    }

    public void setPosMargin(BigDecimal posMargin) {
        this.posMargin = posMargin;
    }

    public BigDecimal getPosMarginRatio() {
        return posMarginRatio;
    }

    public void setPosMarginRatio(BigDecimal posMarginRatio) {
        this.posMarginRatio = posMarginRatio;
    }

    public BigDecimal getMaintMarginRatio() {
        return maintMarginRatio;
    }

    public void setMaintMarginRatio(BigDecimal maintMarginRatio) {
        this.maintMarginRatio = maintMarginRatio;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPosPnlRatio() {
        return posPnlRatio;
    }

    public void setPosPnlRatio(BigDecimal posPnlRatio) {
        this.posPnlRatio = posPnlRatio;
    }

    public BigDecimal getRealisedPnl() {
        return realisedPnl;
    }

    public void setRealisedPnl(BigDecimal realisedPnl) {
        this.realisedPnl = realisedPnl;
    }

    public BigDecimal getPnl() {
        return pnl;
    }

    public void setPnl(BigDecimal pnl) {
        this.pnl = pnl;
    }

    public Integer getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(Integer bidFlag) {
        this.bidFlag = bidFlag;
    }

    public Integer getAutoIncreaseFlag() {
        return autoIncreaseFlag;
    }

    public void setAutoIncreaseFlag(Integer autoIncreaseFlag) {
        this.autoIncreaseFlag = autoIncreaseFlag;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}
