package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListInputDto;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListOutputDto;
import com.hupa.exp.servermng.service.def.IApiBbAccountTransferControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Api(tags="apiBbAccountTransferController")
@RestController
@RequestMapping(path="/v1/http/bbTransfer",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiBbAccountTransferController {

    @Autowired
    private IApiBbAccountTransferControllerService service;

    /**
     * 查询币币用户划转历史记录
     * @param accountId
     * @param asset
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取币币划转记录")
    @GetMapping("/query_bbaccount_transfer_list")
    public BaseResultViaApiDto<BbTransferListInputDto,BbTransferListOutputDto> getAllBbAccountTransferList(
            @ApiParam(name="account_id",value = "用户id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="start_time",value = "开始时间",required = false)
            @RequestParam(name = "start_time") String startTime,
            @ApiParam(name="end_time",value = "结束时间",required = false)
            @RequestParam(name = "end_time") String endTime,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        BbTransferListOutputDto outputDto=new BbTransferListOutputDto();
        BbTransferListInputDto inputDto=new BbTransferListInputDto();
        inputDto.setAsset(asset);

        inputDto.setStatrTime(getDefaultDateTime(startTime));
        inputDto.setEndTime(getDefaultDateTime(endTime));
        inputDto.setAccountId(accountId);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getAllBbAccountTransferList(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    private String getDefaultDateTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();
        //如果开始时间，结束时间没有值则给默认今天时间
        if (StringUtils.isEmpty(startTime)) {
            startTime = formatter.format(dateTime);
        }
        return startTime;
    }
}

