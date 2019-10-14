package com.hupa.exp.servermng.entity.mongodb;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;
import java.util.Map;

public class MongoCollectionNamesOutputDto extends BaseOutputDto {
    private List<String> collectionNames;

    public List<String> getCollectionNames() {
        return collectionNames;
    }

    public void setCollectionNames(List<String> collectionNames) {
        this.collectionNames = collectionNames;
    }
}
