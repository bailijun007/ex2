package com.hupa.exp.servermng.entity.candle;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcCandleStatisticsInputDto extends BaseInputDto {
    private String asset;
    private String symbol;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
