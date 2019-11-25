package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataInputDto;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiTickerLastControllerService;
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

@Api(tags = "apiPcTickerLastController")
@RestController
@RequestMapping(path = "/v1/http/ticker",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcTickerLastController {
    @Autowired
    private IApiTickerLastControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiPcTickerLastController.class);

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<PcTickerLastPageDataInputDto,PcTickerLastPageDataOutputDto> getPcFeeById(
            @ApiParam(name="pair",value ="交易对" ,required = true)
            @RequestParam(name = "pair") String pair,
            @ApiParam(name="current_page",value ="页码" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="条数" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize

    )
    {
        PcTickerLastPageDataInputDto inputDto=new PcTickerLastPageDataInputDto();
        inputDto.setPair(pair);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        PcTickerLastPageDataOutputDto outputDto=new PcTickerLastPageDataOutputDto();
        try {
            outputDto= service.getTickerLastPageData(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
