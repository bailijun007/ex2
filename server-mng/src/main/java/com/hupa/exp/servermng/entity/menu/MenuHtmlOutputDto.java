package com.hupa.exp.servermng.entity.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class MenuHtmlOutputDto extends BaseOutputDto {
    public String getHtmlStr() {
        return htmlStr;
    }

    public void setHtmlStr(String htmlStr) {
        this.htmlStr = htmlStr;
    }

    @JsonProperty("html_str")
    private String htmlStr;
}
