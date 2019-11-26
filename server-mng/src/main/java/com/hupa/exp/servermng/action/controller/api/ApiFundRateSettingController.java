package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.fundrate.*;
import com.hupa.exp.servermng.service.def.IApiFundRateSettingControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiFundRateSettingController")
@RestController
@RequestMapping(path = "/v1/http/fundrate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiFundRateSettingController {
    @Autowired
    private IApiFundRateSettingControllerService settingControllerService;


    @ApiOperation(value = "新增或修改配置")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<FundRateSettingInputDto,FundRateSettingOutputDto> createOrEditCollectFeeSetting(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="collect_num",value = "收费次数",required = true)
            @RequestParam(name = "collect_num") Integer collectNum,
            @ApiParam(name="max_fund_rate",value = "最大资金费率",required = true)
            @RequestParam(name = "max_fund_rate") BigDecimal maxFundRate,
            @ApiParam(name="min_fund_rate",value = "最小资金费率",required = true)
            @RequestParam(name = "min_fund_rate") BigDecimal minFundRate,
            @ApiParam(name="collect_first_time",value = "收费开始时间",required = true)
            @RequestParam(name = "collect_first_time") String collectFirstTime
    )
    {
        FundRateSettingInputDto inputDto=new FundRateSettingInputDto();
        inputDto.setId(id);
        inputDto.setCollectNum(collectNum);
        long dt= DateTime.parse(collectFirstTime+":00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setCollectFirstTime(dt);
        inputDto.setMaxFundRate(maxFundRate);
        inputDto.setMinFundRate(minFundRate);
        FundRateSettingOutputDto outputDto=new FundRateSettingOutputDto();
        try {
            if(inputDto.getId()>0)
                outputDto= settingControllerService.editFundRateSetting(inputDto);
            else
                outputDto= settingControllerService.createFundRateSetting(inputDto);
        } catch (BizException e) {

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "获取配置信息")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<FundRateSettingInfoInputDto,FundRateSettingInfoOutputDto> getAsset(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        FundRateSettingInfoInputDto inputDto=new FundRateSettingInfoInputDto();
        inputDto.setId(id);
        FundRateSettingInfoOutputDto outputDto=new FundRateSettingInfoOutputDto();
        try {
            outputDto= settingControllerService.getFundRateSettingById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "获取配置列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<FundRateSettingListInputDto,FundRateSettingListOutputDto> getAssetList(//
                                                                                                      @ApiParam(name="page_size",value = "条数",required = true)
        @RequestParam(name = "page_size") Integer pageSize,
                                                                                                      @ApiParam(name="current_page",value = "页码",required = true)
        @RequestParam(name = "current_page") Integer currentPage
    )
    {
        FundRateSettingListInputDto inputDto=new FundRateSettingListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        FundRateSettingListOutputDto outputDto=new FundRateSettingListOutputDto();
        try {
            outputDto= settingControllerService.getFundRateSettingList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
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
            outputDto = settingControllerService.deleteFundRateSetting(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
