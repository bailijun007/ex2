package com.hupa.exp.servermng.entity.pcposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.base.entity.bo.pc.PcPositionBo;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;

public class PcPositionPageOutputDto extends BasePageOutputDto<PcPositionInfo> {
    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
