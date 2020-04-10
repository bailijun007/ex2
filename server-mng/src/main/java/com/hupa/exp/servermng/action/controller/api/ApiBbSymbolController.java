package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbol.*;
import com.hupa.exp.servermng.exception.BbSymbolException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiBbSymbolControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;



@Api(tags = "apiBbSymbolController")
@RestController
@RequestMapping(path = "/v1/http/bbSymbol",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiBbSymbolController {

    @Autowired
    private IApiBbSymbolControllerService iApiBbSymbolControllerService;

    @ApiOperation(value = "新增或修改交易对")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> createOrEditBbSymbol(
            @ApiParam(name="id",value = "币币货币对Id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "标的符号",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="asset",value = "标的类型",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="precision",value = "精度",required = true)
            @RequestParam(name = "precision") Integer precision,
            @ApiParam(name="bb_group_id",value = "币币分组",required = true)
            @RequestParam(name = "bb_group_id") Integer bbGroupId,
            @ApiParam(name="bbSymbol_name",value = "币币名称",required = true)
            @RequestParam(name = "bbSymbol_name") String bbSymbolName,
            @ApiParam(name="bbSymbol_name_split",value = "币币名称分隔符",required = true)
            @RequestParam(name = "bbSymbol_name_split") String bbSymbolNameSplit,
            @ApiParam(name="display_name",value = "币币展示名",required = true)
            @RequestParam(name = "display_name") String displayName,
            @ApiParam(name="display_name_split",value = "币币展示名",required = true)
            @RequestParam(name = "display_name_split") String displayNameSplit,
            @ApiParam(name="step",value = "币币步进",required = true)
            @RequestParam(name = "step") BigDecimal step,
            @ApiParam(name="number_precision",value = "数量精度",required = true)
            @RequestParam(name = "number_precision") Integer numberPrecision,
            @ApiParam(name="minTradeNumber",value = "最小交易量",required = true)
            @RequestParam(name = "minTradeNumber") BigDecimal minTradeNumber,
            @ApiParam(name="sort",value = "顺序",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="status",value = "状态",required = true)
            @RequestParam(name = "status") Integer status,
            @ApiParam(name="enable_create",value = "开启下单",required = true)
            @RequestParam(name = "enable_create") Integer enableCreate,
            @ApiParam(name="enable_cancel",value = "开启撤单",required = true)
            @RequestParam(name = "enable_cancel") Integer enableCancel,
            @ApiParam(name="symbol_chinese",value = "币币中文名",required = true)
            @RequestParam(name = "symbol_chinese") String symbolChinese
    )
    {
        BbSymbolInputDto inputDto=new BbSymbolInputDto();
        BbSymbolOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            inputDto.setSymbol(symbol);
            inputDto.setAsset(asset);
            inputDto.setPrecision(precision);
            inputDto.setBbSymbolName(bbSymbolName);
            inputDto.setBbGroupId(bbGroupId);//分组（新加）
            inputDto.setBbSymbolNameSplit(bbSymbolNameSplit);//名分割符（新加）
            inputDto.setDisplayName(displayName);
            inputDto.setDisplayNameSplit(displayNameSplit);
            inputDto.setStep(step);
            inputDto.setSort(sort);
            inputDto.setStatus(status);
            inputDto.setNumberPrecision(numberPrecision);
            inputDto.setMinTradeNumber(minTradeNumber);
            inputDto.setEnableCreate(enableCreate);//开始下单
            inputDto.setEnableCancel(enableCancel);//开启撤单
            inputDto.setSymbolChinese(symbolChinese);
            outputDto= iApiBbSymbolControllerService.createOrEditBbSymbol(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对详情")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<BbSymbolInputDto,GetBbSymbolOutputDto> getBbSymbol(
            @ApiParam(name="id",value = "币币货币对Id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        BbSymbolInputDto inputDto=new BbSymbolInputDto();
        GetBbSymbolOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            outputDto=iApiBbSymbolControllerService.getBbSymbol(inputDto);
        } catch (BbSymbolException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "分页获取交易对列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<BbSymbolListInputDto,BbSymbolListOutputDto> getBbSymbolList(
        @ApiParam(name="asset",value = "币种",required = true)
        @RequestParam(name = "asset") String asset,
        @ApiParam(name="symbol",value = "标的符号",required = true)
        @RequestParam(name = "symbol",required = false) String symbol,
        @ApiParam(name="page_size",value = "条数",required = true)
        @RequestParam(name = "page_size") Integer pageSize,
        @ApiParam(name="current_page",value = "页码",required = true)
        @RequestParam(name = "current_page") Integer currentPage
    )
    {
        BbSymbolListInputDto inputDto=new BbSymbolListInputDto();
        BbSymbolListOutputDto outputDto = null;
        try {
            inputDto.setAsset(asset);
            inputDto.setSymbol(symbol);
            inputDto.setPageSize(pageSize);
            inputDto.setCurrentPage(currentPage);
            outputDto = iApiBbSymbolControllerService.getPosPageByParam(inputDto);
        } catch (BbSymbolException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否已存在")
    @PostMapping(path = "/check_has_bbSymbol")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> checkHasBbSymbol(
        @ApiParam(name="id",value = "id",required = true)
        @RequestParam(name = "id") Long id,
        @ApiParam(name="asset",value = "币种",required = true)
        @RequestParam(name = "asset") String asset,
        @ApiParam(name="symbol",value = "标的符号",required = true)
        @RequestParam(name = "symbol") String symbol,
        @ApiParam(name="display_name",value = "标的符号",required = true)
        @RequestParam(name = "display_name") String displayName
    )
    {
        BbSymbolInputDto inputDto=new BbSymbolInputDto();
        BbSymbolOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            inputDto.setAsset(asset);
            inputDto.setSymbol(symbol);
            inputDto.setDisplayName(displayName);
            outputDto=iApiBbSymbolControllerService.checkHasBbSymbol(inputDto);
        } catch (MngException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    /*@ApiOperation(value = "检查是否有最新成交价")
    @PostMapping(path = "/check_last_price")
    public BaseResultViaApiDto<CheckHasLastPriceInputDto,CheckHasLastPriceOutputDto> checkHasLastPrice(//
        @ApiParam(name="symbol",value = "标的符号",required = true)
        @RequestParam(name = "symbol",required = false) String symbol
    )
    {
        CheckHasLastPriceInputDto inputDto=new CheckHasLastPriceInputDto();
        inputDto.setSymbol(symbol);
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
        try {
            outputDto=iApiBbSymbolControllerService.checkHasLastPrice(inputDto);
        } catch (BbSymbolException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }*/

    @ApiOperation(value = "获取所有的交易对")
    @GetMapping(path = "/get_all_symbol")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> getActiveSymbol() {
        BbSymbolInputDto inputDto = new BbSymbolInputDto();
        BbSymbolOutputDto outputDto = null;
        try {
            outputDto=iApiBbSymbolControllerService.getAllBbSymbolList(inputDto);
        } catch (BbSymbolException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    /*@ApiOperation(value = "获取有效交易对")
    @GetMapping(path = "/get_all_bbSymbol")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> getActiveBbSymbol()
    {
        BbSymbolInputDto inputDto = new BbSymbolInputDto();
        BbSymbolOutputDto outputDto = null;
        try {
            outputDto=iApiBbSymbolControllerService.getAllActiveBbSymbol(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }*/

    @ApiOperation(value = "获取asset对应的symbol")
    @GetMapping(path = "/get_bbSymbol_list_by_asset")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> GetBbSymbolListByAsset(
            @ApiParam(name="asset",value = "标的符号",required = true)
            @RequestParam(name = "asset") String asset
    )
    {
        BbSymbolInputDto inputDto=new BbSymbolInputDto();
        BbSymbolOutputDto outputDto= null;
        try {
            inputDto.setAsset(asset);
            outputDto=iApiBbSymbolControllerService.GetBbSymbolListByAsset(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteBbSymbol(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    )
    {
        DeleteInputDto inputDto = new DeleteInputDto();
        DeleteOutputDto outputDto = null;
        try{
            inputDto.setIds(ids);
            outputDto = iApiBbSymbolControllerService.deleteBbSymbol(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    /**
     * 根据组，查询每组，有多少币币交易对
     * 币币交易对,每组最多只能设置3个，这个条件很重要
     * 核心
     */
    @ApiOperation(value = "获取组的数量")
    @GetMapping(path = "/getBbSymbolGroupNum")
    public BaseResultViaApiDto<BbSymbolInputDto,BbSymbolOutputDto> getBbSymbolGroupNum(
            @ApiParam(name="bb_group_id",value = "币币组id",required = true)
            @RequestParam(name = "bb_group_id") Integer bbGroupId
    )
    {
        BbSymbolInputDto inputDto=new BbSymbolInputDto();
        BbSymbolOutputDto outputDto = null;
        try {
            inputDto.setBbGroupId(bbGroupId);
            outputDto=iApiBbSymbolControllerService.getBbSymbolGroupNum(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



}
