package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbolinterest.*;

public interface IApiSymbolInterestControllerService {
    SymbolInterestOutputDto createOrEditSymbolInterest(SymbolInterestInputDto inputDto) throws BizException;

    SymbolInterestInfoOutputDto getSymbolInterest(SymbolInterestInfoInputDto inputDto) throws BizException;

    SymbolInterestListOutputDto getSymbolInterestList(SymbolInterestListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteSymbolInterest(DeleteInputDto inputDto) throws BizException;
}
