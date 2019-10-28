package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class FundAssetChangeInputDto extends BaseInputDto {
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
