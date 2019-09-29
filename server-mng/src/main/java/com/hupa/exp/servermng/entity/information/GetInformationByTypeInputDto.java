package com.hupa.exp.servermng.entity.information;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetInformationByTypeInputDto extends BaseInputDto {
    private String title;
    private Integer type;
    private int currentPage;
    private int pageSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
