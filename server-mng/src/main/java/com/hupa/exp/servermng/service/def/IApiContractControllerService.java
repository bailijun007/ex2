package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.exception.MngException;

public interface IApiContractControllerService {
    ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException;

    GetContractOutputDto getContract(GetContractInputDto inputDto) throws ContractException;

    ContractListOutputDto getPosPageByParam(ContractListInputDto inputDto) throws ContractException;

    GetAllActiveContractOutputDto getAllActiveContract(GetAllActiveContractInputDto inputDto) throws BizException;

    GetAllSymbolOutputDto getAllSymbolList(GetAllSymbolInputDto inputDto) throws ContractException;

    CheckHasContractOutputDto checkHasContract(CheckHasContractInputDto inputDto) throws  MngException;

    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws ContractException;

    DeleteOutputDto deleteContract(DeleteInputDto inputDto) throws BizException;

    GetContractListByAssetOutputDto GetContractListByAsset(GetContractListByAssetInputDto inputDto)throws BizException;
}
