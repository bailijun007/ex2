package com.hupa.exp.servermng.entity.locale;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class LocaleListInputDto extends BaseInputDto {
   private String module;
   private Integer errorCode;
   private Integer currentPage;
   private Integer pageSize;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
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
