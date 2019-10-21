package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.information.*;
import com.hupa.exp.servermng.help.OSSClientUtil;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiInformationControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiInformationController"})
@RestController
@RequestMapping(path = "/v1/http/information",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiInformationController {
    @Autowired
    private IApiInformationControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiInformationController.class);

    @ApiOperation(value = "获取资讯详情")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<InformationInputDto,InformationOutputDto> createOrEditInformation(
            @ApiParam(name = "id" ,value = "主键id",required =true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name = "title" ,value = "标题",required =true)
            @RequestParam(name = "title") String title,
            @ApiParam(name = "old_img" ,value = "旧的封面图片",required =true)
            @RequestParam(name = "old_img") String oldImg,
            @ApiParam(name = "cover_img" ,value = "封面图片",required =true)
            @RequestParam(name = "cover_img") String coverImg,
            @ApiParam(name = "content" ,value = "内容",required =true)
            @RequestParam(name = "content") String content,
            @ApiParam(name = "type" ,value = "类型",required =true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name = "link_url" ,value = "外链",required =true)
            @RequestParam(name = "link_url") String linkUrl,
            @ApiParam(name = "sort" ,value = "排序",required =true)
            @RequestParam(name = "sort") Integer sort
    )  {
        InformationInputDto inputDto=new InformationInputDto();
        inputDto.setId(id);
        inputDto.setTitle(title);
        inputDto.setOldImg(oldImg);
        inputDto.setCoverImg(coverImg);
        inputDto.setContent(content);
        inputDto.setType(type);
        inputDto.setLinkUrl(linkUrl);
        inputDto.setSort(sort);
        InformationOutputDto outputDto=new InformationOutputDto();
        try {
            outputDto=service.createOrEditInformation(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



    @ApiOperation(value = "获取资讯列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<GetInformationByTypeInputDto,GetInformationByTypeOutputDto> getInformationList(
            @ApiParam(name = "title" ,value = "标题",required =true)
            @RequestParam(name = "title") String title,
            @ApiParam(name = "type" ,value = "类型",required =true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name = "current_page" ,value = "页码",required =true)
            @RequestParam(name = "current_page") int currentPage,
            @ApiParam(name = "page_size" ,value = "条数",required =true)
            @RequestParam(name = "page_size") int pageSize
    )  {
        GetInformationByTypeInputDto inputDto=new GetInformationByTypeInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        inputDto.setType(type);
        inputDto.setTitle(title);
        GetInformationByTypeOutputDto outputDto=new GetInformationByTypeOutputDto();
        try {
            outputDto=service.getInformationByType(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取资讯详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<GetInformationInfoInputDto,GetInformationInfoOutputDto> getInformationInfo(
            @ApiParam(name = "id" ,value = "类型",required =true)
            @RequestParam(name = "id") long id
    )  {
        GetInformationInfoInputDto inputDto=new GetInformationInfoInputDto();
        inputDto.setId(id);
        GetInformationInfoOutputDto outputDto=new GetInformationInfoOutputDto();
        try {
            outputDto=service.getInformationInfo(inputDto);
        } catch (BizException e) {
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
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteInformation(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
