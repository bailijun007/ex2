package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpModuleLimitListBizBo {
    private List<ExpModuleLimitBizBo> rows;
    private Long total;

    public List<ExpModuleLimitBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpModuleLimitBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
