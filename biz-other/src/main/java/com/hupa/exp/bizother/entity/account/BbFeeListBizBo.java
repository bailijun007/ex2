package com.hupa.exp.bizother.entity.account;

import java.util.List;

/**
 * Created by Administrator on 2020/2/6.
 */
public class BbFeeListBizBo {

    private List<BbFeeBizBo> rows;

    private long total;

    public List<BbFeeBizBo> getRows() {
        return rows;
    }

    public void setRows(List<BbFeeBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
