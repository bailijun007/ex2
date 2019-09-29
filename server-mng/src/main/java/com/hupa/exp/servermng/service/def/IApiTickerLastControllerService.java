package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataInputDto;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataOutputDto;

public interface IApiTickerLastControllerService {
    PcTickerLastPageDataOutputDto getTickerLastPageData(PcTickerLastPageDataInputDto inputDto) throws BizException;
}
