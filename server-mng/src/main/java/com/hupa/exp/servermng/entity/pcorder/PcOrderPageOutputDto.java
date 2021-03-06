package com.hupa.exp.servermng.entity.pcorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.base.entity.bo.pc.PcOrderBo;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;

public class PcOrderPageOutputDto extends BasePageOutputDto<PcOrderInfo> {
    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
