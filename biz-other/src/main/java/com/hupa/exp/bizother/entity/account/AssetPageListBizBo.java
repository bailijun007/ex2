package com.hupa.exp.bizother.entity.account;

import java.util.List;

public class AssetPageListBizBo {
    private List<AssetBizBo> rows;
    private Long total;

    public List<AssetBizBo> getRows() {
        return rows;
    }

    public void setRows(List<AssetBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
