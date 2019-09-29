package com.hupa.exp.servermng.entity.coin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckHasCoinOutputDto extends BaseOutputDto {
@JsonProperty("has_coin")
    private boolean hasCoin;

    public boolean isHasCoin() {
        return hasCoin;
    }

    public void setHasCoin(boolean hasCoin) {
        this.hasCoin = hasCoin;
    }
}
