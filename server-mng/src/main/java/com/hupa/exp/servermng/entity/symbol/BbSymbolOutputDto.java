package com.hupa.exp.servermng.entity.symbol;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolOutputDto  extends BaseOutputDto {

    private long id;

    private int number;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
