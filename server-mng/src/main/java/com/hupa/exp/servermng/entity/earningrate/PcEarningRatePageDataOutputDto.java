package com.hupa.exp.servermng.entity.earningrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;
import com.hupa.exp.daoex2.entity.po.PcTickerLastDataPo;
import com.hupa.exp.daoex2.entity.po.expv2.PcEarningRatePo;

public class PcEarningRatePageDataOutputDto extends BasePageOutputDto<PcEarningRatePo> {
    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
