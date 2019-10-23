package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.entity.collectfeesetting.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssetControllerService;
import com.hupa.exp.servermng.service.def.IApiCollectFeeSettingControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiCollectFeeSettingController")
@RestController
@RequestMapping(path = "/v1/http/collect",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiCollectFeeSettingController {
    @Autowired
    private IApiCollectFeeSettingControllerService settingControllerService;


    @ApiOperation(value = "新增或修改配置")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<CollectFeeSettingInputDto,CollectFeeSettingOutputDto> createOrEditCollectFeeSetting(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="collect_fee_num",value = "收费次数",required = true)
            @RequestParam(name = "collect_fee_num") Integer collectFeeNum,
            @ApiParam(name="collect_fee_time",value = "收费开始时间",required = true)
            @RequestParam(name = "collect_fee_time") String collectFeeTime
    )
    {
        CollectFeeSettingInputDto inputDto=new CollectFeeSettingInputDto();
        inputDto.setId(id);
        inputDto.setCollectFeeNum(collectFeeNum);
        long dt= DateTime.parse(collectFeeTime+":00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setCollectFeeTime(dt);
        CollectFeeSettingOutputDto outputDto=new CollectFeeSettingOutputDto();
        try {
            if(inputDto.getId()>0)
                outputDto= settingControllerService.editCollectFeeSetting(inputDto);
            else
                outputDto= settingControllerService.createCollectFeeSetting(inputDto);
        } catch (BizException e) {

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "获取配置信息")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<CollectFeeSettingInfoInputDto,CollectFeeSettingInfoOutputDto> getAsset(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        CollectFeeSettingInfoInputDto inputDto=new CollectFeeSettingInfoInputDto();
        inputDto.setId(id);
        CollectFeeSettingInfoOutputDto outputDto=new CollectFeeSettingInfoOutputDto();
        try {
            outputDto= settingControllerService.getCollectFeeSettingById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "获取配置列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<CollectFeeSettingListInputDto,CollectFeeSettingListOutputDto> getAssetList(//
        @ApiParam(name="page_size",value = "条数",required = true)
        @RequestParam(name = "page_size") Integer pageSize,
        @ApiParam(name="current_page",value = "页码",required = true)
        @RequestParam(name = "current_page") Integer currentPage
    )
    {
        CollectFeeSettingListInputDto inputDto=new CollectFeeSettingListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        CollectFeeSettingListOutputDto outputDto=new CollectFeeSettingListOutputDto();
        try {
            outputDto= settingControllerService.getCollectFeeSettingList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
