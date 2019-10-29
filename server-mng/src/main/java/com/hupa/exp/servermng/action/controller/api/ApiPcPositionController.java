package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.pcfee.PcFeeListInputDto;
import com.hupa.exp.servermng.entity.pcfee.PcFeeListOutputDto;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageInputDto;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPcOrderControllerService;
import com.hupa.exp.servermng.service.def.IApiPcPositionControllerService;
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

@Api(tags = "apiPcFeeController")
@RestController
@RequestMapping(path = "/v1/http/pcposition",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcPositionController {
    @Autowired
    private IApiPcPositionControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiPcOrderController.class);

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<PcPositionPageInputDto,PcPositionPageOutputDto> getPcFeeById(
            @ApiParam(name="account",value ="账号" ,required = true)
            @RequestParam(name = "account") String account,
            @ApiParam(name="pos_id",value ="仓位Id" ,required = true)
            @RequestParam(name = "pos_id") Long posId,
            @ApiParam(name="account_id",value ="账户Id" ,required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value ="资产" ,required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="liq_status",value ="状态" ,required = true)
            @RequestParam(name = "liq_status") Integer liqStatus,
            @ApiParam(name="current_page",value ="页码" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="条数" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize

    )
    {
        PcPositionPageInputDto inputDto=new PcPositionPageInputDto();
        inputDto.setAccount(account);
        inputDto.setPosId(posId);
        inputDto.setAccountId(accountId);
        inputDto.setAsset(asset);
        inputDto.setLiqStatus(liqStatus);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        PcPositionPageOutputDto outputDto=new PcPositionPageOutputDto();
        try {
            outputDto= service.getPcPositionPageData(inputDto);
        } catch (BizException e) {
            e.printStackTrace();
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
