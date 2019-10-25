package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataInputDto;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataOutputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListInputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListOutputDto;

public interface IApiUserApiControllerService {
    UserApiListOutputDto getUserApiPageData(UserApiListInputDto inputDto) throws BizException;
}
