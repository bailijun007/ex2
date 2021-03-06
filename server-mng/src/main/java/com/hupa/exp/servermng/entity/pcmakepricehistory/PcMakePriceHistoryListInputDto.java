package com.hupa.exp.servermng.entity.pcmakepricehistory;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcMakePriceHistoryListInputDto extends BaseInputDto {
    private String year;
    private String asset;
    private String symbol;
    private Integer currentPage;
    private Integer pageSize;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
