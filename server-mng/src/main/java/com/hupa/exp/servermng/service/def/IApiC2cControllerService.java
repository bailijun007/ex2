package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.c2c.*;

import java.util.List;

/**
 * Created by Administrator on 2020/1/14.
 */
public interface IApiC2cControllerService {

    public C2CListOutputDto findAllC2cList(C2CListInputDto inputDto) throws BizException;

    public AuditC2COutputDto auditC2c(AuditC2CInputDto inputDto) throws BizException;

    public BankCardListOutputDto getBankCard(BankCardInputDto inputDto) throws BizException;


}
