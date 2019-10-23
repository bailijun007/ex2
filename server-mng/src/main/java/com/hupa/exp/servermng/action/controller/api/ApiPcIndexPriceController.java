package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.pcfee.PcFeeListInputDto;
import com.hupa.exp.servermng.entity.pcfee.PcFeeListOutputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListInputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcIndexPriceControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "apiPcIndexPriceController")
@RestController
@RequestMapping(path = "/v1/http/pcindexprice",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiPcIndexPriceController {

    @Autowired
    private IApiPcIndexPriceControllerService service;
    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<PcIndexPriceListInputDto,PcIndexPriceListOutputDto> getPcFeeById(
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
        PcIndexPriceListInputDto inputDto=new PcIndexPriceListInputDto();
        inputDto.setAsset(asset);
        inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        PcIndexPriceListOutputDto outputDto=new PcIndexPriceListOutputDto();
        try {
            outputDto= service.getPcIndexPricePageData(inputDto);
        } catch (BizException e) {
            e.printStackTrace();
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
