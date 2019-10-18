package com.hupa.exp.servermng.entity.pcorder;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.daomongo.enums.MongoSortEnum;

import java.math.BigDecimal;

public class PcOrderPageInputDto extends BaseInputDto {
    private String account;
    private Long orderId;
    private Long accountId;
    private String asset;
    private String symbol;
    private Integer closeFlag;
    private Integer status;
    private long currentPage;
    private int pageSize;
    private MongoSortEnum sortEnum;
    private BigDecimal price;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public Integer getCloseFlag() {
        return closeFlag;
    }

    public void setCloseFlag(Integer closeFlag) {
        this.closeFlag = closeFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public MongoSortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(MongoSortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }
}
