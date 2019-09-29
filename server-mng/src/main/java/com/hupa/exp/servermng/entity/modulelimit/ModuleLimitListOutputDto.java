package com.hupa.exp.servermng.entity.modulelimit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.bizother.entity.ExpModuleLimitListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class ModuleLimitListOutputDto  extends BaseOutputDto{
    @JsonProperty("page_data")
    private ExpModuleLimitListBizBo pageData;

    public ExpModuleLimitListBizBo getPageData() {
        return pageData;
    }

    public void setPageData(ExpModuleLimitListBizBo pageData) {
        this.pageData = pageData;
    }
}
