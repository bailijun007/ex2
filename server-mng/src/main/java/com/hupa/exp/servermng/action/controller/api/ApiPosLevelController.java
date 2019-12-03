package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.poslevel.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPosLevelControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = {"apiPosLevelController"})
@RestController
@RequestMapping(path = "/v1/http/poslevel",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPosLevelController {
    @Autowired
    private IApiPosLevelControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiPosLevelController.class);

    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入用户")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<PosLevelInputDto,PosLevelOutputDto> createOrEditPosLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="asset",value = "币",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="gear",value = "档次",required = true)
            @RequestParam(name = "gear") Integer gear,
            @ApiParam(name="min_amt",value = "最小张数",required = true)
            @RequestParam(name = "min_amt") Long minAmt,
            @ApiParam(name="max_amt",value = "最大张数",required = true)
            @RequestParam(name = "max_amt") Long maxAmt,
            @ApiParam(name="max_leverage",value = "最大杠杆",required = true)
            @RequestParam(name = "max_leverage") BigDecimal maxLeverage,
            @ApiParam(name="pos_hold_margin_ratio",value = "维持保证金率",required = true)
            @RequestParam(name = "pos_hold_margin_ratio") BigDecimal posHoldMarginRatio,
            @ApiParam(name="min_hold_margin_ratio",value = "初始保证金率",required = true)
            @RequestParam(name = "min_hold_margin_ratio") BigDecimal minHoldMarginRatio

    ){
        PosLevelOutputDto outputDto=new PosLevelOutputDto();
        PosLevelInputDto inputDto=new PosLevelInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setGear(gear);
        inputDto.setMaxAmt(maxAmt);
        inputDto.setMinAmt(minAmt);
        inputDto.setMaxLeverage(maxLeverage);
        inputDto.setPosHoldMarginRatio(posHoldMarginRatio);
        inputDto.setMinHoldMarginRatio(minHoldMarginRatio);
        try{
            outputDto= service.createOrEditPosLevel(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/query")
    public BaseResultViaApiDto<PosLevelInfoInputDto,PosLevelInfoOutputDto> queryPosLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id

    ){
        PosLevelInfoOutputDto outputDto=new PosLevelInfoOutputDto();
        PosLevelInfoInputDto inputDto=new PosLevelInfoInputDto();
        inputDto.setId(id);
        try{
            outputDto= service.getPosLevel(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<PosLevelListInputDto,PosLevelListOutputDto> queryPosLevelList(
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
              @ApiParam(name="page_size",value = "条数",required = true)
              @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage

    ){
        PosLevelListOutputDto outputDto=new PosLevelListOutputDto();
        PosLevelListInputDto inputDto=new PosLevelListInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto= service.getPosLevelList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
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
            outputDto = service.deletePosLevel(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
