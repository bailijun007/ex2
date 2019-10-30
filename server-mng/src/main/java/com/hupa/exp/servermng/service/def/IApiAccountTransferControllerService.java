package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.transfer.*;

public interface IApiAccountTransferControllerService {
    TransferListOutputDto getAllAccountTransferByAccount(TransferListInputDto inputDto)throws BizException;
}
