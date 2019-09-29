package com.hupa.exp.servermng.entity.dictype;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DicTypeInputDto extends BaseInputDto {
    private Long id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
