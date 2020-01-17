package com.hupa.exp.servermng.service.impl;

/*import com.hupa.exp.daomongo.dao.expv2.def.IMongoDbDao;
import com.hupa.exp.servermng.enums.MongoDbExceptionCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;*/
import com.hupa.exp.servermng.entity.mongodb.*;
import com.hupa.exp.servermng.exception.MongoDbException;
import com.hupa.exp.servermng.service.def.IApiMongoDbControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiMongoDbControllerServiceImpl implements IApiMongoDbControllerService {
/*    @Autowired
    private IMongoDbDao iMongoDbDao;*/

    private Logger logger = LoggerFactory.getLogger(ApiMongoDbControllerServiceImpl.class);

    @Override
    public MongoCollectionNamesOutputDto getMongoCollectionNames(MongoCollectionNamesInputDto inputDto) throws MongoDbException {
      /*  List<String> list= iMongoDbDao.getAllCollectionName();
        if(list==null)
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoCollectionNamesOutputDto outputDto=new MongoCollectionNamesOutputDto();
        outputDto.setCollectionNames(list);*/
        MongoCollectionNamesOutputDto outputDto=new MongoCollectionNamesOutputDto();
        return outputDto;
    }

    @Override
    public MongoCollectionFieldOutputDto getMongoCollectionFields(MongoCollectionFieldInputDto inputDto) throws MongoDbException {
     /*   List<String> list= iMongoDbDao.getCollectionField(inputDto.getCollectionName());
        if(list==null)
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoCollectionFieldOutputDto outputDto=new MongoCollectionFieldOutputDto();
        outputDto.setFields(list);*/
        MongoCollectionFieldOutputDto outputDto=new MongoCollectionFieldOutputDto();
        return outputDto;
    }

    @Override
    public MongoDataOutputDto getMongoData(MongoDataInputDto inputDto) throws MongoDbException {
//        Map<String,Map<String,Object>> searchMap=new HashMap<>();
//        if(!StringUtils.isEmpty(inputDto.getQueryStr()))
//        {
//            searchMap= getSearchMap(inputDto.getCollectionName(),inputDto.getQueryStr());
//        }
     /*  String mongoData= iMongoDbDao.findMongoData(inputDto.getCollectionName(),
               inputDto.getQueryStr(),inputDto.getCurrentPage(),inputDto.getPageSize());
        if(StringUtils.isEmpty(mongoData))
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoDataOutputDto outputDto=new MongoDataOutputDto();
        outputDto.setPageData(mongoData.replace("_id","id"));*/
        MongoDataOutputDto outputDto=new MongoDataOutputDto();
        return outputDto;
    }

}
