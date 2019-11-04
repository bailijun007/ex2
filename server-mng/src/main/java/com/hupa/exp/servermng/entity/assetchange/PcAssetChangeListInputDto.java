package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcAssetChangeListInputDto extends BaseInputDto {
    private String asset;
    private Long accountId;
    private Long id;
    private Integer pageStatus;
    private long currentPage;

    //private long pageSize;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    private int pageSize;

    public String getAsset() {
        return asset;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

//    public long getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(long pageSize) {
//        this.pageSize = pageSize;
//    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
