package com.hupa.exp.servermng.entity.klineconfig;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

/**
 * Created by Administrator on 2020/3/17.
 */
public class RepairKlineListOutputDto extends BaseOutputDto {

    private int total;

    private List<RepairKlineOutputDto> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RepairKlineOutputDto> getRows() {
        return rows;
    }

    public void setRows(List<RepairKlineOutputDto> rows) {
        this.rows = rows;
    }
}
