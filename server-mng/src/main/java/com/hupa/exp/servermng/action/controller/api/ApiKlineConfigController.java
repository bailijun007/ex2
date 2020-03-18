package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.information.InformationInputDto;
import com.hupa.exp.servermng.entity.information.InformationOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiKlineConfigControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiKlineConfigController"})
@RestController
@RequestMapping(path = "/v1/http/klineconfig",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiKlineConfigController {
    private Logger logger = LoggerFactory.getLogger(ApiKlineConfigController.class);

    @Autowired
    IApiKlineConfigControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    @ApiOperation(value = "插入交易对")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> createOrEditKlineConfig(
            @ApiParam(name="id",value = "主键id",required = true)
            @RequestParam(name = "id" ,required = false) long id,
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="klineInterval",value = "时间区间",required = true)
            @RequestParam(name = "klineInterval") String klineInterval,
            @ApiParam(name="enable",value = "是否启用",required = true)
            @RequestParam(name = "enable") boolean enable,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time",required = false) Long statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time",required = false) Long endTime,
            @ApiParam(name="type",value = "类型",required = true)
            @RequestParam(name = "type",required = false) Integer type,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type",required = false) Integer klineType

    ){
        //logger.info("打印日志--------------------->");
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        inputDto.setId(id);
        klineInterval= !StringUtils.isEmpty(klineInterval)&&klineInterval.length()>0?klineInterval.substring(0,klineInterval.length()-1):"";
        inputDto.setKlineInterval(klineInterval);
        inputDto.setStatus(enable);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setStatTime(statTime);
        inputDto.setEndTime(endTime);
        inputDto.setType(type);
        inputDto.setKlineType(klineType);

        try{
            if(id>0)
                outputDto= service.editKlineConfig(inputDto);
            else
                outputDto= service.createKlineConfig(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<KlineConfigListInputDto,KlineConfigListOutputDto> getKlineConfigList(//
             @ApiParam(name="page_size",value = "条数",required = true)
             @RequestParam(name = "page_size") Integer pageSize,
             @ApiParam(name="current_page",value = "页码",required = true)
             @RequestParam(name = "current_page") Integer currentPage,
             @ApiParam(name="kline_type",value = "类别",required = true)
                @RequestParam(name = "kline_type",required = false) Integer klineType
    )
    {
        KlineConfigListInputDto inputDto=new KlineConfigListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        inputDto.setKlineType(klineType);
        KlineConfigListOutputDto outputDto=new KlineConfigListOutputDto();
        try {
            outputDto=service.queryKlineConfigList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对详情")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<KlineConfigInfoInputDto,KlineConfigInfoOutputDto> queryKlineConfigById(//
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        KlineConfigInfoInputDto inputDto=new KlineConfigInfoInputDto();
        inputDto.setId(id);
        KlineConfigInfoOutputDto outputDto=new KlineConfigInfoOutputDto();
        try {
            outputDto=service.queryKlineConfigById(inputDto);
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
            outputDto = service.deleteKlineConfig(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对详情")
    @GetMapping(path = "/getRepairKline")
    public BaseResultViaApiDto<KlineConfigInfoInputDto,RepairKlineListOutputDto> getRepairKline(
      @ApiParam(name="id",value = "id",required = true)
      @RequestParam(name = "id") long id
    ) {
        KlineConfigInfoInputDto inputDto=new KlineConfigInfoInputDto();
        inputDto.setId(id);
        RepairKlineListOutputDto outputDto=new RepairKlineListOutputDto();
        try {
            outputDto=service.getBbCandlePoList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



    @ApiOperation(value = "获取资讯详情")
    @PostMapping("/send")
    public BaseResultViaApiDto<KlineInfoInputDto,KlineConfigOutputDto> send(
            @RequestBody KlineInfoInputDto inputDto
    )  {
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.sendKlineConfig(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



}
