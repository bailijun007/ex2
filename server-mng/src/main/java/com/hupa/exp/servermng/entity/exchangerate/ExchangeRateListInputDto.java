package com.hupa.exp.servermng.entity.exchangerate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ExchangeRateListInputDto extends BaseInputDto {
    private long pageSize;
    private long currentPage;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
