package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.appversion.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;

public interface IApiAppVersionControllerService {
    AppVersionOutputDto createOrUpdateAppVersion(AppVersionInputDto inputDto) throws BizException;

    AppVersionInfoOutputDto getAppVersionById(AppVersionInfoInputDto InputDto) throws BizException;

    AppVersionListOutputDto getAppVersionPageData(AppVersionListInputDto inputDto) throws  BizException;

    DeleteOutputDto deleteAppVersion(DeleteInputDto inputDto) throws BizException;
}
