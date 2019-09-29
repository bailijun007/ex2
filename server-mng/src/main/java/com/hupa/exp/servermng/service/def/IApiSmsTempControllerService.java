package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.sms.*;

public interface IApiSmsTempControllerService  {
    SmsTempOutputDto createSmsTemp(SmsTempInputDto inputDto) throws BizException;

    SmsTempOutputDto editSmsTemp(SmsTempInputDto inputDto) throws BizException;

    SmsTempListOutputDto querySmsTempList(SmsTempListInputDto inputDto) throws  BizException;

    SmsTempInfoOutputDto querySmsTempById(SmsTempInfoInputDto inputDto) throws  BizException;
}
