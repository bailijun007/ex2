package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.daomysql.dao.expv2.def.IBbFeeDao;

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
    private Integer currentPage;//pageNo
    /**
     * 页码
     */
    private Integer pageSize;

    /**
     * 审批状态(4:审批中 5:审批通过:6:拒绝)
     */
    private Integer approvalStatus;


    /**
     * 提现状态
     */
    private Integer payStatus;

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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}
