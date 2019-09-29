package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListInputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListOutputDto;

public interface IApiFundAccountControllerService {
    FundAccountLogListOutputDto getFundAccountLogList(FundAccountLogListInputDto inputDto) throws BizException;
}
