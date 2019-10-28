package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;

public class PcAssetChangeMongoPageBizBo {
    private long total;
    private int pageSize;

    private List<PcAssetChangeMongoBizBo> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<PcAssetChangeMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcAssetChangeMongoBizBo> rows) {
        this.rows = rows;
    }
}
