package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.modulelimit.*;

public interface IApiModuleLimitControllerService {
    ModuleLimitOutputDto createOrEditModuleLimit(ModuleLimitInputDto inputDto) throws BizException;

    GetModuleLimitOutputDto getModuleLimitById(GetModuleLimitInputDto inputDto) throws BizException;

    ModuleLimitListOutputDto getModuleLimitList(ModuleLimitListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteModuleLimit(DeleteInputDto inputDto) throws BizException;
}
