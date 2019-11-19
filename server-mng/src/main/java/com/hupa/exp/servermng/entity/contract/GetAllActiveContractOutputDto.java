package com.hupa.exp.servermng.entity.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class GetAllActiveContractOutputDto extends BaseOutputDto {
    @JsonProperty("active_contract")
   private List<GetContractOutputDto> activeContract;

    public List<GetContractOutputDto> getActiveContract() {
        return activeContract;
    }

    public void setActiveContract(List<GetContractOutputDto> activeContract) {
        this.activeContract = activeContract;
    }
}
