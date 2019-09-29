package com.hupa.exp.servermng.entity.dic;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DicListInputDto extends BaseInputDto {
    private Integer type;
    private int currentPage;
    private int pageSize;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
