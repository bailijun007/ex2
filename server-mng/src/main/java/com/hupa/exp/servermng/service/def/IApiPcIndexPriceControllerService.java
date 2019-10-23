package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListInputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListOutputDto;

public interface IApiPcIndexPriceControllerService {
    PcIndexPriceListOutputDto getPcIndexPricePageData(PcIndexPriceListInputDto inputDto)throws BizException;
}
