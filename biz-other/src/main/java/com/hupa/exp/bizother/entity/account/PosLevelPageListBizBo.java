package com.hupa.exp.bizother.entity.account;

import java.util.List;

public class PosLevelPageListBizBo {
    private List<PcPosLevelBizBo> rows;
    private Long total;

    public List<PcPosLevelBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcPosLevelBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
