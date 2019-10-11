package com.hupa.exp.bizother.entity.role;

import java.util.List;

public class ExpRoleListBizBo {
    private List<ExpRoleBizBo> rows;
    private long total;

    public List<ExpRoleBizBo> getRows() {
        return rows;
    }

    public void setRows(List<ExpRoleBizBo> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
