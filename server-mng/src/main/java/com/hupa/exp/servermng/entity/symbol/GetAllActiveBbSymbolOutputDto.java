package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.servermng.entity.contract.GetContractOutputDto;

import java.util.List;

/**
 * Created by Administrator on 2020/2/10.
 */
public class GetAllActiveBbSymbolOutputDto extends BaseOutputDto {

    @JsonProperty("active_bbSymbol")
    private List<GetBbSymbolOutputDto> activeBbSymbol;

    public List<GetBbSymbolOutputDto> getActiveBbSymbol() {
        return activeBbSymbol;
    }

    public void setActiveBbSymbol(List<GetBbSymbolOutputDto> activeBbSymbol) {
        this.activeBbSymbol = activeBbSymbol;
    }
}
