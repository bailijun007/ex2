package com.hupa.exp.servermng.entity.tickerlast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;
import com.hupa.exp.daomysql.entity.po.expv2.ThirdTickerLastDataPo;

public class PcTickerLastPageDataOutputDto extends BasePageOutputDto<ThirdTickerLastDataPo> {
    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
