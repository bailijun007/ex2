package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;

public class FundAssertChangeMongoPageBizBo {
    private long total;
    private long pageSize;
    private List<FundAssertChangeMongoBizBo> rows;

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

    public List<FundAssertChangeMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundAssertChangeMongoBizBo> rows) {
        this.rows = rows;
    }
}
