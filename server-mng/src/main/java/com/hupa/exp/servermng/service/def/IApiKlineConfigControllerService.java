package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.*;

import java.util.List;

public interface IApiKlineConfigControllerService {
    KlineConfigOutputDto createKlineConfig(KlineConfigInputDto inputDto) throws BizException;

    KlineConfigInfoOutputDto queryKlineConfigById(KlineConfigInfoInputDto inputDto) throws BizException;

    KlineConfigOutputDto editKlineConfig(KlineConfigInputDto inputDto) throws BizException;

    KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws  BizException;

    DeleteOutputDto deleteKlineConfig(DeleteInputDto inputDto) throws BizException;

    RepairKlineListOutputDto getBbCandlePoList(KlineConfigInfoInputDto inputDto) throws BizException;


    KlineConfigOutputDto sendKlineConfig(KlineInfoInputDto inputDto) throws BizException;


}
