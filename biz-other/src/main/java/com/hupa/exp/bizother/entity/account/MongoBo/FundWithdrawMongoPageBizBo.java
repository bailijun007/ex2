package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;


public class FundWithdrawMongoPageBizBo {
    private long total;
    private long pageSize;
    private List<FundWithdrawMongoBizBo> rows;

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

    public List<FundWithdrawMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundWithdrawMongoBizBo> rows) {
        this.rows = rows;
    }
}
