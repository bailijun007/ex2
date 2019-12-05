package com.hupa.exp.servermng.entity.constant;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ConstantListInputDto extends BaseInputDto {
    private String key;
    private Long parentId;
    private Long currentPage;
    private Long pageSize;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
