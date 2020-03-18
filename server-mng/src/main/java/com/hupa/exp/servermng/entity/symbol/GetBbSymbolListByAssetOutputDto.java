package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.servermng.entity.contract.GetContractOutputDto;

import java.util.List;

/**
 * Created by Administrator on 2020/2/10.
 */
public class GetBbSymbolListByAssetOutputDto  extends BaseOutputDto {
    @JsonProperty("asset_symbol_list")
    private List<GetBbSymbolOutputDto> assetSymbolList;


    public List<GetBbSymbolOutputDto> getAssetSymbolList() {
        return assetSymbolList;
    }

    public void setAssetSymbolList(List<GetBbSymbolOutputDto> assetSymbolList) {
        this.assetSymbolList = assetSymbolList;
    }
}

