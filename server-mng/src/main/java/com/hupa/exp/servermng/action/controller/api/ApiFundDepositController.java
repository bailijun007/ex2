package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListInputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListOutputDto;
import com.hupa.exp.servermng.service.def.IApiFundDepositControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiFundDepositController")
@RestController
@RequestMapping(path="/v1/http/funddeposit",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiFundDepositController {

    @Autowired
    private IApiFundDepositControllerService service;

    /**
     * 用户充币历史记录查询
     * @param asset
     * @param accountId
     * @param depositTime
     * @param depositId
     * @param pageStatus
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取fundwithdraw")
    @GetMapping("/query_account_fund_deposit_list")
    public BaseResultViaApiDto<FundDepositListInputDto,FundDepositListOutputDto> getAccountAllFundWith(
            @ApiParam(name="asset",value = "用户id",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="account_id",value = "用户id",required = true)
            @RequestParam(name = "account_id") Long accountId,
/*            @ApiParam(name="deposit_time",value = "deposit_time",required = true)
            @RequestParam(name = "deposit_time") Long depositTime,
            @ApiParam(name="deposit_id",value = "deposit_id",required = true)
            @RequestParam(name = "deposit_id") Long depositId,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,*/
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        FundDepositListOutputDto outputDto=new FundDepositListOutputDto();
        FundDepositListInputDto inputDto=new FundDepositListInputDto();
        inputDto.setAsset(asset);
        inputDto.setAccountId(accountId);
/*        inputDto.setDepositTime(depositTime);
        inputDto.setDepositId(depositId);
        inputDto.setPageStatus(pageStatus);*/
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);

        try{
            outputDto = service.getAccountAllFundDeposit(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
