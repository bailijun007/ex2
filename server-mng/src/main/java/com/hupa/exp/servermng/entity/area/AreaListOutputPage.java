package com.hupa.exp.servermng.entity.area;

import org.apache.pulsar.shade.org.codehaus.jackson.annotate.JsonProperty;

public class AreaListOutputPage {
    private String id;
    @JsonProperty("area_code")
    private String areaCode;
    @JsonProperty("area_name")
    private String areaName;
    private String enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
