package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.mongodb.*;

public interface IApiMongoDbControllerService {
    MongoCollectionNamesOutputDto getMongoCollectionNames(MongoCollectionNamesInputDto inputDto) throws BizException;

    MongoCollectionFieldOutputDto getMongoCollectionFields(MongoCollectionFieldInputDto inputDto)throws BizException;

    MongoDataOutputDto getMongoData(MongoDataInputDto inputDto)throws BizException;

}
