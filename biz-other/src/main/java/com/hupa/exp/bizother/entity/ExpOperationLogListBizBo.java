package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpOperationLogListBizBo {
    private List<ExpOperationLogBizBo> rows;
    private long total;

    public List<ExpOperationLogBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpOperationLogBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
