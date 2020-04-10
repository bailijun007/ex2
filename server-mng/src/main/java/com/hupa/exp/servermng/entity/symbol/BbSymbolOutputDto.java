package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolOutputDto  extends BaseOutputDto {

    private long id;

    private int number;

    @JsonProperty("has_bbSymbol")
    private boolean hasBbSymbol;

    @JsonProperty("asset_symbol_list")
    private List<GetBbSymbolOutputDto> assetSymbolList;

    @JsonProperty("symbol_list")
    private List<String> symbolList;

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

    public boolean isHasBbSymbol() {
        return hasBbSymbol;
    }

    public void setHasBbSymbol(boolean hasBbSymbol) {
        this.hasBbSymbol = hasBbSymbol;
    }

    public List<GetBbSymbolOutputDto> getAssetSymbolList() {
        return assetSymbolList;
    }

    public void setAssetSymbolList(List<GetBbSymbolOutputDto> assetSymbolList) {
        this.assetSymbolList = assetSymbolList;
    }

    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }
}
