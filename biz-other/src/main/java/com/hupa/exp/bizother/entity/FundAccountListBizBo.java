package com.hupa.exp.bizother.entity;

import java.util.List;

public class FundAccountListBizBo {
    private List<FundAccountBizBo> rows;

    private Long total;

    public List<FundAccountBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundAccountBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
