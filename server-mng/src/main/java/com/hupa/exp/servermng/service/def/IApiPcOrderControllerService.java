package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageInputDto;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageOutputDto;

public interface IApiPcOrderControllerService {
    PcOrderPageOutputDto getPcOrderPageData(PcOrderPageInputDto inputDto) throws BizException;
}
