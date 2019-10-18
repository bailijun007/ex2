package com.hupa.exp.servermng.entity.asset;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class AssetListInputDto extends BaseInputDto {
    private String realName;
    private Integer pageSize;
    private Integer currentPage;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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
