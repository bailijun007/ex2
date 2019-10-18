package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.asset.*;

public interface IApiAssetControllerService {
    AssetOutputDto createAsset(AssetInputDto inputDto) throws BizException;

    AssetOutputDto editAsset(AssetInputDto inputDto) throws BizException;

    GetAssetOutputDto getAssetById(GetAssetInputDto inputDto) throws BizException;

    AssetListOutputDto getAssetList(AssetListInputDto inputDto) throws BizException;

    RealNameListOutPutDto getRealNameList(RealNameListInputDto inputDto) throws BizException;

    CheckHasAssetOutputDto checkHasAsset(CheckHasAssetInputDto inputDto) throws BizException;

}
