package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.klineconfig.*;

public interface IApiKlineConfigControllerService {
    KlineConfigOutputDto createKlineConfig(KlineConfigInputDto inputDto) throws BizException;

    KlineConfigInfoOutputDto queryKlineConfigById(KlineConfigInfoInputDto inputDto) throws BizException;

    KlineConfigOutputDto editKlineConfig(KlineConfigInputDto inputDto) throws BizException;

    KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws  BizException;
}
