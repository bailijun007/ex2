package com.hupa.exp.servermng.entity.modulelimit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.bizother.entity.ExpModuleLimitListBizBo;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ModuleLimitListInputDto extends BaseInputDto {
    private Integer pageSize;
    private Integer currentPage;

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
