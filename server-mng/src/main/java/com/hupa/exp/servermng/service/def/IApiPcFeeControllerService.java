package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcfee.*;

public interface IApiPcFeeControllerService {
    PcFeeOutputDto createOrEditPcFee(PcFeeInputDto inputDto) throws BizException;

    PcFeeInfoOutputDto getPcFeeInfo(PcFeeInfoInputDto inputDto)throws BizException;

    PcFeeListOutputDto getPcFeePageData(PcFeeListInputDto inputDto)throws BizException;
}
