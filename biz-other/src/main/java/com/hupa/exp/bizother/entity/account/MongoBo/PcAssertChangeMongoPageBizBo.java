package com.hupa.exp.bizother.entity.account.MongoBo;

import java.util.List;

public class PcAssertChangeMongoPageBizBo {
    private long total;
    private int pageSize;

    private List<PcAssertChangeMongoBizBo> rows;

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

    public List<PcAssertChangeMongoBizBo> getRows() {
        return rows;
    }

    public void setRows(List<PcAssertChangeMongoBizBo> rows) {
        this.rows = rows;
    }
}
