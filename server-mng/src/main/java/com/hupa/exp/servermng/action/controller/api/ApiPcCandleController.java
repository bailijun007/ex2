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
}
