package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class FundWithdrawAccountListInputDto extends BaseInputDto {

    /**
     * 币种
     */
    private String asset;
    /**
     * 账户Id
     */
    private Long accountId;
    /**
     * 每页显示记录数
     */
    private long currentPage;
    /**
     * 页码
     */
    private int pageSize;

    /**
     * 审批状态(4:审批中 5:审批通过:6:拒绝)
     */
    private Integer approvalStatus;

    /* private Long withdrawTime;
    private Long withdrawId;
    private Integer pageStatus*/;



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

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /*
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
    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }*/




}
