package com.hupa.exp.servermng.entity.tickerlast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcTickerLastPageDataInputDto extends BaseInputDto {
    private String pair;
    @JsonProperty("current_page")
    private long currentPage;
    @JsonProperty("page_size")
    private long pageSize;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
