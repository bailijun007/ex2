package com.hupa.exp.bizother.entity.dic;

import java.util.List;

public class ExpDicListBizBo {
    private List<ExpDicBizBo> rows;

    private long total;

    public List<ExpDicBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpDicBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
