package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListInputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListOutputDto;

public interface IApiFundDepositControllerService {
    FundDepositListOutputDto getAccountAllFundDeposit(FundDepositListInputDto inputDto)throws BizException;
}
