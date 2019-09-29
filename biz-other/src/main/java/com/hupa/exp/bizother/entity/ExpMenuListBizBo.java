package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpMenuListBizBo  {
    private List<ExpMenuBizBo> rows;
    private long total;

    public List<ExpMenuBizBo> getRows() {

        return rows;
    }

    public void setRows(List<ExpMenuBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
