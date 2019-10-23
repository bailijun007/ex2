package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbolrate.*;
import com.hupa.exp.servermng.service.def.IApiSymbolRateControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = {"apiSymbolRateController"})
@RestController
@RequestMapping(path = "/v1/http/symbolrate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiSymbolRateController {
    @Autowired
    private IApiSymbolRateControllerService service;
    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入或修改利率")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<SymbolRateInputDto,SymbolRateOutputDto> createOrEditSymbolRate(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="base_rate",value = "基础利率",required = true)
            @RequestParam(name = "base_rate") BigDecimal baseRate,
            @ApiParam(name="valuation_rate",value = "计价利率",required = true)
            @RequestParam(name = "valuation_rate") BigDecimal valuationRate,
                   @ApiParam(name="rate_time",value = "利率时间",required = true)
            @RequestParam(name = "rate_time") String rateTime

    ){
        SymbolRateOutputDto outputDto=new SymbolRateOutputDto();
        SymbolRateInputDto inputDto=new SymbolRateInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setBaseRate(baseRate);
        inputDto.setValuationRate(valuationRate);
        long dt= DateTime.parse(rateTime+" 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setRateTime(dt);
        try{
            outputDto= service.createOrEditSymbolRate(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/query")
    public BaseResultViaApiDto<SymbolRateInfoInputDto,SymbolRateInfoOutputDto> querySymbolRate(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id

    ){
        SymbolRateInfoOutputDto outputDto=new SymbolRateInfoOutputDto();
        SymbolRateInfoInputDto inputDto=new SymbolRateInfoInputDto();
        inputDto.setId(id);
        try{
            outputDto= service.getSymbolRate(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<SymbolRateListInputDto,SymbolRateListOutputDto> querySymbolRateList(
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage

    ){
        SymbolRateListOutputDto outputDto=new SymbolRateListOutputDto();
        SymbolRateListInputDto inputDto=new SymbolRateListInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto= service.getSymbolRateList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteSymbolRate(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteSymbolRate(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
