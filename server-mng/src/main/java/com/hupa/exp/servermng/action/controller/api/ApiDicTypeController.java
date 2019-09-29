package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dictype.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiDicTypeControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiDicController")
@RestController
@RequestMapping(path = "/v1/http/dictype",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiDicTypeController {

    private Logger logger = LoggerFactory.getLogger(ApiDicTypeController.class);

    @Autowired
    private IApiDicTypeControllerService service;
    @Autowired
    private SessionHelper sessionHelper;

    @ApiOperation(value = "获取字典信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<DicTypeListInputDto,DicTypeListOutputDto> getList(
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize
    ){
        logger.info("打印日志--------------------->");
        DicTypeListInputDto inputDto=new DicTypeListInputDto();
        DicTypeListOutputDto outputDto=new DicTypeListOutputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.queryDicTypeList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取字典信息")
    @GetMapping("/query_all_list")
    public BaseResultViaApiDto<DicTypeAllListInputDto,DicTypeAllListOutputDto> getAllList(
    ){
        logger.info("打印日志--------------------->");
        DicTypeAllListInputDto inputDto=new DicTypeAllListInputDto();
        DicTypeAllListOutputDto outputDto=new DicTypeAllListOutputDto();

        try{
            outputDto = service.getDicTypeAllList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "创建或修改")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<DicTypeInputDto,DicTypeOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="key",value = "值",required = true)
            @RequestParam(name = "key") String key,
            @ApiParam(name="value",value = "值",required = true)
            @RequestParam(name = "value") String value
    ){
        logger.info("打印日志--------------------->");
        DicTypeInputDto inputDto=new DicTypeInputDto();
        DicTypeOutputDto outputDto=new DicTypeOutputDto();
        inputDto.setId(id);
        inputDto.setKey(key);
        inputDto.setValue(value);
        try{
            if(id>0)
                outputDto = service.editDicType(inputDto);
            else
                outputDto = service.createDicType(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<DicTypeInfoInputDto,DicTypeInfoOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id
    ){
        logger.info("打印日志--------------------->");
        DicTypeInfoInputDto inputDto=new DicTypeInfoInputDto();
        DicTypeInfoOutputDto outputDto=new DicTypeInfoOutputDto();
        inputDto.setId(id);

        try{
            outputDto = service.getDicTypeById(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteDic(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteDicType(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}