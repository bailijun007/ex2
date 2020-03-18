package com.hupa.exp.servermng.entity.bborder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;
import com.hupa.exp.servermng.entity.pcorder.PcOrderInfo;

/**
 * Created by Administrator on 2020/2/15.
 */
public class BbOrderPageOutputDto extends BasePageOutputDto<BbOrderInfo> {

    @JsonProperty("page_data")
    private String pageData;

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }
}
