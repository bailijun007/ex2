package com.hupa.exp.servermng.entity.mongodb;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class MongoDataInputDto extends BaseInputDto {
    private String collectionName;
    private Integer pageSize;
    private Integer currentPage;
    private String queryStr;

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
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
