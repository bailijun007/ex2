package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.storinglevel.*;
import com.hupa.exp.servermng.entity.symbolinterest.*;
import com.hupa.exp.servermng.service.def.IApiSymbolInterestControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = {"apiStoringLevelController"})
@RestController
@RequestMapping(path = "/v1/http/symbolinterest",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiSymbolInterestController {
    @Autowired
    private IApiSymbolInterestControllerService service;
    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入用户")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<SymbolInterestInputDto,SymbolInterestOutputDto> createOrEditStoringLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="symbol",value = "交易对",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="symbol_interest",value = "档次",required = true)
            @RequestParam(name = "symbol_interest") BigDecimal symbolInterest,
                   @ApiParam(name="interest_time",value = "时间",required = true)
            @RequestParam(name = "interest_time") String interestTime

    ){
        SymbolInterestOutputDto outputDto=new SymbolInterestOutputDto();
        SymbolInterestInputDto inputDto=new SymbolInterestInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        inputDto.setSymbolInterest(symbolInterest);
        long dt= DateTime.parse(interestTime+" 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setInterestTime(dt);
        try{
            outputDto= service.createOrEditSymbolInterest(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/query")
    public BaseResultViaApiDto<SymbolInterestInfoInputDto,SymbolInterestInfoOutputDto> queryStoringLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id

    ){
        SymbolInterestInfoOutputDto outputDto=new SymbolInterestInfoOutputDto();
        SymbolInterestInfoInputDto inputDto=new SymbolInterestInfoInputDto();
        inputDto.setId(id);
        try{
            outputDto= service.getSymbolInterest(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<SymbolInterestListInputDto,SymbolInterestListOutputDto> queryStoringLevelList(
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage

    ){
        SymbolInterestListOutputDto outputDto=new SymbolInterestListOutputDto();
        SymbolInterestListInputDto inputDto=new SymbolInterestListInputDto();
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto= service.getSymbolInterestList(inputDto);
        }catch(BizException e){
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
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteSymbolInterest(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
