package com.hupa.exp.servermng.entity.symbolinterest;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class SymbolInterestListInputDto extends BaseInputDto {
    private String symbol;
    private Integer pageSize;
    private Integer currentPage;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
