package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.earningrate.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiEarningRateControllerService;
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

@Api(tags="apiEarningRateController")
@RestController
@RequestMapping(path = "/v1/http/earningrate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiEarningRateController {
    private Logger logger = LoggerFactory.getLogger(ApiEarningRateController.class);

    @Autowired
    private IApiEarningRateControllerService service;

    @ApiOperation(value = "获取收益率列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<PcEarningRatePageDataInputDto,PcEarningRatePageDataOutputDto> getList(
            @ApiParam(name="account",value = "账号",required = true)
            @RequestParam(name = "account") String account,
            @ApiParam(name="rate_time",value = "时间",required = true)
            @RequestParam(name = "rate_time") Long rateTime,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize
    ){
        //logger.info("打印日志--------------------->");
        PcEarningRatePageDataInputDto inputDto=new PcEarningRatePageDataInputDto();
        PcEarningRatePageDataOutputDto outputDto=new PcEarningRatePageDataOutputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        inputDto.setAccount(account);
        inputDto.setRateTime(rateTime);
        try{
            outputDto = service.queryEarningRatePageData(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }




    @ApiOperation(value = "创建或修改")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<PcEarningRateInputDto,PcEarningRateOutputDto> createOrEditDic(
             @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
             @ApiParam(name="account",value = "account",required = true)
             @RequestParam(name = "account") String account,
            @ApiParam(name="sort",value = "sort",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="earning_rate",value = "earning_rate",required = true)
            @RequestParam(name = "earning_rate") BigDecimal earningRate,
            @ApiParam(name="earning_rate_time",value = "earning_rate_time",required = true)
            @RequestParam(name = "earning_rate_time") String earningRateTime,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset",required = false) String asset,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol",required = false) String symbol

    ){
        PcEarningRateInputDto inputDto=new PcEarningRateInputDto();
        PcEarningRateOutputDto outputDto=new PcEarningRateOutputDto();
        inputDto.setId(id);
        inputDto.setAccount(account);
        inputDto.setSort(sort);
        inputDto.setEarningRate(earningRate);
        long dt=DateTime.parse(earningRateTime+" 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setEarningRateTime(dt);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        try{
            if(id>0)
                outputDto = service.editEarningRate(inputDto);
            else
                outputDto = service.createEarningRate(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<PcEarningRateInfoInputDto,PcEarningRateInfoOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id
    ){
        //logger.info("打印日志--------------------->");
        PcEarningRateInfoInputDto inputDto=new PcEarningRateInfoInputDto();
        PcEarningRateInfoOutputDto outputDto=new PcEarningRateInfoOutputDto();
        inputDto.setId(id);

        try{
            outputDto = service.getEarningRateById(inputDto);

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
            outputDto = service.deleteEarningRate(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检测是否已存在")
    @PostMapping("/check_has_earning_rate")
    public BaseResultViaApiDto<CheckHasEarningRateInputDto,CheckHasEarningRateOutputDto> deleteDic(
            @ApiParam(name="account",value = "account",required = true)
            @RequestParam(name = "account") String account,
                @ApiParam(name="earning_rate_time",value = "earning_rate_time",required = true)
                @RequestParam(name = "earning_rate_time") String earningRateTime
    ){
        //logger.info("打印日志--------------------->");
        CheckHasEarningRateInputDto inputDto=new CheckHasEarningRateInputDto();
        CheckHasEarningRateOutputDto outputDto=new CheckHasEarningRateOutputDto();
        inputDto.setAccount(account);
        long dt=DateTime.parse(earningRateTime +" 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        inputDto.setRateTime(dt);

        try{
            outputDto = service.checkHasEarningRate(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
