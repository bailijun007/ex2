package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.bbfee.BbFeeInputDto;
import com.hupa.exp.servermng.entity.bbfee.BbFeeListInputDto;
import com.hupa.exp.servermng.entity.bbfee.BbFeeListOutputDto;
import com.hupa.exp.servermng.entity.bbfee.BbFeeOutputDto;
import com.hupa.exp.servermng.service.def.IApiBbFeeControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Api(tags = "apiBbFeeController")
@RestController
@RequestMapping(path = "/v1/http/bbfee",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiBbFeeController {

    @Autowired
    private IApiBbFeeControllerService service;

    @ApiOperation(value = "创建或修改手续费规则")
    @PostMapping("/create_or_edit")
    public BaseResultDto<BbFeeInputDto,BbFeeOutputDto> createOrEditBbFee(
            @ApiParam(name="id",value ="id" ,required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="tier",value ="等级" ,required = true)
            @RequestParam(name = "tier") Integer tier,
            @ApiParam(name="compare",value ="包含类型" ,required = true)
            @RequestParam(name = "compare") String compare,
            @ApiParam(name="trading_volume",value ="30天总交易量" ,required = true)
            @RequestParam(name = "trading_volume") BigDecimal tradingVolume,
            @ApiParam(name="maker_fee",value ="挂单成交手续费" ,required = true)
            @RequestParam(name = "maker_fee") BigDecimal makerFee,
            @ApiParam(name="taker_fee",value ="吃单成交手续费" ,required = true)
            @RequestParam(name = "taker_fee") BigDecimal takerFee,
            @ApiParam(name="withdraw_limit",value ="24小时提币额度" ,required = true)
            @RequestParam(name = "withdraw_limit") BigDecimal withdrawLimit
    )
    {
        BbFeeInputDto inputDto=new BbFeeInputDto();
        BbFeeOutputDto outputDto = null;
        try {
            inputDto.setCompare(compare);
            inputDto.setId(id);
            inputDto.setMakerFee(makerFee);
            inputDto.setTakerFee(takerFee);
            inputDto.setTier(tier);
            inputDto.setWithdrawLimit(withdrawLimit);
            inputDto.setTradingVolume(tradingVolume);
            outputDto= service.createOrEditBbFee(inputDto);
        } catch (BizException e) {
            BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询单个")
    @GetMapping("/query")
    public BaseResultDto<BbFeeInputDto,BbFeeOutputDto> getBbFeeById(
            @ApiParam(name="id",value ="id" ,required = true)
            @RequestParam(name = "id") long id
    ) {
        BbFeeInputDto inputDto=new BbFeeInputDto();
        BbFeeOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            outputDto= service.getBbFeeInfo(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<BbFeeListInputDto,BbFeeListOutputDto> getBbFeeById(
            @ApiParam(name="current_page",value ="currentPage" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="pageSize" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize
    )
    {
        BbFeeListInputDto inputDto=new BbFeeListInputDto();
        BbFeeListOutputDto outputDto = null;
        try {
            inputDto.setCurrentPage(currentPage);
            inputDto.setPageSize(pageSize);
            outputDto= service.getBbFeePageData(inputDto);
        } catch (BizException e) {
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
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);
        try{
            outputDto = service.deleteBbFee(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}

