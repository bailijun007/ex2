package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.area.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.service.def.IApiAreaControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiAreaController")
@RestController
@RequestMapping(path = "/v1/http/area",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAreaController {

    //private Logger logger = LoggerFactory.getLogger(ApiAreaController.class);

    //@Autowired
    //private SessionHelper sessionHelper;

    @Autowired
    private IApiAreaControllerService service;

    /**
     * 插入区号信息
     */
    @ApiOperation(value = "插入区号信息")
    @PostMapping("/create_area")
    public BaseResultViaApiDto<AreaInputDto,AreaOutputDto> createArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="area_code",value = "区号",required = true)
            @RequestParam(name = "area_code") String areaCode,
            @ApiParam(name="area_name",value = "区名",required = true)
            @RequestParam(name = "area_name") String areaName,
                     @ApiParam(name="enable",value = "是否生效",required = true)
            @RequestParam(name = "enable") boolean enable
    ){
        AreaOutputDto outputDto=new AreaOutputDto();
        AreaInputDto inputDto=new AreaInputDto();
        inputDto.setAreaCode(areaCode);
        inputDto.setAreaName(areaName);
        inputDto.setId(id);
        inputDto.setEnable(enable);
        try{
            if(id>0)
                outputDto = service.editArea(inputDto);
            else
                outputDto= service.createArea(inputDto);

        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query")
    public BaseResultViaApiDto<GetAreaInputDto,GetAreaOutputDto> getArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    ){
        //logger.info("打印日志--------------------->");
        GetAreaOutputDto outputDto=new GetAreaOutputDto();
        GetAreaInputDto inputDto=new GetAreaInputDto();
        inputDto.setId(id);
        try{
            outputDto = service.getAreaById(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<AreaListInputDto,AreaListOutputDto> getAreaList(
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        //logger.info("打印日志--------------------->");
        AreaListInputDto inputDto=new AreaListInputDto();
        AreaListOutputDto outputDto=new AreaListOutputDto();

        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getAreaList(inputDto);
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
            outputDto = service.deleteArea(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
