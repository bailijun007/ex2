package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListInputDto;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListOutputDto;

public interface IApiPcMakePriceHistoryControllerService {
    PcMakePriceHistoryListOutputDto getPcMakePriceHistoryPageData(PcMakePriceHistoryListInputDto inputDto)throws BizException;
}
