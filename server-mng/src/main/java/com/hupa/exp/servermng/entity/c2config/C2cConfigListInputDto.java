package com.hupa.exp.servermng.entity.c2config;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

/**
 * Created by Administrator on 2020/1/10.
 */
public class C2cConfigListInputDto  extends BaseInputDto {

    private long id;
    /**
     * 币种
     */
    private String asset;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 当前第几页
     */
    private Integer currentPage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
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
