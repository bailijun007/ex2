package com.hupa.exp.servermng.entity.user;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class CreateAccountInputDto extends BaseInputDto {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
