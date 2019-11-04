package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListInputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiFundAccountControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiFundAccountController")
@RestController
@RequestMapping(path="/v1/http/fundaccount",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiFundAccountController {

    @Autowired
    private IApiFundAccountControllerService service;
    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiFundAccountController.class);


    @ApiOperation(value = "获取fundaccountlog")
    @GetMapping("/query_fund_account_log_list")
    public BaseResultViaApiDto<FundAccountLogListInputDto,FundAccountLogListOutputDto> getPcAccountLogList(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="account_id",value = "account_id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        FundAccountLogListOutputDto outputDto=new FundAccountLogListOutputDto();
        FundAccountLogListInputDto inputDto=new FundAccountLogListInputDto();
        inputDto.setId(id);
        inputDto.setAccountId(accountId);
        inputDto.setPageStatus(pageStatus);
        inputDto.setAsset(asset);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getFundAccountLogList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
