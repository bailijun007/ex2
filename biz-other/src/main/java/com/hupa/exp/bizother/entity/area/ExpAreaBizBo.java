package com.hupa.exp.bizother.entity.area;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpAreaBizBo {
    private long id;
    @JsonProperty("area_code")
    private String areaCode;
    @JsonProperty("area_name")
    private String areaName;
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
