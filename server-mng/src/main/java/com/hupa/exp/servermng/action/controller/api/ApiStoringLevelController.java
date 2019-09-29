package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.storinglevel.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiStoringLevelControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = {"apiStoringLevelController"})
@RestController
@RequestMapping(path = "/v1/http/storinglevel",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiStoringLevelController {
    @Autowired
    private IApiStoringLevelControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiStoringLevelController.class);

    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入用户")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<StoringLevelInputDto,StoringLevelOutputDto> createOrEditStoringLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="pair",value = "交易对",required = true)
            @RequestParam(name = "pair") String pair,
            @ApiParam(name="gear",value = "档次",required = true)
            @RequestParam(name = "gear") Integer gear,
            @ApiParam(name="min_amt",value = "最小张数",required = true)
            @RequestParam(name = "min_amt") Integer minAmt,
            @ApiParam(name="max_amt",value = "最大张数",required = true)
            @RequestParam(name = "max_amt") Integer maxAmt,
            @ApiParam(name="max_leverage",value = "最大杠杆",required = true)
            @RequestParam(name = "max_leverage") Integer maxLeverage,
            @ApiParam(name="pos_matin_margin_ratio",value = "维持保证金率",required = true)
            @RequestParam(name = "pos_matin_margin_ratio") BigDecimal posMatinMarginRatio
    ){
        StoringLevelOutputDto outputDto=new StoringLevelOutputDto();
        StoringLevelInputDto inputDto=new StoringLevelInputDto();
        inputDto.setId(id);
        inputDto.setPair(pair);
        inputDto.setGear(gear);
        inputDto.setMaxAmt(maxAmt);
        inputDto.setMinAmt(minAmt);
        inputDto.setMaxLeverage(maxLeverage);
        inputDto.setPosMatinMarginRatio(posMatinMarginRatio);
        try{
            outputDto= service.createOrEditStoringLevel(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询")
    @GetMapping("/query")
    public BaseResultViaApiDto<StoringLevelInfoInputDto,StoringLevelInfoOutputDto> queryStoringLevel(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id

    ){
        StoringLevelInfoOutputDto outputDto=new StoringLevelInfoOutputDto();
        StoringLevelInfoInputDto inputDto=new StoringLevelInfoInputDto();
        inputDto.setId(id);
        try{
            outputDto= service.getStoringLevel(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<StoringLevelListInputDto,StoringLevelListOutputDto> queryStoringLevelList(
            @ApiParam(name="pair",value = "pair",required = true)
            @RequestParam(name = "pair") String pair,
              @ApiParam(name="page_size",value = "条数",required = true)
              @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage

    ){
        StoringLevelListOutputDto outputDto=new StoringLevelListOutputDto();
        StoringLevelListInputDto inputDto=new StoringLevelListInputDto();
        inputDto.setPair(pair);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto= service.getStoringLevelList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
