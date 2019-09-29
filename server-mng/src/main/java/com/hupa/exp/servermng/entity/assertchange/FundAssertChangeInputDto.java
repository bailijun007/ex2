package com.hupa.exp.servermng.entity.assertchange;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class FundAssertChangeInputDto extends BaseInputDto {
   private long id;
    private String symbol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
