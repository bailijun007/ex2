package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.entity.collectfeesetting.*;

public interface IApiCollectFeeSettingControllerService  {
    CollectFeeSettingOutputDto createCollectFeeSetting(CollectFeeSettingInputDto inputDto) throws BizException;

    CollectFeeSettingOutputDto editCollectFeeSetting(CollectFeeSettingInputDto inputDto) throws BizException;

    CollectFeeSettingInfoOutputDto getCollectFeeSettingById(CollectFeeSettingInfoInputDto inputDto) throws BizException;

    CollectFeeSettingListOutputDto getCollectFeeSettingList(CollectFeeSettingListInputDto inputDto) throws BizException;

}
