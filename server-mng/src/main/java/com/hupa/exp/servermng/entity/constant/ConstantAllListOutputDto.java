package com.hupa.exp.servermng.entity.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class ConstantAllListOutputDto extends BaseOutputDto {
    @JsonProperty("constant_list")
    List<ConstantInfoOutputDto> constantList;

    public List<ConstantInfoOutputDto> getConstantList() {
        return constantList;
    }

    public void setConstantList(List<ConstantInfoOutputDto> constantList) {
        this.constantList = constantList;
    }
}
