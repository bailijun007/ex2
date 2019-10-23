package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingInputDto;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingOutputDto;

public interface IApiImportSettingControllerService {
    ImportSettingOutputDto importSetting(ImportSettingInputDto inputDto)throws BizException;
}
