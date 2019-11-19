package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.expthirdapiconfig.*;

public interface IApiExpThirdApiConfigControllerService {
    ExpThirdApiConfigOutputDto createExpThirdApiConfig(ExpThirdApiConfigInputDto inputDto) throws BizException;

    ExpThirdApiConfigOutputDto editExpThirdApiConfig(ExpThirdApiConfigInputDto inputDto) throws BizException;

    ExpThirdApiConfigInfoOutputDto getExpThirdApiConfigById(ExpThirdApiConfigInfoInputDto infoInputDto) throws BizException;

    ExpThirdApiConfigListOutputDto getExpThirdApiConfigPageData(ExpThirdApiConfigListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteExpThirdApiConfig(DeleteInputDto inputDto) throws BizException;
}
