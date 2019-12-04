package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.constant.*;

public interface IConstantService
{
    ConstantOutputDto createOrEditConstant(ConstantInputDto inputDto) throws BizException;

    ConstantInfoOutputDto getConstant(ConstantInfoInputDto inputDto) throws BizException;

    ConstantListOutputDto getConstantPage(ConstantListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteConstant(DeleteInputDto inputDto) throws BizException;
}
