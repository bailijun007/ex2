package com.hupa.exp.servermng.entity.dictype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DicTypeListInputDto extends BaseInputDto{
    @JsonProperty("current_page")
    private int currentPage;
    @JsonProperty("page_size")
    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
