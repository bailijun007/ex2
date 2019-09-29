package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.information.*;


public interface IApiInformationControllerService {
    GetInformationByTypeOutputDto getInformationByType(GetInformationByTypeInputDto inputDto) throws BizException;

    GetInformationInfoOutputDto getInformationInfo(GetInformationInfoInputDto inputDto) throws BizException;

    InformationOutputDto createOrEditInformation(InformationInputDto inputDto)throws BizException;

    DeleteOutputDto deleteInformation(DeleteInputDto inputDto) throws BizException;


}
