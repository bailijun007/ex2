package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpLocaleListBizBo {
    private List<ExpLocaleBizBo> rows;
    private long total;

    public List<ExpLocaleBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpLocaleBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
