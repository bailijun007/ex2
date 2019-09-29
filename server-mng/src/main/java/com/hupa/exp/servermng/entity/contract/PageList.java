package com.hupa.exp.servermng.entity.contract;

import com.hupa.exp.bizpccontract.entity.PcContractBizBo;

import java.util.List;

public class PageList {
    public List<PcContractBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcContractBizBo> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private List<PcContractBizBo> rows;
    private Integer total;
}
