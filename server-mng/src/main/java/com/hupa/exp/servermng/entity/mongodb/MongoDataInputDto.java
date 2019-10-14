package com.hupa.exp.servermng.entity.mongodb;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class MongoDataInputDto extends BaseInputDto {
    private String collectionName;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
