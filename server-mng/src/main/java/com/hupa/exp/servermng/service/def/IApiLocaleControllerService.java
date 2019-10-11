package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.locale.*;

public interface IApiLocaleControllerService {
    LocaleOutputDto createLocale(LocaleInputDto inputDto) throws BizException;

    LocaleOutputDto editLocale(LocaleInputDto inputDto) throws BizException;

    LocaleInfoOutputDto getLocaleById(LocaleInfoInputDto inputDto) throws BizException;

    LocaleListOutputDto getLocaleList(LocaleListInputDto inputDto) throws BizException;

    LocaleDeleteOutputDto deleteLocale(LocaleDeleteInputDto inputDto) throws BizException;

    CheckHasLocaleOutputDto checkHasLocale(CheckHasLocaleInputDto inputDto) throws  BizException;

}
