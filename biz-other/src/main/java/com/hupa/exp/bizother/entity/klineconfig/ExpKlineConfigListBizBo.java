package com.hupa.exp.bizother.entity.klineconfig;

import java.util.List;

public class ExpKlineConfigListBizBo {
   private List<ExpKlineConfigBizBo> rows;
   private Long total;

    public List<ExpKlineConfigBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpKlineConfigBizBo> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
