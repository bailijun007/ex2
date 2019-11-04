package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.daomongo.enums.MongoSortEnum;

public class FundWithdrawAccountListInputDto extends BaseInputDto {
    private String asset;
    private Long accountId;
    private Long withdrawTime;
    private Long withdrawId;
    private Integer pageStatus;
    private long currentPage;
    private int pageSize;



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

    public Long getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Long withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
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
