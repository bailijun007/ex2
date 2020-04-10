package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.area.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;

public interface IApiAreaControllerService {
    AreaOutputDto createArea(AreaInputDto inputDto) throws BizException;

    AreaOutputDto editArea(AreaInputDto inputDto) throws BizException;

    AreaOutputDto getAreaById(AreaInputDto inputDto) throws BizException;

    AreaListOutputDto getAreaList(AreaListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteArea(DeleteInputDto inputDto) throws BizException;
}
