package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcAssetChangeListInputDto extends BaseInputDto {
    private String symbol;
    private Long id;
    private long currentPage;

    //private long pageSize;



    private int pageSize;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

//    public long getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(long pageSize) {
//        this.pageSize = pageSize;
//    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
