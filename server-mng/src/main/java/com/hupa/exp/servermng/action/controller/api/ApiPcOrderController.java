package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageInputDto;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPcOrderControllerService;
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

@Api(tags = "apiPcOrderController")
@RestController
@RequestMapping(path = "/v1/http/pcorder",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcOrderController {

    @Autowired
    private IApiPcOrderControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiPcOrderController.class);

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<PcOrderPageInputDto,PcOrderPageOutputDto> getPcFeeById(
            @ApiParam(name="account",value ="账号" ,required = true)
            @RequestParam(name = "account") String account,
            @ApiParam(name="order_id",value ="委托id" ,required = true)
            @RequestParam(name = "order_id") Long orderId,
            @ApiParam(name="account_id",value ="账户id" ,required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="pair",value ="交易对" ,required = true)
            @RequestParam(name = "pair") String pair,
            @ApiParam(name="close_flag",value ="状态" ,required = true)
            @RequestParam(name = "close_flag") Integer closeFlag,
            @ApiParam(name="status",value ="状态" ,required = true)
            @RequestParam(name = "status") Integer status,
            @ApiParam(name="current_page",value ="页码" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="条数" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize

    )
    {
        PcOrderPageInputDto inputDto=new PcOrderPageInputDto();
        inputDto.setAccount(account);
        inputDto.setOrderId(orderId);
        inputDto.setAccountId(accountId);
        inputDto.setPair(pair);
        inputDto.setCloseFlag(closeFlag);
        inputDto.setStatus(status);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        PcOrderPageOutputDto outputDto=new PcOrderPageOutputDto();
        try {
            outputDto= service.getPcOrderPageData(inputDto);
        } catch (BizException e) {
            e.printStackTrace();
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}