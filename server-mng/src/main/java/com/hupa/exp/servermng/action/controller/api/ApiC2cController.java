package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.c2c.*;
import com.hupa.exp.servermng.service.def.IApiC2cControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(tags = "apiC2cController")
@RestController
@RequestMapping(path = "/v1/http/c2c",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiC2cController {

    @Autowired
    private IApiC2cControllerService apiC2cControllerService;

    /**
     * 查询C2C列表
     * @param userId
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取c2c列表")
    @GetMapping("/query_c2c_list")
    public BaseResultViaApiDto<C2CListInputDto,C2CListOutputDto> getAccountAllFundWith(
            @ApiParam(name="user_Id",value = "用户id",required = true)
            @RequestParam(name = "user_Id") Long userId,
            @ApiParam(name="approval_status",value = "用户id",required = true)
            @RequestParam(name = "approval_status") Integer approvalStatus,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        C2CListOutputDto outputDto=new C2CListOutputDto();
        C2CListInputDto inputDto=new C2CListInputDto();
        inputDto.setUserId(userId);
        inputDto.setApprovalStatus(approvalStatus);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = apiC2cControllerService.findAllC2cList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取银行卡列表")
    @GetMapping("/query_userCardList")
    public BaseResultViaApiDto<BankCardInputDto,BankCardListOutputDto> getUserCardList(
            @ApiParam(name="user_Id",value = "用户id",required = true)
            @RequestParam(name = "user_Id") Long userId
    ){
        BankCardListOutputDto outputDto = new BankCardListOutputDto();
        BankCardInputDto inputDto=new BankCardInputDto();
        inputDto.setUserId(userId);
        try{
            outputDto = apiC2cControllerService.getBankCard(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    /**
     * C2C审核
     * @param approvalId
     * @param approvalStatus
     * @return
     */
    @ApiOperation(value = "C2C审核")
    @PostMapping("/c2c_approval")
    public BaseResultViaApiDto<AuditC2CInputDto,AuditC2COutputDto> auditC2cApproval(
            @ApiParam(name="approval_Id",value = "主键id",required = true)
            @RequestParam(name = "approval_Id") long approvalId,
            @ApiParam(name="approval_Status",value = "审核状态",required = true)
            @RequestParam(name = "approval_Status") Integer approvalStatus){

        AuditC2COutputDto outputDto=new AuditC2COutputDto();
        AuditC2CInputDto inputDto=new AuditC2CInputDto();
        inputDto.setId(approvalId);
        inputDto.setApprovalStatus(approvalStatus);
        try{
            outputDto = apiC2cControllerService.auditC2c(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }








}
