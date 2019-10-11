package com.hupa.exp.bizother.entity.account;

import java.util.List;

public class StoringLevelPageListBizBo {
    private List<PcStoringLevelBizBo> rows;
    private Long total;

    public List<PcStoringLevelBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcStoringLevelBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
