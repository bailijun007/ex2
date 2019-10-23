package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbolrate.*;

public interface IApiSymbolRateControllerService {
    SymbolRateOutputDto createOrEditSymbolRate(SymbolRateInputDto inputDto) throws BizException;

    SymbolRateInfoOutputDto getSymbolRate(SymbolRateInfoInputDto inputDto) throws BizException;

    SymbolRateListOutputDto getSymbolRateList(SymbolRateListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteSymbolRate(DeleteInputDto inputDto) throws BizException;
}
