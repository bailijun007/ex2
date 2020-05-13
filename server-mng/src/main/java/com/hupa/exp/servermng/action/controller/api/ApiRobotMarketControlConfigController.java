package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiRobotMarketControlConfigControllerService;
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
@RequestMapping(path = "/v1/http/market_control_config", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRobotMarketControlConfigController {

    @Autowired
    private IApiRobotMarketControlConfigControllerService service;

    @GetMapping("/query_list")
    public BaseResultViaApiDto<RobotMarketControlConfigInputDto, RobotMarketControlConfigListOutputDto> pageQuery(
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
        RobotMarketControlConfigListOutputDto outputDto = new RobotMarketControlConfigListOutputDto();
        RobotMarketControlConfigInputDto inputDto = new RobotMarketControlConfigInputDto();
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
    public BaseResultViaApiDto<RobotMarketControlConfigInputDto, RobotMarketControlConfigOutputDto> createOrEditAsset(
            @ApiParam(name="id",value = "Id",required = false)
            @RequestParam(name = "id") long id,
            @ApiParam(name="asset",value = "资产",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value = "交易对",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="expAreaType",value = "类型",required = true)
            @RequestParam(name = "expAreaType") Integer expAreaType,
            @ApiParam(name="controlEnable",value = "控制开关",required = false)
            @RequestParam(name = "controlEnable") Integer controlEnable,
            @ApiParam(name="min_space",value = "最小价格间隔距离",required = false)
            @RequestParam(name = "minSpace") BigDecimal minSpace,
            @ApiParam(name="maxSpace",value = "最大价格间隔距离",required = false)
            @RequestParam(name = "maxSpace") BigDecimal maxSpace,
            @ApiParam(name="minSwing",value = "最小价格摆动",required = false)
            @RequestParam(name = "minSwing") BigDecimal minSwing,
            @ApiParam(name="maxSwing",value = "最大价格摆动",required = false)
            @RequestParam(name = "maxSwing") BigDecimal maxSwing,
            @ApiParam(name="minOrderNumber",value = "最小下单量",required = false)
            @RequestParam(name = "minOrderNumber") BigDecimal minOrderNumber,
            @ApiParam(name="maxOrderNumber",value = "最大下单量",required = false)
            @RequestParam(name = "maxOrderNumber") BigDecimal maxOrderNumber,
            @ApiParam(name="minFrequency",value = "行情控制最小频率",required = false)
            @RequestParam(name = "minFrequency") Integer minFrequency,
            @ApiParam(name="maxFrequency",value = "行情控制最大频率",required = false)
            @RequestParam(name = "maxFrequency") Integer maxFrequency
    ) {
        RobotMarketControlConfigInputDto inputDto=new RobotMarketControlConfigInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setExpAreaType(expAreaType);
        inputDto.setControlEnable(controlEnable);
        inputDto.setMinSpace(minSpace);
        inputDto.setMaxSpace(maxSpace);
        inputDto.setMinSwing(minSwing);
        inputDto.setMaxSwing(maxSwing);
        inputDto.setMinOrderNumber(minOrderNumber);
        inputDto.setMaxOrderNumber(maxOrderNumber);
        inputDto.setMinFrequency(minFrequency);
        inputDto.setMaxFrequency(maxFrequency);

        RobotMarketControlConfigOutputDto outputDto=new RobotMarketControlConfigOutputDto();
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
    public BaseResultViaApiDto<RobotMarketControlConfigInputDto, RobotMarketControlConfigOutputDto> queryById(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(name = "id") Long id
    ) {
        RobotMarketControlConfigOutputDto outputDto = new RobotMarketControlConfigOutputDto();
        RobotMarketControlConfigInputDto inputDto = new RobotMarketControlConfigInputDto();
        inputDto.setId(id);
        try {
            outputDto = service.queryById(id);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

}
