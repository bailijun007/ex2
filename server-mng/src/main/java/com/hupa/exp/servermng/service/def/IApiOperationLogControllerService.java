package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListInputDto;
import com.hupa.exp.servermng.entity.operationlog.OperationLogListOutputDto;

public interface IApiOperationLogControllerService {
    OperationLogListOutputDto getOperationLogList(OperationLogListInputDto inputDto) throws BizException;
}
