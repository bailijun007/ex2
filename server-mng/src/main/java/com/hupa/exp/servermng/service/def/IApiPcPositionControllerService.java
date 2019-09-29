package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageInputDto;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageOutputDto;

public interface IApiPcPositionControllerService {
    PcPositionPageOutputDto getPcPositionPageData(PcPositionPageInputDto inputDto) throws BizException;;
}
