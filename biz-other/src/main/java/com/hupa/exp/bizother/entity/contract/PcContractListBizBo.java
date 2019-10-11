package com.hupa.exp.bizother.entity.contract;

import java.util.List;

public class PcContractListBizBo {
    private List<PcContractBizBo> rows;
    private long total;

    public List<PcContractBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcContractBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
