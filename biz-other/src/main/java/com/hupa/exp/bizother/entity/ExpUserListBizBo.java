package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpUserListBizBo {
    private List<ExpUserBizBo> rows;

    private Long total;

    public List<ExpUserBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpUserBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
