package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpInformationPageDataBizBo {
    private List<ExpInformationBizBo> rows;

    private long total;

    public List<ExpInformationBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpInformationBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
