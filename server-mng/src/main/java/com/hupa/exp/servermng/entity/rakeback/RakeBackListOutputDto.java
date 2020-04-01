package com.hupa.exp.servermng.entity.rakeback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;
import com.hupa.exp.servermng.entity.symbol.BbSymbolListOutputPage;

/**
 * Created by Administrator on 2020/3/30.
 */
public class RakeBackListOutputDto extends BasePageOutputDto<RakeBackOutputDto> {

    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
