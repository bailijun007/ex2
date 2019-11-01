package com.hupa.exp.servermng.entity.earningrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcEarningRatePageDataInputDto extends BaseInputDto {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("current_page")
    private long currentPage;
    @JsonProperty("page_size")
    private long pageSize;
    private Long rateTime;
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRateTime() {
        return rateTime;
    }

    public void setRateTime(Long rateTime) {
        this.rateTime = rateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
