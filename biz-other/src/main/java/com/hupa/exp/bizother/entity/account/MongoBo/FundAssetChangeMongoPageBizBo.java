package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;

public class FundAssetChangeMongoPageBizBo {
    private long total;
    private long pageSize;
    private List<FundAssetChangeMongoBizBo> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<FundAssetChangeMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundAssetChangeMongoBizBo> rows) {
        this.rows = rows;
    }
}
