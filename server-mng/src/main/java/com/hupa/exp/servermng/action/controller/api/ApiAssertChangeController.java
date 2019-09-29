package com.hupa.exp.servermng.action.controller.api;


import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.assertchange.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssertChangeControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiAssertChangeController")
@RestController
@RequestMapping(path = "/v1/http/assert",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAssertChangeController {
    @Autowired
    private IApiAssertChangeControllerService service;
    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiAssertChangeController.class);
    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_fund_assert")
    public BaseResultViaApiDto<FundAssertChangeInputDto,FundAssertChangeOutputDto> getFundAssertChange(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol
    ){

        FundAssertChangeOutputDto outputDto=new FundAssertChangeOutputDto();
        FundAssertChangeInputDto inputDto=new FundAssertChangeInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        try{
            outputDto = service.getFundAssertChange(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_fund_assert_list")
    public BaseResultViaApiDto<FundAssertChangeListInputDto,FundAssertChangeListOutputDto> getFundAssertChangeLsit(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        FundAssertChangeListOutputDto outputDto=new FundAssertChangeListOutputDto();
        FundAssertChangeListInputDto inputDto=new FundAssertChangeListInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getFundAssertChangeList(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_pc_assert")
    public BaseResultViaApiDto<PcAssertChangeInputDto,PcAssertChangeOutputDto> getPcAssertChange(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol
    ){

        PcAssertChangeOutputDto outputDto=new PcAssertChangeOutputDto();
        PcAssertChangeInputDto inputDto=new PcAssertChangeInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        try{
            outputDto = service.getPcAssertChange(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取区号信息")
    @GetMapping("/query_pc_assert_list")
    public BaseResultViaApiDto<PcAssertChangeListInputDto,PcAssertChangeListOutputDto> getPcAssertChangeList(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        PcAssertChangeListOutputDto outputDto=new PcAssertChangeListOutputDto();
        PcAssertChangeListInputDto inputDto=new PcAssertChangeListInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getPcAssertChangeList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
