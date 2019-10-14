package com.hupa.exp.servermng.entity.mongodb;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class MongoCollectionFieldOutputDto extends BaseOutputDto {
    private List<String> fields;

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
