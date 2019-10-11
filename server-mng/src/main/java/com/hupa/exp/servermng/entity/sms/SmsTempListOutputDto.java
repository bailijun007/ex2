package com.hupa.exp.servermng.entity.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.bizother.entity.sms.ExpSmsListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class SmsTempListOutputDto extends BaseOutputDto {

    @JsonProperty("page_data")
    private ExpSmsListBizBo pageData;

    public ExpSmsListBizBo getPageData() {
        return pageData;
    }

    public void setPageData(ExpSmsListBizBo pageData) {
        this.pageData = pageData;
    }
//        private String pageData;
//
//    public String getPageData() {
//        return pageData;
//    }
//
//    public void setPageData(String pageData) {
//        this.pageData = pageData;
//    }
}
