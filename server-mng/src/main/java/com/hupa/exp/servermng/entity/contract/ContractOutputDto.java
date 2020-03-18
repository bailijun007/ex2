package com.hupa.exp.servermng.entity.contract;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class ContractOutputDto extends BaseOutputDto {
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
