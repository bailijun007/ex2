package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dictype.*;

public interface IApiDicTypeControllerService {
    DicTypeListOutputDto queryDicTypeList(DicTypeListInputDto inputDto) throws BizException;

    DicTypeOutputDto createDicType(DicTypeInputDto inputDto) throws BizException;

    DicTypeOutputDto editDicType(DicTypeInputDto inputDto) throws BizException;

    DicTypeInfoOutputDto getDicTypeById(DicTypeInfoInputDto inputDto) throws BizException;

    DicTypeAllListOutputDto getDicTypeAllList(DicTypeAllListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteDicType(DeleteInputDto inputDto) throws BizException;
}
