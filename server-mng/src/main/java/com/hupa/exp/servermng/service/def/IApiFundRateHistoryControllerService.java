package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.fundrate.FundRateHistoryListInputDto;
import com.hupa.exp.servermng.entity.fundrate.FundRateHistoryListOutputDto;

public interface IApiFundRateHistoryControllerService {
    FundRateHistoryListOutputDto getFundRateHistoryPageData(FundRateHistoryListInputDto inputDto)throws BizException;
}
