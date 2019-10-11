package com.hupa.exp.bizother.entity.area;

import java.util.List;

public class ExpAreaListBizBo {
    private List<ExpAreaBizBo> rows;

    private long total;

    public List<ExpAreaBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpAreaBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
