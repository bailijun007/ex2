package com.hupa.exp.bizother.service.operationlog.def;

import com.hupa.exp.bizother.entity.operationlog.ExpOperationLogListBizBo;

public interface IExpOperationLogService {
     long createOperationLog(long accountId,String userName,String operationModule,String operationType,
                             String before,String after);

     ExpOperationLogListBizBo getExpOperationLogList(String operationModule,String operationType,long currentPage,long pageSize);
}
