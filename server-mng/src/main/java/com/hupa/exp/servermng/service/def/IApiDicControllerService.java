package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dic.*;

public interface IApiDicControllerService {
   DicAllListOutputDto queryDicListByType(DicAllListInputDto inputDto) throws BizException;

   DicAllListOutputDto queryParentDic(DicAllListInputDto inputDto) throws BizException;

   DicOutputDto createDic(DicInputDto inputDto) throws BizException;

   DicOutputDto editDic(DicInputDto inputDto) throws BizException;

   DicInfoOutputDto getDicById(DicInfoInputDto inputDto) throws BizException;

   DicListOutputDto queryDicList(DicListInputDto inputDto) throws BizException;

   DeleteOutputDto deleteDic(DeleteInputDto inputDto) throws BizException;


}
