package com.hupa.exp.servermng.service.def;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.entity.po.expv2.PcContractPo;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;

public interface IApiContractControllerService {
    ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException;

    GetContractOutputDto getContract(GetContractInputDto inputDto) throws ContractException;

    ContractListOutputDto selectPosPageByParam(ContractListInputDto inputDto) throws ContractException;

    CheckHasContractOutputDto checkHasContract(CheckHasContractInputDto inputDto)throws ContractException;

    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws ContractException;
}