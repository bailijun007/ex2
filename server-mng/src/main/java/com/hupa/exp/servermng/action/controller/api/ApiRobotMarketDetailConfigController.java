package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiRobotMarketDetailConfigControllerService;
import com.hupa.exp.servermng.service.def.IApiRobotMarketRootConfigControllerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author BaiLiJun  on 2020/5/12
 */
@RestController
@RequestMapping(path = "/v1/http/market_detail_config", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRobotMarketDetailConfigController {

    @Autowired
    private IApiRobotMarketDetailConfigControllerService service;

    @GetMapping("/query_list")
    public BaseResultViaApiDto<RobotMarketDetailConfigInputDto, RobotMarketDetailConfigListOutputDto> pageQuery(
            @ApiParam(name="asset",value = "币种",required = false)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对",required = false)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name = "exp_area_type", value = "类型：1.pc,2.bb", required = false)
            @RequestParam(name = "exp_area_type") Integer expAreaType,
            @ApiParam(name = "page_size", value = "条数", required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name = "page_no", value = "页码", required = true)
            @RequestParam(name = "page_no") Integer pageNo
    ) {
        RobotMarketDetailConfigListOutputDto outputDto = new RobotMarketDetailConfigListOutputDto();
        RobotMarketDetailConfigInputDto inputDto = new RobotMarketDetailConfigInputDto();
        inputDto.setExpAreaType(expAreaType);
        if (expAreaType==0) {
            inputDto.setExpAreaType(null);
        }
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setPageNo(pageNo);
        inputDto.setPageSize(pageSize);
        try {
            outputDto = service.pageQuery(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }


    @ApiOperation(value = "新增或修改币种")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<RobotMarketDetailConfigInputDto, RobotMarketDetailConfigOutputDto> createOrEditAsset(
            @ApiParam(name="id",value = "Id",required = false)
            @RequestParam(name = "id") long id,
            @ApiParam(name="bidUserId",value = "买入做市用户id",required = false)
            @RequestParam(name = "bidUserId") Long bidUserId,
            @ApiParam(name="askUserId",value = "卖出做市用户id",required = false)
            @RequestParam(name = "askUserId") Long askUserId,
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="expAreaType",value = "类型",required = true)
            @RequestParam(name = "expAreaType") Integer expAreaType,
            @ApiParam(name="marketEnable",value = "是否启用",required = false)
            @RequestParam(name = "marketEnable") Integer marketEnable,
            @ApiParam(name="minOrderNumber",value = "最小的下单的委托量",required = false)
            @RequestParam(name = "minOrderNumber") BigDecimal minOrderNumber,
            @ApiParam(name="maxOrderNumber",value = "最大的下单的委托量",required = false)
            @RequestParam(name = "maxOrderNumber") BigDecimal maxOrderNumber,
            @ApiParam(name="maxOrderBookSize",value = "最大深度数",required = false)
            @RequestParam(name = "maxOrderBookSize") Integer maxOrderBookSize,
            @ApiParam(name="orderBookBidNumber",value = "order_book_bid_number",required = false)
            @RequestParam(name = "orderBookBidNumber") String orderBookBidNumber,
            @ApiParam(name="orderBookAskNumber",value = "orderBookAskNumber",required = false)
            @RequestParam(name = "orderBookAskNumber") String orderBookAskNumber,
            @ApiParam(name="minFrequency",value = "做市最小频率",required = false)
            @RequestParam(name = "minFrequency") Integer minFrequency,
            @ApiParam(name="maxFrequency",value = "做市最大频率",required = false)
            @RequestParam(name = "maxFrequency") Integer maxFrequency
    ) {
        RobotMarketDetailConfigInputDto inputDto=new RobotMarketDetailConfigInputDto();
        inputDto.setId(id);
        inputDto.setExpAreaType(expAreaType);
        inputDto.setBidUserId(bidUserId);
        inputDto.setAskUserId(askUserId);
        inputDto.setMinOrderNumber(minOrderNumber);
        inputDto.setMaxOrderNumber(maxOrderNumber);
        inputDto.setMaxOrderBookSize(maxOrderBookSize);
        inputDto.setOrderBookBidNumber(orderBookBidNumber);
        inputDto.setOrderBookAskNumber(orderBookAskNumber);
        inputDto.setMarketEnable(marketEnable);
        inputDto.setMinFrequency(minFrequency);
        inputDto.setMaxFrequency(maxFrequency);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);

        RobotMarketDetailConfigOutputDto outputDto=new RobotMarketDetailConfigOutputDto();
        try {
            if(StringUtils.isEmpty(asset)||StringUtils.isEmpty(symbol)||expAreaType==null){
                throw new MngException(MngExceptionCode.THE_PARAMETER_CANNOT_BE_NULL);
            }
            if(inputDto.getId()>0){
                outputDto= service.edit(inputDto);
            } else{
                outputDto= service.create(inputDto);
            }
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @GetMapping("/query")
    public BaseResultViaApiDto<RobotMarketDetailConfigInputDto, RobotMarketDetailConfigOutputDto> queryById(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(name = "id") Long id
    ) {
        RobotMarketDetailConfigOutputDto outputDto = new RobotMarketDetailConfigOutputDto();
        RobotMarketDetailConfigInputDto inputDto = new RobotMarketDetailConfigInputDto();
        inputDto.setId(id);
        try {
            outputDto = service.queryById(id);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

}
