package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.enums.BizUserExceptionCode;
import com.hupa.exp.bizother.exception.BizUserException;
import com.hupa.exp.daomysql.dao.expv2.def.IMongoDbDao;
import com.hupa.exp.servermng.entity.mongodb.*;
import com.hupa.exp.servermng.enums.MongoDbExceptionCode;
import com.hupa.exp.servermng.exception.MongoDbException;
import com.hupa.exp.servermng.service.def.IApiMongoDbControllerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ApiMongoDbControllerServiceImpl implements IApiMongoDbControllerService {
    @Autowired
    private IMongoDbDao iMongoDbDao;

    @Override
    public MongoCollectionNamesOutputDto getMongoCollectionNames(MongoCollectionNamesInputDto inputDto) throws MongoDbException {
        List<String> list= iMongoDbDao.getAllCollectionName();
        if(list==null)
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoCollectionNamesOutputDto outputDto=new MongoCollectionNamesOutputDto();
        outputDto.setCollectionNames(list);
        return outputDto;
    }

    @Override
    public MongoCollectionFieldOutputDto getMongoCollectionFields(MongoCollectionFieldInputDto inputDto) throws MongoDbException {
        List<String> list= iMongoDbDao.getCollectionField(inputDto.getCollectionName());
        if(list==null)
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoCollectionFieldOutputDto outputDto=new MongoCollectionFieldOutputDto();
        outputDto.setFields(list);
        return outputDto;
    }

    @Override
    public MongoDataOutputDto getMongoData(MongoDataInputDto inputDto) throws MongoDbException {
       String mongoData= iMongoDbDao.findMongoData(inputDto.getCollectionName(),new HashMap<>(),1,1);
        if(StringUtils.isEmpty(mongoData))
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoDataOutputDto outputDto=new MongoDataOutputDto();
        outputDto.setPageData(mongoData.replace("_id","id"));
        return outputDto;
    }
}
