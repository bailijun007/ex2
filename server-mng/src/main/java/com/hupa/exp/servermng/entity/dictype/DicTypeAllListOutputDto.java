package com.hupa.exp.servermng.entity.dictype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class DicTypeAllListOutputDto extends BaseOutputDto {
    @JsonProperty("dic_type_list")
    List<DicTypeInfoOutputDto> dicTypeList;

    public List<DicTypeInfoOutputDto> getDicTypeList() {
        return dicTypeList;
    }

    public void setDicTypeList(List<DicTypeInfoOutputDto> dicTypeList) {
        this.dicTypeList = dicTypeList;
    }
}
