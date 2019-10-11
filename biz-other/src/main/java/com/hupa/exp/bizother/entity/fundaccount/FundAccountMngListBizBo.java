package com.hupa.exp.bizother.entity.fundaccount;

import java.util.List;

public class FundAccountMngListBizBo {
    private List<FundAccountMngBizBo> rows;

    private Long total;

    public List<FundAccountMngBizBo> getRows() {
        return rows;
    }

    public void setRows(List<FundAccountMngBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
