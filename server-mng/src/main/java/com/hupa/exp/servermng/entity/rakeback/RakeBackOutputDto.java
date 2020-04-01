package com.hupa.exp.servermng.entity.rakeback;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/3/30.
 */
public class RakeBackOutputDto {


    private String id;
    /**
     * 用户的ID
     */
    private Long  accountId;

    /**
     * 返佣人的id
     */
    private Long rakeBackAccountId;

    /**
     * 返佣人的等级
     */
    private Integer rakeBackLevel;

    /**
     * 返佣比例
     */
    private BigDecimal proportion;

    /**
     * 返佣金额
     */
    private BigDecimal rakeBackAmt;

    /**
     * 每天挂单原手续费
     */
    private BigDecimal sourceMakerFee;

    /**
     * 每天吃单原手续费
     */
    private BigDecimal sourceTakeFee;


    /**
     * 每天原收益额度
     */
    private BigDecimal sourceRakeBackAmt;

    private Long collectId;

    /**
     * 返佣原数据
     */
    private String sourceValue;

    /**
     * 返佣类型
     * 合约类型：1
     * 币币类型：2
     */
    private String type;
    /**
     * 返佣时间
     */
     private String time;
    /**
     * 返佣成功或者失败的状态
     * 成功状态1，失败0
     */
    private String state;
    /**
     * 币种
     */
    private String asset;
    /**
     * 货币对
     */
    private String symbol;

    /**
     * 当前BTC的价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private String  ctime;


    private String  mtime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRakeBackAccountId() {
        return rakeBackAccountId;
    }

    public void setRakeBackAccountId(Long rakeBackAccountId) {
        this.rakeBackAccountId = rakeBackAccountId;
    }

    public Integer getRakeBackLevel() {
        return rakeBackLevel;
    }

    public void setRakeBackLevel(Integer rakeBackLevel) {
        this.rakeBackLevel = rakeBackLevel;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public BigDecimal getRakeBackAmt() {
        return rakeBackAmt;
    }

    public void setRakeBackAmt(BigDecimal rakeBackAmt) {
        this.rakeBackAmt = rakeBackAmt;
    }

    public BigDecimal getSourceRakeBackAmt() {
        return sourceRakeBackAmt;
    }

    public void setSourceRakeBackAmt(BigDecimal sourceRakeBackAmt) {
        this.sourceRakeBackAmt = sourceRakeBackAmt;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public BigDecimal getSourceMakerFee() {
        return sourceMakerFee;
    }

    public void setSourceMakerFee(BigDecimal sourceMakerFee) {
        this.sourceMakerFee = sourceMakerFee;
    }

    public BigDecimal getSourceTakeFee() {
        return sourceTakeFee;
    }

    public void setSourceTakeFee(BigDecimal sourceTakeFee) {
        this.sourceTakeFee = sourceTakeFee;
    }
}
