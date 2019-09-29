package com.hupa.exp.servermng.entity.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckHasLastPriceOutputDto extends BaseOutputDto {
    @JsonProperty("has_last_price")
    private boolean hasLastPrice;

    public boolean isHasLastPrice() {
        return hasLastPrice;
    }

    public void setHasLastPrice(boolean hasLastPrice) {
        this.hasLastPrice = hasLastPrice;
    }
}
