package com.hupa.exp.servermng.entity.rakeback;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/3/30.
 */
public class RakeBackInputDto extends BaseInputDto {

    private Long id;
    /**
     * 用户的ID
     */
    private String  accountId;

    /**
     * 返佣人的id
     */
    private String rakeBackAccountId;

    /**
     * 返佣人的等级
     */
    private String rakeBackLevel;

    /**
     * 返佣比例
     */
    private BigDecimal proportion;

    /**
     * 返佣金额
     */
    private BigDecimal rakeBackAmt;

    /**
     * 每天原收益额度
     */
    private BigDecimal sourceRakeBackAmt;

    private String collectId;

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
   // private Long  time;
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
    private Long  ctime;


    private Long  mtime;

    private Long pageSize;

    private Long currentPage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

   /* @Override
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }*/

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRakeBackAccountId() {
        return rakeBackAccountId;
    }

    public void setRakeBackAccountId(String rakeBackAccountId) {
        this.rakeBackAccountId = rakeBackAccountId;
    }

    public String getRakeBackLevel() {
        return rakeBackLevel;
    }

    public void setRakeBackLevel(String rakeBackLevel) {
        this.rakeBackLevel = rakeBackLevel;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }
}
