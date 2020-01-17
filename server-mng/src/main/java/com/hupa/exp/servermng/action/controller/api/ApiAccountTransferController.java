package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.fundwithdraw.FundWithdrawAccountListInputDto;
import com.hupa.exp.servermng.entity.fundwithdraw.FundWithdrawAccountListOutputDto;
import com.hupa.exp.servermng.entity.transfer.TransferListInputDto;
import com.hupa.exp.servermng.entity.transfer.TransferListOutputDto;
import com.hupa.exp.servermng.service.def.IApiAccountTransferControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiAccountTransferController")
@RestController
@RequestMapping(path="/v1/http/transfer",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAccountTransferController {

    @Autowired
    private IApiAccountTransferControllerService service;

    /**
     * 查询用户划转历史记录
     * @param accountId
     * @param asset
     * @param transferTime
     * @param transferId
     * @param pageStatus
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取fundwithdraw")
    @GetMapping("/query_account_transfer_list")
    public BaseResultViaApiDto<TransferListInputDto,TransferListOutputDto> getAccountAllFundWith(
            @ApiParam(name="account_id",value = "用户id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset,
        /*    @ApiParam(name="transfer_time",value = "transfer_time",required = true)
            @RequestParam(name = "transfer_time") Long transferTime,
            @ApiParam(name="transfer_id",value = "transfer_id",required = true)
            @RequestParam(name = "transfer_id") Long transferId,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,*/
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        TransferListOutputDto outputDto=new TransferListOutputDto();
        TransferListInputDto inputDto=new TransferListInputDto();
        inputDto.setAsset(asset);
/*        inputDto.setTransferTime(transferTime);
        inputDto.setTransferId(transferId);
        inputDto.setPageStatus(pageStatus);*/
        inputDto.setAccountId(accountId);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);

        try{
            outputDto = service.getAllAccountTransferByAccount(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
