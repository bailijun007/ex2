package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.assetchange.*;

public interface IApiAssetChangeControllerService {
    FundAssetChangeOutputDto getFundAssetChange(FundAssetChangeInputDto inputDto) throws BizException;

    FundAssetChangeListOutputDto getFundAssetChangeList(FundAssetChangeListInputDto inputDto)throws BizException;

    PcAssetChangeOutputDto getPcAssetChange(PcAssetChangeInputDto inputDto) throws BizException;

    PcAssetChangeListOutputDto getPcAssetChangeList(PcAssetChangeListInputDto inputDto)throws BizException;

}
