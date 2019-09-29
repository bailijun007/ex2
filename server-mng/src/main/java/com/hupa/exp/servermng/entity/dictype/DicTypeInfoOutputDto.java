package com.hupa.exp.servermng.entity.dictype;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class DicTypeInfoOutputDto extends BaseOutputDto {
    private String id;
    private String key;
    private String value;


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

}
