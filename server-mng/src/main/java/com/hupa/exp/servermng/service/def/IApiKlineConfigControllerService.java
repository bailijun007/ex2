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


    /**
     * 查询某个时间段数据K线
     * @param inputDto
     * @return
     * @throws BizException
     */
    RepairKlineListOutputDto getIntervalKline(KlineConfigInputDto inputDto) throws BizException;

    /**
     * 发通知，补充一段时间K线
     * @param inputDto
     * @return
     * @throws BizException
     */
    KlineConfigOutputDto repairKlineConfig(KlineConfigInputDto  inputDto) throws BizException;//KlineConfigInfoInputDto

    /**
     * 撤销某个时间段k线数据
     * @param inputDto
     * @return
     * @throws BizException
     */
    KlineConfigOutputDto getRevokeKline(KlineConfigInputDto inputDto) throws BizException;

    /**
     * 手动修补某个时间段K线
     * @param inputDto
     * @return
     * @throws BizException
     */
    KlineConfigOutputDto getManualKline(KlineConfigInputDto inputDto) throws BizException;

    /**
     * 修补第三方历史K线数据
     * @param inputDto
     * @return
     * @throws BizException
     */
    KlineConfigOutputDto getThirdDataKline(KlineConfigInputDto inputDto) throws BizException;

    /**
     * 按分钟级别的修补，第三方历史K线数据
     * @param inputDto
     * @return
     * @throws BizException
     */
    KlineConfigOutputDto getThirdDataIntervalKline(KlineConfigInputDto inputDto) throws BizException;


    KlineConfigOutputDto getResetKline(KlineConfigInputDto inputDto) throws BizException;


}
