package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsInputDto;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcCandleControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiPcCandleController")
@RestController
@RequestMapping(path="/v1/http/candle",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcCandleController {
    @Autowired
    private IApiPcCandleControllerService service;

    @ApiOperation(value = "获取模块限制信息")
    @GetMapping("/query_interval_count")
    public BaseResultViaApiDto<PcCandleStatisticsInputDto,PcCandleStatisticsOutputDto> getArea(
            @ApiParam(name="asset",value = "页码",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "条数",required = true)
            @RequestParam(name = "symbol") String symbol
    ){
        //logger.info("打印日志--------------------->");
        PcCandleStatisticsOutputDto outputDto=new PcCandleStatisticsOutputDto();
        PcCandleStatisticsInputDto inputDto=new PcCandleStatisticsInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        try{
            outputDto = service.getPcCandleStatisticsData(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



    @ApiOperation(value = "获取模块限制信息")
    @GetMapping("/query_interval_BbKline")
    public BaseResultViaApiDto<PcCandleStatisticsInputDto,PcCandleStatisticsOutputDto> getBbKline(
            @ApiParam(name="asset",value = "页码",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "条数",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="interval",value = "时间段",required = true)
            @RequestParam(name = "interval") String interval,
            @ApiParam(name="year",value = "年份",required = true)
            @RequestParam(name = "year") String year,
            @ApiParam(name="month",value = "月份",required = true)
            @RequestParam(name = "month") String month,
            @ApiParam(name="day",value = "天",required = true)
            @RequestParam(name = "day") String day,
            @ApiParam(name="klineType",value = "类型",required = true)
            @RequestParam(name = "klineType") String klineType
    ){
        PcCandleStatisticsOutputDto outputDto = null;
        PcCandleStatisticsInputDto inputDto = new PcCandleStatisticsInputDto();
        try{
            inputDto.setAsset(asset);
            inputDto.setSymbol(symbol);
            inputDto.setInterval(interval);
            inputDto.setYear(year);
            inputDto.setMonth(month);
            inputDto.setDay(day);
            inputDto.setKlineType(klineType);
            outputDto = service.getBbCandleStatisticsData(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
