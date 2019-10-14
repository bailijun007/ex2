package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class CheckHasCoinInputDto extends BaseInputDto {
    private String coinName;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }
}
