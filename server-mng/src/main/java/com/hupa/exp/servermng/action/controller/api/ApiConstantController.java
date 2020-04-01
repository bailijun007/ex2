package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.constant.*;
import com.hupa.exp.servermng.service.def.IConstantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiConstantController")
@RestController
@RequestMapping(path = "/v1/http/constant",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiConstantController {

    @Autowired
    private IConstantService service;

    /**
     * 插入区号信息
     */
    @ApiOperation(value = "插入区号信息")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ConstantInputDto,ConstantOutputDto> createOrEditConstant(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="key",value = "区号",required = true)
            @RequestParam(name = "key") String key,
            @ApiParam(name="value",value = "区名",required = true)
            @RequestParam(name = "value") String value,
            @ApiParam(name="split_symbol",value = "间隔符",required = true)
            @RequestParam(name = "split_symbol") String splitSymbol,
            @ApiParam(name="parent",value = "间隔符",required = true)
            @RequestParam(name = "parent") Boolean parent,
            @ApiParam(name="parent_id",value = "间隔符",required = true)
            @RequestParam(name = "parent_id") String parentId,
            @ApiParam(name="remark",value = "备注",required = true)
            @RequestParam(name = "remark") String remark
    ){
        ConstantOutputDto outputDto=new ConstantOutputDto();
        ConstantInputDto inputDto=new ConstantInputDto();
        inputDto.setId(id);
        inputDto.setKey(key);
        inputDto.setSplitSymbol(splitSymbol);
        inputDto.setValue(value);
        inputDto.setParent(parent);
        inputDto.setParentId(Long.parseLong(parentId));
        inputDto.setRemark(remark);
        try{
            outputDto = service.createOrEditConstant(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query")
    public BaseResultViaApiDto<ConstantInfoInputDto,ConstantInfoOutputDto> getConstant(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") String id
    ){
        //logger.info("打印日志--------------------->");
        ConstantInfoOutputDto outputDto=new ConstantInfoOutputDto();
        ConstantInfoInputDto inputDto=new ConstantInfoInputDto();
        inputDto.setId(Long.parseLong(id));
        try{
            outputDto = service.getConstant(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<ConstantListInputDto,ConstantListOutputDto> getConstantList(
            @ApiParam(name="key",value = "key",required = true)
            @RequestParam(name = "key") String key,
            @ApiParam(name="parent_id",value = "间隔符",required = true)
            @RequestParam(name = "parent_id") String parentId,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Long pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Long currentPage
    ){
        //logger.info("打印日志--------------------->");
        ConstantListInputDto inputDto=new ConstantListInputDto();
        ConstantListOutputDto outputDto=new ConstantListOutputDto();
        inputDto.setKey(key);
        inputDto.setParentId(Long.parseLong(parentId));
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getConstantPage(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteArea(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteConstant(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "通过常量类型获取信息")
    @GetMapping("/query_type_list")
    public BaseResultViaApiDto<ConstantAllListInputDto,ConstantAllListOutputDto> getDicListByType(
            @ApiParam(name="parent_id",value = "类型",required = true)
            @RequestParam(name = "parent_id") String parentId
    ){
        //logger.info("打印日志--------------------->");
        ConstantAllListInputDto inputDto=new ConstantAllListInputDto();
        ConstantAllListOutputDto outputDto=new ConstantAllListOutputDto();
        inputDto.setParentId(Long.parseLong(parentId));
        try{
            outputDto = service.getAllConstant(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "通过字典类型获取字典信息")
    @GetMapping("/query_parent_list")
    public BaseResultViaApiDto<ConstantAllListInputDto,ConstantAllListOutputDto> getParentDicList(
    ){
        //logger.info("打印日志--------------------->");
        ConstantAllListInputDto inputDto=new ConstantAllListInputDto();
        ConstantAllListOutputDto outputDto=new ConstantAllListOutputDto();
        try{
            outputDto = service.getParentConstant(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
