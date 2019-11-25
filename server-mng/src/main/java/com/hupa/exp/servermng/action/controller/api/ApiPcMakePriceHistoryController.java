package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListInputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListOutputDto;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListInputDto;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcIndexPriceControllerService;
import com.hupa.exp.servermng.service.def.IApiPcMakePriceHistoryControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ApiPcMakePriceHistoryController")
@RestController
@RequestMapping(path = "/v1/http/makeprice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcMakePriceHistoryController {
    @Autowired
    private IApiPcMakePriceHistoryControllerService service;
    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<PcMakePriceHistoryListInputDto,PcMakePriceHistoryListOutputDto> getPcFeeById(
            @ApiParam(name="year",value ="年" ,required = true)
            @RequestParam(name = "year") String year,
            @ApiParam(name="asset",value ="币" ,required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="symbol",value ="交易对" ,required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="current_page",value ="页码" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="条数" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize

    )
    {
        PcMakePriceHistoryListInputDto inputDto=new PcMakePriceHistoryListInputDto();
        inputDto.setYear(year);
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        PcMakePriceHistoryListOutputDto outputDto=new PcMakePriceHistoryListOutputDto();
        try {
            outputDto= service.getPcMakePriceHistoryPageData(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
