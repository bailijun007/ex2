package com.hupa.exp.servermng.entity.dic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class DicAllListOutputDto extends BaseOutputDto{
    @JsonProperty("dic_list")
    List<DicInfoOutputDto> dicList;

    public List<DicInfoOutputDto> getDicList() {
        return dicList;
    }

    public void setDicList(List<DicInfoOutputDto> dicList) {
        this.dicList = dicList;
    }
}
