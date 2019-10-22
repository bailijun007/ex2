package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.daomongo.enums.MongoSortEnum;

public class FundWithdrawAccountListInputDto extends BaseInputDto {
    private Long accountId;
    private Long startTime;
    private Long endTime;
    private long currentPage;
    private int pageSize;
    private Integer pageStatus;
    private MongoSortEnum sortEnum;



    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public MongoSortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(MongoSortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }
}
