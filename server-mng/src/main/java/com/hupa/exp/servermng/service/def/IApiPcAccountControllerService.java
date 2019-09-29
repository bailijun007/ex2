package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListInputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListOutputDto;

public interface IApiPcAccountControllerService {
    PcAccountLogListOutputDto getPcAccountLogList(PcAccountLogListInputDto inputDto) throws BizException;
}
