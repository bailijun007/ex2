package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.rakeback.RakeBackInputDto;
import com.hupa.exp.servermng.entity.rakeback.RakeBackListOutputDto;
import com.hupa.exp.servermng.service.def.IApiRakeBackControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返佣数据
 */
@Api(tags = "apiRakeBackController")
@RestController
@RequestMapping(path = "/v1/http/rakeback",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRakeBackController {

    @Autowired
    private IApiRakeBackControllerService service;

    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultDto<RakeBackInputDto,RakeBackListOutputDto> getPcOrderList(
            @ApiParam(name="account_id",value ="账户id" ,required = true)
            @RequestParam(name = "account_id") String accountId,
            @ApiParam(name="rakeBack_Account_Id",value ="返佣人账户id" ,required = true)
            @RequestParam(name = "rakeBack_Account_Id") String rakeBackAccountId,
            @ApiParam(name="asset",value ="交易对" ,required = true)
            @RequestParam(name = "asset") String asset,
            //@ApiParam(name="symbol",value ="交易对" ,required = true)
            //@RequestParam(name = "symbol") String symbol,
            @ApiParam(name="time",value ="返佣时间" ,required = true)
            @RequestParam(name = "time") String time,
            @ApiParam(name="status",value ="返佣状态" ,required = true)
            @RequestParam(name = "status") Integer status,
            @ApiParam(name="type",value ="类型" ,required = true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name="current_page",value ="页码" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="条数" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize
    ) {
        RakeBackInputDto inputDto=new RakeBackInputDto();
        inputDto.setAccountId(accountId);
        inputDto.setRakeBackAccountId(rakeBackAccountId);
        inputDto.setAsset(asset);
        inputDto.setTime(time);
        inputDto.setState(status==null?null:String.valueOf(status));
        inputDto.setType(type==null?null:String.valueOf(type));
        //inputDto.setSymbol(symbol);
        inputDto.setCurrentPage(Long.valueOf(currentPage));
        inputDto.setPageSize(Long.valueOf(pageSize));
        RakeBackListOutputDto outputDto = new RakeBackListOutputDto();
        try {
            outputDto= service.getPosPageByParam(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
