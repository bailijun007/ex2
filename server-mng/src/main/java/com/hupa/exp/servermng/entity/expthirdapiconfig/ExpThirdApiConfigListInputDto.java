package com.hupa.exp.servermng.entity.expthirdapiconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ExpThirdApiConfigListInputDto extends BaseInputDto {
    private Integer currentPage;
    private Integer pageSize;

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
