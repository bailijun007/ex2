package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class CheckHasCoinInputDto extends BaseInputDto {
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
