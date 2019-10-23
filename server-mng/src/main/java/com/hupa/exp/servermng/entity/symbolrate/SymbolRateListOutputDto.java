package com.hupa.exp.servermng.entity.symbolrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;

public class SymbolRateListOutputDto extends BasePageOutputDto<SymbolRateInfoOutputDto> {
    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
