package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListInputDto;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListOutputDto;

/**
 * Created by Administrator on 2020/2/15.
 */
public interface IApiBbAccountTransferControllerService {

    BbTransferListOutputDto getAllBbAccountTransferList(BbTransferListInputDto inputDto)throws BizException;

}
