package com.hupa.exp.servermng.entity.dic;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DicInputDto extends BaseInputDto {
    private Long id;
    private String key;
    private String value;
    private Integer dicType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDicType() {
        return dicType;
    }

    public void setDicType(Integer dicType) {
        this.dicType = dicType;
    }
}