package com.hupa.exp.servermng.entity.appversion;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class AppVersionListInputDto extends BaseInputDto{
    private Integer type;
    private Integer currentPage;
    private Integer pageSize;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
