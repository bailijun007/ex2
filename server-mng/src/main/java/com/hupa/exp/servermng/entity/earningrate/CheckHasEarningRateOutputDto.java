package com.hupa.exp.servermng.entity.earningrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckHasEarningRateOutputDto extends BaseOutputDto {
    @JsonProperty("has_earning_rate")
    private boolean hasEarningRate;

    public boolean isHasEarningRate() {
        return hasEarningRate;
    }

    public void setHasEarningRate(boolean hasEarningRate) {
        this.hasEarningRate = hasEarningRate;
    }
}
