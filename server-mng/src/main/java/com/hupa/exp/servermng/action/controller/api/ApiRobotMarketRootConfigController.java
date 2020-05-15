package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
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
@RequestMapping(path = "/v1/http/marketControl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRobotMarketRootConfigController {

    @Autowired
    private IApiRobotMarketRootConfigControllerService service;

    @GetMapping("/query_list")
    public BaseResultViaApiDto<RobotMarketRootConfigInputDto, RobotMarketRootConfigListOutputDto> pageQuery(
            @ApiParam(name = "exp_area_type", value = "类型：1.pc,2.bb", required = false)
            @RequestParam(name = "exp_area_type") Integer expAreaType,
            @ApiParam(name = "page_size", value = "条数", required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name = "page_no", value = "页码", required = true)
            @RequestParam(name = "page_no") Integer pageNo
    ) {
        RobotMarketRootConfigListOutputDto outputDto = new RobotMarketRootConfigListOutputDto();
        RobotMarketRootConfigInputDto inputDto = new RobotMarketRootConfigInputDto();
        inputDto.setExpAreaType(expAreaType);
        if (expAreaType==0) {
            inputDto.setExpAreaType(null);
        }
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
    public BaseResultViaApiDto<RobotMarketRootConfigInputDto,RobotMarketRootConfigOutputDto> createOrEditAsset(
            @ApiParam(name="id",value = "Id",required = false)
            @RequestParam(name = "id") long id,
            @ApiParam(name="expAreaType",value = "类型",required = true)
            @RequestParam(name = "expAreaType") Integer expAreaType,
            @ApiParam(name="createOrderUrl",value = "创建订单url",required = true)
            @RequestParam(name = "createOrderUrl") String createOrderUrl,
            @ApiParam(name="cancelOrderUrl",value = "取消订单url",required = true)
            @RequestParam(name = "cancelOrderUrl") String cancelOrderUrl,
            @ApiParam(name="queryOrderUrl",value = "查询订单url",required = true)
            @RequestParam(name = "queryOrderUrl") String queryOrderUrl
    ) {
        RobotMarketRootConfigInputDto inputDto=new RobotMarketRootConfigInputDto();
        inputDto.setId(id);
        inputDto.setExpAreaType(expAreaType);
        inputDto.setCreateOrderUrl(createOrderUrl);
        inputDto.setCancelOrderUrl(cancelOrderUrl);
        inputDto.setQueryOrderUrl(queryOrderUrl);
        RobotMarketRootConfigOutputDto outputDto=new RobotMarketRootConfigOutputDto();
        try {
            if(StringUtils.isEmpty(createOrderUrl)||StringUtils.isEmpty(cancelOrderUrl)||StringUtils.isEmpty(queryOrderUrl)){
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
    public BaseResultViaApiDto<RobotMarketRootConfigInputDto, RobotMarketRootConfigOutputDto> queryById(
            @ApiParam(name = "id", value = "id", required = true)
            @RequestParam(name = "id") Long id
    ) {
        RobotMarketRootConfigOutputDto outputDto = new RobotMarketRootConfigOutputDto();
        RobotMarketRootConfigInputDto inputDto = new RobotMarketRootConfigInputDto();
        inputDto.setId(id);
        try {
            outputDto = service.queryById(id);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

}
