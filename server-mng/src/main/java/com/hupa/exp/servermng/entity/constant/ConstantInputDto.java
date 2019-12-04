package com.hupa.exp.servermng.entity.constant;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ConstantInputDto extends BaseInputDto {
    private Long id;
    private String key;
    private String value;
    private String splitSymbol;

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

    public String getSplitSymbol() {
        return splitSymbol;
    }

    public void setSplitSymbol(String splitSymbol) {
        this.splitSymbol = splitSymbol;
    }

}
