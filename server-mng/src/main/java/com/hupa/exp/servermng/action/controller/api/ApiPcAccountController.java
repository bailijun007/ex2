package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListInputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPcAccountControllerService;
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

@Api(tags="apiPcAccountController")
@RestController
@RequestMapping(path="/v1/http/pcaccount",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcAccountController {
    @Autowired
    private IApiPcAccountControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiPcAccountController.class);

    @ApiOperation(value = "获取pcaccountlog")
    @GetMapping("/query_pc_account_log_list")
    public BaseResultViaApiDto<PcAccountLogListInputDto,PcAccountLogListOutputDto> getPcAccountLogList(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        PcAccountLogListOutputDto outputDto=new PcAccountLogListOutputDto();
        PcAccountLogListInputDto inputDto=new PcAccountLogListInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getPcAccountLogList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
}
}
