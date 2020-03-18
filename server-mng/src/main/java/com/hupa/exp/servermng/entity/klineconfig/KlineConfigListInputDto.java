package com.hupa.exp.servermng.entity.klineconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class KlineConfigListInputDto extends BaseInputDto {
    private Integer pageSize;
    private Integer currentPage;
    private Integer klineType;

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

    public Integer getKlineType() {
        return klineType;
    }

    public void setKlineType(Integer klineType) {
        this.klineType = klineType;
    }
}
