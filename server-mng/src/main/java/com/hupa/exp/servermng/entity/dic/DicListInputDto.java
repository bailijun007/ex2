package com.hupa.exp.servermng.entity.dic;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DicListInputDto extends BaseInputDto {
    private Integer parentId;
    private int currentPage;
    private int pageSize;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
