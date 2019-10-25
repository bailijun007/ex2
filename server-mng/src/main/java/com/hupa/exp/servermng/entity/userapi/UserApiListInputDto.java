package com.hupa.exp.servermng.entity.userapi;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class UserApiListInputDto extends BaseInputDto {
    private String userName;
    private Long accountId;
    private Integer pageSize;
    private Integer currentPage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
