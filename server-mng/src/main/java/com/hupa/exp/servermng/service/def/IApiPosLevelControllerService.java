package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.poslevel.*;

public interface IApiPosLevelControllerService {
    PosLevelOutputDto createOrEditPosLevel(PosLevelInputDto inputDto) throws BizException;

    PosLevelInfoOutputDto getPosLevel(PosLevelInfoInputDto inputDto) throws BizException;

    PosLevelListOutputDto getPosLevelList(PosLevelListInputDto inputDto) throws BizException;

}