package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.storinglevel.*;

public interface IApiStoringLevelControllerService {
    StoringLevelOutputDto createOrEditStoringLevel(StoringLevelInputDto inputDto) throws BizException;

    StoringLevelInfoOutputDto getStoringLevel(StoringLevelInfoInputDto inputDto) throws BizException;

    StoringLevelListOutputDto getStoringLevelList(StoringLevelListInputDto inputDto) throws BizException;

}
