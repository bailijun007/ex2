package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiContractControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiContractController")
@RestController
@RequestMapping(path = "/v1/http/contract",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiContractController {
    @Autowired
    private SessionHelper sessionHelper;
    @Autowired
    private IApiContractControllerService iApiContractControllerService;

    private Logger logger = LoggerFactory.getLogger(ApiContractController.class);

    @ApiOperation(value = "新增或修改交易对")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> createOrEditContract(
            @ApiParam(name="id",value = "永续合约Id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="pair",value = "标的符号",required = true)
            @RequestParam(name = "pair") String pair,
            @ApiParam(name="pair_type",value = "标的类型",required = true)
            @RequestParam(name = "pair_type") Integer pairType,
            @ApiParam(name="currency",value = "计价货币符号",required = true)
            @RequestParam(name = "currency") String currency,
            @ApiParam(name="precision",value = "精度",required = true)
            @RequestParam(name = "precision") Integer precision,
            @ApiParam(name="contract_name",value = "合约名称",required = true)
            @RequestParam(name = "contract_name") String contractName,
            @ApiParam(name="display_name",value = "合约展示名",required = true)
            @RequestParam(name = "display_name") String displayName,
            @ApiParam(name="display_name_split",value = "合约展示名",required = true)
            @RequestParam(name = "display_name") String displayNameSplit,
            @ApiParam(name="default_price",value = "默认交易价",required = true)
            @RequestParam(name = "default_price") BigDecimal defaultPrice,
            @ApiParam(name="last_price",value = "最新成交价",required = true)
            @RequestParam(name = "last_price") BigDecimal lastPrice,
            @ApiParam(name="face_value",value = "合约面值",required = true)
            @RequestParam(name = "face_value") Integer faceValue,
            @ApiParam(name="step",value = "永续合约步进",required = true)
            @RequestParam(name = "step") BigDecimal step,
            @ApiParam(name="sort",value = "顺序",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="status",value = "状态",required = true)
            @RequestParam(name = "status") Integer status,
            @ApiParam(name="privilege",value = "永续合约权限")
            @RequestParam(name = "privilege",required = false) Integer privilege
    )
    {
        ContractInputDto inputDto=new ContractInputDto();
        inputDto.setId(id);
        inputDto.setPair(pair);
        inputDto.setPairType(pairType);
        inputDto.setCurrency(currency);
        inputDto.setPrecision(precision);
        inputDto.setContractName(contractName);
        inputDto.setDisplayName(displayName);
        inputDto.setDefaultPrice(defaultPrice);
        inputDto.setLastPrice(lastPrice);
        inputDto.setDisplayNameSplit(displayNameSplit);
        inputDto.setStep(step);
        inputDto.setFaceValue(faceValue);
        inputDto.setSort(sort);
        inputDto.setStatus(status);
        inputDto.setPrivilege(privilege);

        ContractOutputDto outputDto=new ContractOutputDto();
        try {
            outputDto= iApiContractControllerService.createOrEditContract(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "获取交易对详情")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<GetContractInputDto,GetContractOutputDto> getContract(
            @ApiParam(name="id",value = "永续合约Id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        GetContractInputDto inputDto=new GetContractInputDto();
        inputDto.setId(id);
        GetContractOutputDto outputDto=new GetContractOutputDto();
        try {
            outputDto=iApiContractControllerService.getContract(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<ContractListInputDto,ContractListOutputDto> getContractList(//
            @ApiParam(name="pair",value = "标的符号",required = true)
            @RequestParam(name = "pair",required = false) String pair,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    )
    {
        ContractListInputDto inputDto=new ContractListInputDto();
        inputDto.setPair(pair);
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        ContractListOutputDto outputDto=new ContractListOutputDto();
        try {
            outputDto=iApiContractControllerService.selectPosPageByParam(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否已存在")
    @PostMapping(path = "/check_has_contract")
    public BaseResultViaApiDto<CheckHasContractInputDto,CheckHasContractOutputDto> checkHasContract(//
                                                                                                    @ApiParam(name="pair",value = "标的符号",required = true)
                                                                                                    @RequestParam(name = "pair",required = false) String pair
    )
    {
        CheckHasContractInputDto inputDto=new CheckHasContractInputDto();
        inputDto.setPair(pair);
        CheckHasContractOutputDto outputDto=new CheckHasContractOutputDto();
        try {
            outputDto=iApiContractControllerService.checkHasContract(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否已存在")
    @PostMapping(path = "/check_last_price")
    public BaseResultViaApiDto<CheckHasLastPriceInputDto,CheckHasLastPriceOutputDto> checkHasLastPrice(//
       @ApiParam(name="pair",value = "标的符号",required = true)
       @RequestParam(name = "pair",required = false) String pair
    )
    {
        CheckHasLastPriceInputDto inputDto=new CheckHasLastPriceInputDto();
        inputDto.setPair(pair);
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
        try {
            outputDto=iApiContractControllerService.checkHasLastPrice(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
