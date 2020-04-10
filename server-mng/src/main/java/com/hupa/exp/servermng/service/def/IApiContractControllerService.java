package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.exception.MngException;

public interface IApiContractControllerService {
    ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException;

    GetContractOutputDto getContract(ContractInputDto inputDto) throws ContractException;

    ContractListOutputDto getPosPageByParam(ContractListInputDto inputDto) throws ContractException;

    ContractOutputDto getAllActiveContract(ContractInputDto inputDto) throws BizException;

    ContractOutputDto getAllSymbolList(ContractInputDto inputDto) throws ContractException;

    ContractOutputDto checkHasContract(ContractInputDto inputDto) throws  MngException;

    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws ContractException;

    DeleteOutputDto deleteContract(DeleteInputDto inputDto) throws BizException;

    ContractOutputDto GetContractListByAsset(ContractInputDto inputDto)throws BizException;

    ContractOutputDto getContractGroupNum(ContractInputDto inputDto) throws BizException;
    //查询pc合约列表
    ContractOutputDto findContractListByAll(ContractInputDto inputDto) throws BizException;


}
