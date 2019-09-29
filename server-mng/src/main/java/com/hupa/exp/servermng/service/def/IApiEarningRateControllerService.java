package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.entity.po.expv2.PcEarningRatePo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.earningrate.*;

public interface IApiEarningRateControllerService {
    PcEarningRatePageDataOutputDto queryEarningRatePageData(PcEarningRatePageDataInputDto inputDto) throws BizException;

    PcEarningRateOutputDto createEarningRate(PcEarningRateInputDto inputDto) throws BizException;

    PcEarningRateOutputDto editEarningRate(PcEarningRateInputDto inputDto) throws BizException;

    PcEarningRateInfoOutputDto getEarningRateById(PcEarningRateInfoInputDto inputDto) throws BizException;

    DeleteOutputDto deleteEarningRate(DeleteInputDto inputDto) throws BizException;

    CheckHasEarningRateOutputDto checkHasEarningRate(CheckHasEarningRateInputDto inputDto) throws BizException;
}
