package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListInputDto;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiOperationLogControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "apiOperationLogController")
@RestController
@RequestMapping(path = "/v1/http/operationlog")
public class ApiOperationLogController {
    @Autowired
    private IApiOperationLogControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiOperationLogController.class);

@ApiOperation(value = "查询错误日志")
    @GetMapping("query_list")
    public BaseResultDto<OperationLogListInputDto,OperationLogListOutputDto> getOperationLogList(
    @ApiParam(name="operation_module",value = "操作模块")
    @RequestParam(name="operation_module")String operationModule,
    @ApiParam(name="operation_type",value = "操作类型")
    @RequestParam(name="operation_type")String operationType,
    @ApiParam(name="current_page",value = "页码")
    @RequestParam(name="current_page")Integer currentPage,
    @ApiParam(name="page_size",value = "行数")
    @RequestParam(name="page_size")Integer pageSize
)
{
    OperationLogListInputDto inputDto=new OperationLogListInputDto();
    inputDto.setCurrentPage(currentPage);
    inputDto.setOperationModule(operationModule);
    inputDto.setPageSize(pageSize);
    inputDto.setOperationType(operationType);
    OperationLogListOutputDto outputDto=new OperationLogListOutputDto();
    try {
        outputDto= service.getOperationLogList(inputDto);

    } catch (BizException e) {
        BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
    }
    return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
}
}
