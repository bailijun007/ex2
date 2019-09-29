package com.hupa.exp.servermng.entity.user;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class EditFundAccountInputDto extends BaseInputDto {

    private long id;
    private String funds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }
}
