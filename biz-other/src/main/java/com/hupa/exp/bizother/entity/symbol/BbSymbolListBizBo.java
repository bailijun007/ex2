package com.hupa.exp.bizother.entity.symbol;

import java.util.List;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolListBizBo {

    private List<BbSymbolBizBo> rows;

    private long total;

    public List<BbSymbolBizBo> getRows() {
        return rows;
    }

    public void setRows(List<BbSymbolBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
