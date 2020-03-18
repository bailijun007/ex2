package com.hupa.exp.servermng.entity.bborder;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/15.
 */
public class BbOrderPageInputDto extends BaseInputDto {

    private String account;

    /**
     * 用户账户Id
     */
    private Long accountId;
    /**
     * 资产类型
     */
    private String asset;
    /**
     * 货币对
     */
    private String symbol;
    /**
     * 委托状态
     */
    private Integer status;
    /**
     * 买卖:1-买,0-卖
     */
    private Integer bidFlag;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 当前页
     */
    private long currentPage;
    /**
     * 每页大小
     */
    private int pageSize;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(Integer bidFlag) {
        this.bidFlag = bidFlag;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
