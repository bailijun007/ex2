package com.hupa.exp.servermng.entity.dic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class DicInfoOutputDto  extends BaseOutputDto {
    private String id;
    private String key;
    private String value;
    @JsonProperty("parent_id")
    private String parentId;
    private String parent;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
