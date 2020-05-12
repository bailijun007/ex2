package com.hupa.exp.servermng.entity.bbtransfer;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

/**
 * Created by Administrator on 2020/2/15.
 */
public class BbTransferListInputDto extends BaseInputDto {

    private String asset;
    private Long accountId;
    private Long transferTime;
    private Long transferId;
    private String statrTime;
    private String endTime;
    private long currentPage;
    private int pageSize;
    private Integer pageStatus;

    public String getStatrTime() {
        return statrTime;
    }

    public void setStatrTime(String statrTime) {
        this.statrTime = statrTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
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
}
