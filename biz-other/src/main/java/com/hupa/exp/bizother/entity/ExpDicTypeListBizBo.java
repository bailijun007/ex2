package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpDicTypeListBizBo {
    private List<ExpDicTypeBizBo> rows;
    private long total;


    public List<ExpDicTypeBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpDicTypeBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
