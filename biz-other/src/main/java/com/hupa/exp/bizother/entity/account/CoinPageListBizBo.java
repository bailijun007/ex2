package com.hupa.exp.bizother.entity.account;

import java.util.List;

public class CoinPageListBizBo {
    private List<CoinBizBo> rows;
    private Long total;

    public List<CoinBizBo> getRows() {
        return rows;
    }

    public void setRows(List<CoinBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
