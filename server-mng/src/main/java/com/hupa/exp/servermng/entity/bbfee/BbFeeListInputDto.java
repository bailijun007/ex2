package com.hupa.exp.servermng.entity.bbfee;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

/**
 * Created by Administrator on 2020/2/6.
 */
public class BbFeeListInputDto extends BaseInputDto {

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
}
