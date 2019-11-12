package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dic.*;
import com.hupa.exp.servermng.entity.exchangerate.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiDicControllerService;
import com.hupa.exp.servermng.service.def.IApiExchangeRateControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags="apiExchangeRateController")
@RestController
@RequestMapping(path = "/v1/http/exchangerate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiExchangeRateController {
    @Autowired
    private IApiExchangeRateControllerService service;


    @ApiOperation(value = "获取字典信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<ExchangeRateListInputDto,ExchangeRateListOutputDto> getExchangeRateList(
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize
    ){
        //logger.info("打印日志--------------------->");
        ExchangeRateListInputDto inputDto=new ExchangeRateListInputDto();
        ExchangeRateListOutputDto outputDto=new ExchangeRateListOutputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.queryExchangeRateList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "新增或修改字段")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ExchangeRateInputDto,ExchangeRateOutputDto> createOrEditExchangeRate(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="source_asset",value = "待转换汇率",required = true)
            @RequestParam(name = "source_asset") String sourceAsset,
            @ApiParam(name="target_asset",value = "转换汇率",required = true)
            @RequestParam(name = "target_asset") String targetAsset,
            @ApiParam(name="exchange_rate",value = "汇率",required = true)
            @RequestParam(name = "exchange_rate") BigDecimal exchangeRate,
            @ApiParam(name="auto_refresh",value = "是否自动更新",required = true)
            @RequestParam(name = "auto_refresh") boolean autoRefresh
    ){
        //logger.info("打印日志--------------------->");
        ExchangeRateInputDto inputDto=new ExchangeRateInputDto();
        ExchangeRateOutputDto outputDto=new ExchangeRateOutputDto();
        inputDto.setId(id);
        inputDto.setSourceAsset(sourceAsset);
        inputDto.setTargetAsset(targetAsset);
        inputDto.setExchangeRate(exchangeRate);
        inputDto.setAutoRefresh(autoRefresh);
        try{
            if(id>0)
                outputDto = service.editExchangeRate(inputDto);
            else
                outputDto = service.createExchangeRate(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<ExchangeRateInfoInputDto,ExchangeRateInfoOutputDto> queryExchangeRate(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id
    ){
        //logger.info("打印日志--------------------->");
        ExchangeRateInfoOutputDto outputDto=new ExchangeRateInfoOutputDto();
        ExchangeRateInfoInputDto inputDto=new ExchangeRateInfoInputDto();

        inputDto.setId(id);

        try{
            outputDto = service.getExchangeRateById(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteExchangeRate(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteExchangeRate(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
