package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.ExpOperationLogBizBo;
import com.hupa.exp.bizother.entity.ExpOperationLogListBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListInputDto;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListOutputDto;
import com.hupa.exp.servermng.service.def.IApiOperationLogControllerService;
import org.apache.pulsar.shade.org.apache.avro.generic.GenericData;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiOperationLogControllerServiceImpl implements IApiOperationLogControllerService {

    @Autowired
    private IExpOperationLogService logService;

    @Override
    public OperationLogListOutputDto getOperationLogList(OperationLogListInputDto inputDto) {
        ExpOperationLogListBizBo listBizBo= logService.getExpOperationLogList(inputDto.getOperationModule(),inputDto.getOperationType(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        OperationLogListOutputDto logListOutputDto=new OperationLogListOutputDto();
        List<OperationLogListOutputDto.OperationLogInfo> logInfos=new ArrayList<>();
        for(ExpOperationLogBizBo bo:listBizBo.getRows())
        {
            OperationLogListOutputDto.OperationLogInfo info=new OperationLogListOutputDto.OperationLogInfo();
            info.setAfter(bo.getAfter());
            info.setBefore(bo.getBefore());
            info.setId(String.valueOf(bo.getId()));
            info.setOperationModule(bo.getOperationModule());
            info.setOperationType(bo.getOperationType());
            info.setUserName(bo.getUserName());
            info.setAccountId(String.valueOf(bo.getAccountId()));
            info.setOperationTime(String.valueOf(bo.getOperationTime()));
            logInfos.add(info);
        }
        logListOutputDto.setRows(logInfos);
        logListOutputDto.setTotal(listBizBo.getTotal());
        logListOutputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        logListOutputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return logListOutputDto;
    }
}
