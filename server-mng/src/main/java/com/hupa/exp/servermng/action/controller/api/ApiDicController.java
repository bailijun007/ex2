package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dic.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiDicControllerService;
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
@RequestMapping(path = "/v1/http/dic",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiDicController {

    private Logger logger = LoggerFactory.getLogger(ApiDicController.class);

    @Autowired
    private IApiDicControllerService service;
    @Autowired
    private SessionHelper sessionHelper;

    @ApiOperation(value = "通过字典类型获取字典信息")
    @GetMapping("/query_type_list")
    public BaseResultViaApiDto<DicAllListInputDto,DicAllListOutputDto> getDicListByType(
            @ApiParam(name="dic_type_key",value = "类型",required = true)
            @RequestParam(name = "dic_type_key") String dicTypeKey
    ){
        //logger.info("打印日志--------------------->");
        DicAllListInputDto inputDto=new DicAllListInputDto();
        DicAllListOutputDto outputDto=new DicAllListOutputDto();

        inputDto.setTypekey(dicTypeKey);
        try{
            outputDto = service.queryDicListByType(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取字典信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<DicListInputDto,DicListOutputDto> getAreaList(
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="dic_type",value = "类型",required = true)
            @RequestParam(name = "parent_id") Integer parentId
    ){
        //logger.info("打印日志--------------------->");
        DicListInputDto inputDto=new DicListInputDto();
        DicListOutputDto outputDto=new DicListOutputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        inputDto.setParentId(parentId);
        try{
            outputDto = service.queryDicList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "新增或修改字段")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<DicInputDto,DicOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="key",value = "键",required = true)
            @RequestParam(name = "key") String key,
            @ApiParam(name="value",value = "值",required = true)
            @RequestParam(name = "value") String value,
            @ApiParam(name="parent_id",value = "类型",required = true)
            @RequestParam(name = "parent_id") Integer parentId
    ){
        //logger.info("打印日志--------------------->");
        DicInputDto inputDto=new DicInputDto();
        DicOutputDto outputDto=new DicOutputDto();
        inputDto.setId(id);
        inputDto.setKey(key);
        inputDto.setValue(value);
        inputDto.setParentId(parentId);
        try{
            if(id>0)
                outputDto = service.editDic(inputDto);
            else
                outputDto = service.createDic(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<DicInfoInputDto,DicInfoOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id
    ){
        //logger.info("打印日志--------------------->");
        DicInfoInputDto inputDto=new DicInfoInputDto();
        DicInfoOutputDto outputDto=new DicInfoOutputDto();
        inputDto.setId(id);

        try{
            outputDto = service.getDicById(inputDto);

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
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteDic(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}