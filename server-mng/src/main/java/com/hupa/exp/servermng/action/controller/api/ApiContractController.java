package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiContractControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiContractController")
@RestController
@RequestMapping(path = "/v1/http/contract",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiContractController {
    //@Autowired
    //private SessionHelper sessionHelper;
    @Autowired
    private IApiContractControllerService iApiContractControllerService;

    //private Logger logger = LoggerFactory.getLogger(ApiContractController.class);

    @ApiOperation(value = "新增或修改交易对")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> createOrEditContract(
            @ApiParam(name="id",value = "永续合约Id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "标的符号",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="symbol_type",value = "标的类型",required = true)
            @RequestParam(name = "symbol_type") Integer symbolType,
            @ApiParam(name="asset",value = "标的类型",required = true)
            @RequestParam(name = "asset") String asset,
//            @ApiParam(name="currency",value = "计价货币符号",required = true)
//            @RequestParam(name = "currency") String currency,
            @ApiParam(name="precision",value = "精度",required = true)
            @RequestParam(name = "precision") Integer precision,
            @ApiParam(name="contract_type",value = "合约类型",required = true)
            @RequestParam(name = "contract_type") Integer contractType,
            @ApiParam(name="contract_group",value = "合约分组",required = true)
            @RequestParam(name = "contract_group") Integer contractGroup,
            @ApiParam(name="settle_price",value = "结算金额",required = true)
            @RequestParam(name = "settle_price") BigDecimal settlePrice,
            @ApiParam(name="contract_name",value = "合约名称",required = true)
            @RequestParam(name = "contract_name") String contractName,
            @ApiParam(name="contract_name_split",value = "合约名称分隔符",required = true)
            @RequestParam(name = "contract_name_split") String contractNameSplit,
            @ApiParam(name="display_name",value = "合约展示名",required = true)
            @RequestParam(name = "display_name") String displayName,
            @ApiParam(name="display_name_split",value = "合约展示名",required = true)
            @RequestParam(name = "display_name_split") String displayNameSplit,
            @ApiParam(name="default_price",value = "默认交易价",required = true)
            @RequestParam(name = "default_price") BigDecimal defaultPrice,
            @ApiParam(name="last_price",value = "最新成交价",required = true)
            @RequestParam(name = "last_price") BigDecimal lastPrice,
            @ApiParam(name="face_value",value = "合约面值",required = true)
            @RequestParam(name = "face_value") Integer faceValue,
            @ApiParam(name="step",value = "永续合约步进",required = true)
            @RequestParam(name = "step") BigDecimal step,
            @ApiParam(name="quote_currency",value = "合约面值计价货币",required = true)
            @RequestParam(name = "quote_currency") String quoteCurrency,
            @ApiParam(name="base_currency",value = "基础货币",required = true)
            @RequestParam(name = "base_currency") String baseCurrency,
            @ApiParam(name="settle_currency",value = "结算货币",required = true)
            @RequestParam(name = "settle_currency") String settleCurrency,
            @ApiParam(name="face_currency",value = "合约面值货币",required = true)
            @RequestParam(name = "face_currency") String faceCurrency,
            @ApiParam(name="sort",value = "顺序",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="status",value = "状态",required = true)
            @RequestParam(name = "status") Integer status,
            @ApiParam(name="enable_create",value = "开启下单",required = true)
            @RequestParam(name = "enable_create") Integer enableCreate,
            @ApiParam(name="enable_cancel",value = "开启撤单",required = true)
            @RequestParam(name = "enable_cancel") Integer enableCancel,
            @ApiParam(name="privilege",value = "永续合约权限")
            @RequestParam(name = "privilege",required = false) Integer privilege,
            @ApiParam(name="contract_chinese_name",value = "合约中文名",required = true)
            @RequestParam(name = "contract_chinese_name") String contractChineseName
    )
    {
        ContractInputDto inputDto=new ContractInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        inputDto.setSymbolType(symbolType);
        inputDto.setAsset(asset);
        //inputDto.setCurrency(currency);
        inputDto.setPrecision(precision);
        inputDto.setContractName(contractName);
        inputDto.setContractType(contractType);//合约类型（新加）
        inputDto.setContractGroup(contractGroup);//合约分组（新加）
        inputDto.setSettlePrice(settlePrice);//结算金额（新加）
        inputDto.setContractNameSplit(contractNameSplit);//合约名分割符（新加）
        inputDto.setDisplayName(displayName);
        inputDto.setDefaultPrice(defaultPrice);
        inputDto.setLastPrice(lastPrice);
        inputDto.setDisplayNameSplit(displayNameSplit);
        inputDto.setStep(step);
        inputDto.setFaceValue(faceValue);
        inputDto.setSort(sort);
        inputDto.setStatus(status);
        inputDto.setEnableCreate(enableCreate);
        inputDto.setEnableCancel(enableCancel);
        inputDto.setPrivilege(privilege);
        inputDto.setQuoteCurrency(quoteCurrency);
        inputDto.setBaseCurrency(baseCurrency);
        inputDto.setSettleCurrency(settleCurrency);
        inputDto.setFaceCurrency(faceCurrency);
        inputDto.setContractChineseName(contractChineseName);
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
    public BaseResultViaApiDto<ContractInputDto,GetContractOutputDto> getContract(
            @ApiParam(name="id",value = "永续合约Id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        ContractInputDto inputDto=new ContractInputDto();
        GetContractOutputDto outputDto=new GetContractOutputDto();
        try {
            inputDto.setId(id);
            outputDto=iApiContractControllerService.getContract(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<ContractListInputDto,ContractListOutputDto> getContractList(//
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
        ContractListInputDto inputDto=new ContractListInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        ContractListOutputDto outputDto=new ContractListOutputDto();
        try {
            outputDto=iApiContractControllerService.getPosPageByParam(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否已存在")
    @PostMapping(path = "/check_has_contract")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> checkHasContract(
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
        ContractInputDto inputDto=new ContractInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setDisplayName(displayName);
        ContractOutputDto outputDto = null;
        try {
            outputDto=iApiContractControllerService.checkHasContract(inputDto);
        } catch (MngException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否有最新成交价")
    @PostMapping(path = "/check_last_price")
    public BaseResultViaApiDto<CheckHasLastPriceInputDto,CheckHasLastPriceOutputDto> checkHasLastPrice(
       @ApiParam(name="symbol",value = "标的符号",required = true)
       @RequestParam(name = "symbol",required = false) String symbol
    )
    {
        CheckHasLastPriceInputDto inputDto=new CheckHasLastPriceInputDto();
        inputDto.setSymbol(symbol);
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
        try {
            outputDto=iApiContractControllerService.checkHasLastPrice(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "检查是否已存在")
    @GetMapping(path = "/get_all_symbol")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> getActiveSymbol() {
        ContractInputDto inputDto = new ContractInputDto();
        ContractOutputDto outputDto = null;
        try {
            outputDto=iApiContractControllerService.getAllSymbolList(inputDto);
        } catch (ContractException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取有效交易对")
    @GetMapping(path = "/get_all_contract")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> getActiveContract()
    {
        ContractInputDto inputDto=new ContractInputDto();
        ContractOutputDto outputDto = null;
        try {
            outputDto=iApiContractControllerService.getAllActiveContract(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取asset对应的symbol")
    @GetMapping(path = "/get_contract_list_by_asset")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> GetContractListByAsset(
            @ApiParam(name="asset",value = "标的符号",required = true)
            @RequestParam(name = "asset",required = false) String asset
    )
    {
        ContractInputDto inputDto=new ContractInputDto();
        inputDto.setAsset(asset);
        ContractOutputDto outputDto = null;
        try {
            outputDto=iApiContractControllerService.GetContractListByAsset(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteContract(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);
        try{
            outputDto = iApiContractControllerService.deleteContract(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    /**
     * 根据组，查询每组，有多少合约交易对
     * 合约交易对,每组只能设置3个
     * @param groupContract
     * @return
     */
    @ApiOperation(value = "获取组的数量")
    @GetMapping(path = "/getContractGroupNum")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> getContractGroupNum(
            @ApiParam(name="contract_group",value = "合约组id",required = true)
            @RequestParam(name = "contract_group") Integer groupContract
    ) {
        ContractInputDto inputDto = new ContractInputDto();
        ContractOutputDto outputDto = null;
        try {
            inputDto.setContractGroup(groupContract);
            outputDto=iApiContractControllerService.getContractGroupNum(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取所有的合约交易对")
    @GetMapping(path = "/get_contract_list_by_all")
    public BaseResultViaApiDto<ContractInputDto,ContractOutputDto> findContractListByAll(
            @ApiParam(name="asset",value = "标的符号",required = true)
            @RequestParam(name = "asset",required = false) String asset) {
        ContractInputDto inputDto = new ContractInputDto();
        ContractOutputDto outputDto = null;
        try {
            inputDto.setAsset(asset);
            outputDto=iApiContractControllerService.findContractListByAll(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



}
