package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.area.*;

public interface IApiAreaControllerService {
    AreaOutputDto createArea(AreaInputDto inputDto) throws BizException;

    AreaOutputDto editArea(AreaInputDto inputDto) throws BizException;

    GetAreaOutputDto getAreaById(GetAreaInputDto inputDto) throws BizException;

    AreaListOutputDto getAreaList(AreaListInputDto inputDto) throws BizException;
}
