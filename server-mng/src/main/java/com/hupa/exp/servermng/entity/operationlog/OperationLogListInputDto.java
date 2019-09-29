package com.hupa.exp.servermng.entity.operationlog;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class OperationLogListInputDto extends BaseInputDto {
    private String operationModule;
    private String operationType;
    private Integer currentPage;
    private Integer pageSize;

    public String getOperationModule() {
        return operationModule;
    }

    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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
}
