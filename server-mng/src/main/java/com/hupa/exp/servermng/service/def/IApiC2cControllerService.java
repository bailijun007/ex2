package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.c2c.AuditC2CInputDto;
import com.hupa.exp.servermng.entity.c2c.AuditC2COutputDto;
import com.hupa.exp.servermng.entity.c2c.C2CListInputDto;
import com.hupa.exp.servermng.entity.c2c.C2CListOutputDto;

/**
 * Created by Administrator on 2020/1/14.
 */
public interface IApiC2cControllerService {

    public C2CListOutputDto findAllC2cList(C2CListInputDto inputDto) throws BizException;

    public AuditC2COutputDto auditC2c(AuditC2CInputDto inputDto) throws BizException;

}
