package com.hupa.exp.servermng.action.controller.api;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.servermng.entity.fundwithdraw.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiFundWithdrawControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiFundWithdrawController")
@RestController
@RequestMapping(path="/v1/http/fundwithdraw",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiFundWithdrawController {
    @Autowired
    private IApiFundWithdrawControllerService service;



    private Logger logger = LoggerFactory.getLogger(ApiFundWithdrawController.class);

    @ApiOperation(value = "获取fundwithdraw")
    @GetMapping("/query_fund_withdraw_list")
    public BaseResultViaApiDto<FundWithdrawListInputDto,FundWithdrawListOutputDto> getFundWithdrawList(
            @ApiParam(name="accountName",value = "用户名",required = true)
            @RequestParam(name = "account") String account,
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        FundWithdrawListOutputDto outputDto=new FundWithdrawListOutputDto();
        FundWithdrawListInputDto inputDto=new FundWithdrawListInputDto();
        inputDto.setId(id);
        inputDto.setAccount(account);
        inputDto.setSymbol(asset);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getFundWithdrawList(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取fundwithdraw")
    @GetMapping("/query_account_fund_withdraw_list")
    public BaseResultViaApiDto<FundWithdrawAccountListInputDto,FundWithdrawAccountListOutputDto> getAccountAllFundWith(
            @ApiParam(name="asset",value = "用户id",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="account_id",value = "用户id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="withdraw_time",value = "withdraw_time",required = true)
            @RequestParam(name = "withdraw_time") Long withdrawTime,
            @ApiParam(name="withdraw_id",value = "withdraw_id",required = true)
            @RequestParam(name = "withdraw_id") Long withdrawId,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        FundWithdrawAccountListOutputDto outputDto=new FundWithdrawAccountListOutputDto();
        FundWithdrawAccountListInputDto inputDto=new FundWithdrawAccountListInputDto();
        inputDto.setAsset(asset);
        inputDto.setWithdrawTime(withdrawTime);
        inputDto.setWithdrawId(withdrawId);
        inputDto.setAccountId(accountId);
        inputDto.setPageStatus(pageStatus);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);

        try{
            outputDto = service.getAccountAllFundWith(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取fundwithdraw")
    @PostMapping("/audit_fail")
    public BaseResultViaApiDto<AuditFailFundWithdrawInputDto,AuditFundWithdrawOutputDto> auditFailFundWithdraw(
            @ApiParam(name="accountid",value = "accountid",required = true)
            @RequestParam(name = "accountid") long accountId,
            @ApiParam(name="asset",value = "币",required = true)
            @RequestParam(name = "asset") String symbol,
            @ApiParam(name="withdrawid",value = "主键id",required = true)
            @RequestParam(name = "withdrawid") long withdrawId,
            @ApiParam(name="reason",value = "原因",required = true)
            @RequestParam(name = "reason") String reason
    ){

        AuditFundWithdrawOutputDto outputDto=new AuditFundWithdrawOutputDto();
        AuditFailFundWithdrawInputDto inputDto=new AuditFailFundWithdrawInputDto();
        inputDto.setAccountId(accountId);
        inputDto.setSymbol(symbol);
        inputDto.setWithdrawId(withdrawId);
        inputDto.setReason(reason);
        try{
            outputDto = service.auditFailFundWithdraw(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取fundwithdraw")
    @PostMapping("/audit_pass")
    public BaseResultViaApiDto<AuditPassFundWithdrawInputDto,AuditFundWithdrawOutputDto> auditPassFundWithdraw(
            @ApiParam(name="accountid",value = "accountid",required = true)
            @RequestParam(name = "accountid") long accountId,
            @ApiParam(name="asset",value = "币",required = true)
            @RequestParam(name = "asset") String symbol,
            @ApiParam(name="withdrawid",value = "主键id",required = true)
            @RequestParam(name = "withdrawid") long withdrawId
    ){

        AuditFundWithdrawOutputDto outputDto=new AuditFundWithdrawOutputDto();
        AuditPassFundWithdrawInputDto inputDto=new AuditPassFundWithdrawInputDto();
        inputDto.setAccountId(accountId);
        inputDto.setSymbol(symbol);
        inputDto.setWithdrawId(withdrawId);
        try{
            outputDto = service.auditPassFundWithdraw(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
