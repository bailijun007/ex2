package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

/**
 * Created by Administrator on 2020/2/10.
 */
public class CheckHasBbSymbolOutputDto extends BaseOutputDto {


    @JsonProperty("has_bbSymbol")
    private boolean hasBbSymbol;

    public boolean isHasBbSymbol() {
        return hasBbSymbol;
    }

    public void setHasBbSymbol(boolean hasBbSymbol) {
        this.hasBbSymbol = hasBbSymbol;
    }
}
