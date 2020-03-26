package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiKlineConfigControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

    @ApiOperation(value = "获取某个时间段K线")
    @PostMapping(path = "/getIntervalKline")
    public BaseResultViaApiDto<KlineConfigInputDto,RepairKlineListOutputDto> getIntervalKline(
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time") String statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type") Integer klineType
    ) {
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setKlineType(klineType);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");//:ss
        inputDto.setStatTime(DateTime.parse(statTime, formatter).getMillis());
        inputDto.setEndTime(DateTime.parse(endTime, formatter).getMillis());
        RepairKlineListOutputDto outputDto=new RepairKlineListOutputDto();
        try {
            outputDto=service.getIntervalKline(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "修补某一段实时交易数据")
    @GetMapping(path = "/getRepairKline")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> getRepairKline(//KlineConfigInfoInputDto
            //@ApiParam(name="id",value = "id",required = true)
            //@RequestParam(name = "id") long id
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time") String statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type") Integer klineType
    ) {
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        //inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setKlineType(klineType);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        inputDto.setStatTime(DateTime.parse(statTime, formatter).getMillis());
        inputDto.setEndTime(DateTime.parse(endTime, formatter).getMillis());
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.repairKlineConfig(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "撤销某一段时间的K线数据")
    @GetMapping(path = "/getRevokeKline")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> getRevokeKline(
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time") String statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type") Integer klineType
    ) {
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setKlineType(klineType);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        inputDto.setStatTime(DateTime.parse(statTime, formatter).getMillis());
        inputDto.setEndTime(DateTime.parse(endTime, formatter).getMillis());
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.getRevokeKline(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "手动修补K线")
    @PostMapping("/getManualKline")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> getManualKline( //KlineConfigOutputDto
            @RequestBody KlineConfigInputDto inputDto//@RequestBody KlineInfoInputDto inputDto
    )  {
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.getManualKline(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "修补第三方历史K线数据")
    @GetMapping(path = "/getThirdDataKline")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> getThirdDataKline(
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time") String statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type") Integer klineType
    ) {
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setKlineType(klineType);
        DateTimeFormatter formatter  = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        inputDto.setStatTime(DateTime.parse(statTime,formatter).getMillis());
        inputDto.setEndTime(DateTime.parse(endTime, formatter).getMillis());
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.getThirdDataKline(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "按分钟级别的修补，第三方历史K线数据")
    @GetMapping(path = "/getThirdDataIntervalKline")
    public BaseResultViaApiDto<KlineConfigInputDto,KlineConfigOutputDto> getThirdDataIntervalKline(
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对名称",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="stat_time",value = "开始时间",required = true)
            @RequestParam(name = "stat_time") String statTime,
            @ApiParam(name="end_time",value = "开始时间",required = true)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="kline_type",value = "类别",required = true)
            @RequestParam(name = "kline_type") Integer klineType
    ) {
        KlineConfigInputDto inputDto=new KlineConfigInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setKlineType(klineType);
        DateTimeFormatter formatter  = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        inputDto.setStatTime(DateTime.parse(statTime, formatter).getMillis());
        inputDto.setEndTime(DateTime.parse(endTime, formatter).getMillis());
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        try {
            outputDto=service.getThirdDataIntervalKline(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
