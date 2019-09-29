package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpSmsListBizBo {
    private List<ExpSmsTempBizBo> rows;
    private Long total;

    public List<ExpSmsTempBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpSmsTempBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
