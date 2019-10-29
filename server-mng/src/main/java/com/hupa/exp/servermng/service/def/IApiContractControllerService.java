package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.exception.MngException;

public interface IApiContractControllerService {
    ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException;

    GetContractOutputDto getContract(GetContractInputDto inputDto) throws ContractException;

    ContractListOutputDto selectPosPageByParam(ContractListInputDto inputDto) throws ContractException;

    GetAllSymbolOutputDto selectAllSymbolList(GetAllSymbolInputDto inputDto) throws ContractException;

    CheckHasContractOutputDto checkHasContract(CheckHasContractInputDto inputDto) throws  MngException;

    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws ContractException;
}
