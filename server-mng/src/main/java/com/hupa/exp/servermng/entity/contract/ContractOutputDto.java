package com.hupa.exp.servermng.entity.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class ContractOutputDto extends BaseOutputDto {
    private long id;

    private int number;

    @JsonProperty("asset_symbol_list")
    private List<GetContractOutputDto> assetSymbolList;

    @JsonProperty("symbol_list")
    private List<String> symbolList;

    @JsonProperty("active_contract")
    private List<GetContractOutputDto> activeContract;

    @JsonProperty("has_contract")
    private boolean hasContract;


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

    public List<GetContractOutputDto> getAssetSymbolList() {
        return assetSymbolList;
    }

    public void setAssetSymbolList(List<GetContractOutputDto> assetSymbolList) {
        this.assetSymbolList = assetSymbolList;
    }
    public List<String> getSymbolList() {
        return symbolList;
    }

    public void setSymbolList(List<String> symbolList) {
        this.symbolList = symbolList;
    }

    public List<GetContractOutputDto> getActiveContract() {
        return activeContract;
    }

    public void setActiveContract(List<GetContractOutputDto> activeContract) {
        this.activeContract = activeContract;
    }

    public boolean isHasContract() {
        return hasContract;
    }

    public void setHasContract(boolean hasContract) {
        this.hasContract = hasContract;
    }
}
