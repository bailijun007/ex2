package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.exchangerate.*;

public interface IApiExchangeRateControllerService {

    ExchangeRateOutputDto createExchangeRate(ExchangeRateInputDto inputDto) throws BizException;

    ExchangeRateOutputDto editExchangeRate(ExchangeRateInputDto inputDto) throws BizException;

    ExchangeRateInfoOutputDto getExchangeRateById(ExchangeRateInfoInputDto inputDto) throws BizException;

    ExchangeRateListOutputDto queryExchangeRateList(ExchangeRateListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteExchangeRate(DeleteInputDto inputDto) throws BizException;
}
