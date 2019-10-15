package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.bizother.enums.BizUserExceptionCode;
import com.hupa.exp.bizother.exception.BizUserException;
import com.hupa.exp.daomysql.dao.expv2.def.IMongoDbDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.servermng.action.controller.api.ApiOperationLogController;
import com.hupa.exp.servermng.entity.mongodb.*;
import com.hupa.exp.servermng.enums.MongoDbExceptionCode;
import com.hupa.exp.servermng.exception.MongoDbException;
import com.hupa.exp.servermng.service.def.IApiMongoDbControllerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiMongoDbControllerServiceImpl implements IApiMongoDbControllerService {
    @Autowired
    private IMongoDbDao iMongoDbDao;

    private Logger logger = LoggerFactory.getLogger(ApiMongoDbControllerServiceImpl.class);

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
        Map<String,Map<String,Object>> searchMap=new HashMap<>();
        if(!StringUtils.isEmpty(inputDto.getQueryStr()))
        {
            searchMap= getSearchMap(inputDto.getCollectionName(),inputDto.getQueryStr());
        }
       String mongoData= iMongoDbDao.findMongoData(inputDto.getCollectionName(),
               searchMap,inputDto.getCurrentPage(),inputDto.getPageSize());
        if(StringUtils.isEmpty(mongoData))
            throw new MongoDbException(MongoDbExceptionCode.MONGO_DB_HAS_NULL_DATA);
        MongoDataOutputDto outputDto=new MongoDataOutputDto();
        outputDto.setPageData(mongoData.replace("_id","id"));
        return outputDto;
    }

    private Map<String,Map<String,Object>> getSearchMap(String collectionName,String searchStr) {
        Map<String, Map<String, Object>> filedMap = new HashMap<>();
        try {
                //把这个表的字段类型查出来
                Map<String, Object> fieldMap = iMongoDbDao.getCollectionFieldType(collectionName);
                String[] searchArr = searchStr.split(",");
                //构造查询条件
                for (String search : searchArr) {
                    Map<String, Object> searchMap = new HashMap<>();
                    Object ret = null;
                    String[] strArr = search.split("[|]");
                    String fieldStr = strArr[0].equals("id") ? "_id" : strArr[0];
                    //获取字段属性
                    Object field = fieldMap.get(fieldStr);
                    //BigDecimal特殊处理
                    if (field.getClass().getSimpleName().contains("BigDecimal")) {
                        getBigDecimal(strArr[2]);
                    } else {
                        Class<?> class1 = Class.forName(getType(field.getClass().getName()));
                        Method method = null;
                        try {
                            method = class1.getMethod("parse" + fixparse(field.getClass().getSimpleName()), String.class);
                            if (method != null) {

                                try {
                                    ret = method.invoke(null, strArr[2]);
                                    if (ret != null)
                                        System.out.println(ret);
                                } catch (IllegalAccessException e) {
                                    logger.error("字段[" + strArr[0] + "]类型转换失败！");
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                    searchMap.put(strArr[1], ret);
                    filedMap.put(fieldStr, searchMap);
                }
            } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    return  filedMap;
    }


    private static String fixparse(String fstype) {
        switch (fstype) {
            case "Integer":
                return "Int";
            case "long":
                return "Long";
            default:
                return fstype;
        }
    }

    private static String getType(String fstype) {
        switch (fstype) {
            case "long":
                return "java.lang.Long";
            case "int":
                return "java.lang.Integer";
            default:
                return fstype;
        }
    }

    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
}
