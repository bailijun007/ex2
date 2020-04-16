package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.appversion.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.service.def.IApiAppVersionControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiAppVersionController")
@RestController
@RequestMapping(path = "/v1/http/appversion",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAppVersionController {

    @Autowired
    private IApiAppVersionControllerService service;
    /**
     * 插入区号信息
     */
    @ApiOperation(value = "插入区号信息")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<AppVersionInputDto,AppVersionOutputDto> createArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="type",value = "类型",required = true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name="version",value = "版本",required = true)
            @RequestParam(name = "version") String version,
            @ApiParam(name="remark",value = "备注",required = true)
            @RequestParam(name = "remark") String remark,
            @ApiParam(name="forced_update",value = "备注",required = true)
            @RequestParam(name = "forced_update") boolean forcedUpdate,
            @ApiParam(name="update_content",value = "备注",required = true)
            @RequestParam(name = "update_content") String updateContent,
            @ApiParam(name="link_url",value = "备注",required = true)
            @RequestParam(name = "link_url") String linkUrl,
            @ApiParam(name="release_time",value = "备注",required = true)
            @RequestParam(name = "release_time") Long releaseTime,
            @ApiParam(name="status",value = "启用状态",required = true)
            @RequestParam(name = "status") String status

    ){
        AppVersionOutputDto outputDto=new AppVersionOutputDto();
        AppVersionInputDto inputDto=new AppVersionInputDto();
        inputDto.setType(type);
        inputDto.setVersion(version);
        inputDto.setId(id);
        inputDto.setRemark(remark);
        inputDto.setForcedUpdate(forcedUpdate);
        inputDto.setUpdateContent(updateContent);
        inputDto.setLinkUrl(linkUrl);
        inputDto.setReleaseTime(releaseTime);
        inputDto.setStatus(status);
        try{
                outputDto = service.createOrUpdateAppVersion(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query")
    public BaseResultViaApiDto<AppVersionInfoInputDto,AppVersionInfoOutputDto> getArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    ){
        //logger.info("打印日志--------------------->");
        AppVersionInfoOutputDto outputDto=new AppVersionInfoOutputDto();
        AppVersionInfoInputDto inputDto=new AppVersionInfoInputDto();
        inputDto.setId(id);
        try{
            outputDto = service.getAppVersionById(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<AppVersionListInputDto,AppVersionListOutputDto> getAreaList(
            @ApiParam(name="type",value = "类型",required = true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        //logger.info("打印日志--------------------->");

        AppVersionListOutputDto outputDto=new AppVersionListOutputDto();
        AppVersionListInputDto inputDto=new AppVersionListInputDto();
        inputDto.setType(type);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getAppVersionPageData(inputDto);
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
            outputDto = service.deleteAppVersion(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
