package com.hupa.exp.servermng.entity.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.bizother.entity.menu.ExpMenuBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class MenuListOutputDto extends BaseOutputDto {

@JsonProperty("page_data")
    private List<ExpMenuBizBo> pageData;

    public List<ExpMenuBizBo> getPageData() {
        return pageData;
    }

    public void setPageData(List<ExpMenuBizBo> pageData) {
        this.pageData = pageData;
    }
}
