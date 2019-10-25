package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.symbolrate.SymbolRateListInputDto;
import com.hupa.exp.servermng.entity.symbolrate.SymbolRateListOutputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListInputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListOutputDto;
import com.hupa.exp.servermng.service.def.IApiUserApiControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"apiUserApiController"})
@RestController
@RequestMapping(path = "/v1/http/userapi",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiUserApiController {

    @Autowired
    private IApiUserApiControllerService service;
    @ApiOperation(value = "查询列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<UserApiListInputDto,UserApiListOutputDto> querySymbolRateList(
            @ApiParam(name="user_name",value = "user_name",required = true)
            @RequestParam(name = "user_name") String userName,
            @ApiParam(name="account_id",value = "account_id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage

    ){
        UserApiListOutputDto outputDto=new UserApiListOutputDto();
        UserApiListInputDto inputDto=new UserApiListInputDto();
        inputDto.setUserName(userName);
        inputDto.setAccountId(accountId);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto= service.getUserApiPageData(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
