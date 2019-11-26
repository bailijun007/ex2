package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.fundrate.*;

public interface IApiFundRateSettingControllerService {
    FundRateSettingOutputDto createFundRateSetting(FundRateSettingInputDto inputDto) throws BizException;

    FundRateSettingOutputDto editFundRateSetting(FundRateSettingInputDto inputDto) throws BizException;

    FundRateSettingInfoOutputDto getFundRateSettingById(FundRateSettingInfoInputDto inputDto) throws BizException;

    FundRateSettingListOutputDto getFundRateSettingList(FundRateSettingListInputDto inputDto) throws BizException;

    DeleteOutputDto deleteFundRateSetting(DeleteInputDto inputDto) throws BizException;
}
