package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.fundwithdraw.*;

public interface IApiFundWithdrawControllerService {
    //FundWithdrawListOutputDto   getFundWithdrawList(FundWithdrawListInputDto inputDto)throws BizException;

    AuditFundWithdrawOutputDto auditPassFundWithdraw(AuditPassFundWithdrawInputDto inputDto) throws BizException;

    AuditFundWithdrawOutputDto auditFailFundWithdraw(AuditFailFundWithdrawInputDto inputDto) throws BizException;

    FundWithdrawAccountListOutputDto getAccountAllFundWith(FundWithdrawAccountListInputDto inputDto)throws BizException;

}
