package com.hupa.exp.bizother.entity.account;

import java.util.List;

public class PcFeeListBizBo {
    private List<PcFeeBizBo> rows;

    private long total;

    public List<PcFeeBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcFeeBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
