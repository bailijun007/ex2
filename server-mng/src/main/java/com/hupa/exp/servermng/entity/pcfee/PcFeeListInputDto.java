package com.hupa.exp.servermng.entity.pcfee;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcFeeListInputDto extends BaseInputDto {
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
