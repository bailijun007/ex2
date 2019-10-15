package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.mongodb.*;
import com.hupa.exp.servermng.service.def.IApiMongoDbControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiMongoDbController")
@RestController
@RequestMapping(path = "/v1/http/mongo",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiMongoDbController {
    @Autowired
    private IApiMongoDbControllerService service;
    @ApiOperation(value = "获取文档")
    @GetMapping("/get_collections")
    public BaseResultViaApiDto<MongoCollectionNamesInputDto,MongoCollectionNamesOutputDto> getMongoCollectionNames(
    ){
        //logger.info("打印日志--------------------->");
        MongoCollectionNamesInputDto inputDto=new MongoCollectionNamesInputDto();
        MongoCollectionNamesOutputDto outputDto=new MongoCollectionNamesOutputDto();

        try{
            outputDto = service.getMongoCollectionNames(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取文档字段")
    @GetMapping("/get_collection_fields")
    public BaseResultViaApiDto<MongoCollectionFieldInputDto,MongoCollectionFieldOutputDto> getMongoCollectionFields(
            @ApiParam(name="collection_name",value = "collection_name",required = true)
            @RequestParam(name = "collection_name") String collectionName
    ){
        //logger.info("打印日志--------------------->");
        MongoCollectionFieldInputDto inputDto=new MongoCollectionFieldInputDto();
        MongoCollectionFieldOutputDto outputDto=new MongoCollectionFieldOutputDto();
        inputDto.setCollectionName(collectionName);
        try{
            outputDto = service.getMongoCollectionFields(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取数据")
    @GetMapping("/get_mongo_data")
    public BaseResultViaApiDto<MongoDataInputDto,MongoDataOutputDto> getMongoData(
            @ApiParam(name="collection_name",value = "collection_name",required = true)
            @RequestParam(name = "collection_name") String collectionName,
            @ApiParam(name="query_str",value = "query_str",required = true)
            @RequestParam(name = "query_str") String queryStr,
            @ApiParam(name="page_size",value = "page_size",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "current_page",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        //logger.info("打印日志--------------------->");
        MongoDataInputDto inputDto=new MongoDataInputDto();
        MongoDataOutputDto outputDto=new MongoDataOutputDto();
        inputDto.setCollectionName(collectionName);
        inputDto.setQueryStr(queryStr);
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        try{
            outputDto = service.getMongoData(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
