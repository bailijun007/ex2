package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;

public class FundAccountLogMongoPageBizBo {
    private long total;
    private long pageSize;
    private List<FundAccountLogMongoBizBo> rows;

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

    public List<FundAccountLogMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundAccountLogMongoBizBo> rows) {
        this.rows = rows;
    }
}
