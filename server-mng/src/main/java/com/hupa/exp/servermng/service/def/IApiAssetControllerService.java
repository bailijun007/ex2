package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;

public interface IApiAssetControllerService {

    AssetOutputDto createAsset(AssetInputDto inputDto) throws BizException;

    AssetOutputDto editAsset(AssetInputDto inputDto) throws BizException;

    GetAssetOutputDto getAssetById(AssetInputDto inputDto) throws BizException;

    AssetListOutputDto getAssetList(AssetListInputDto inputDto) throws BizException;

    RealNameListOutPutDto getRealNameList(AssetInputDto inputDto) throws BizException;//RealNameListInputDto

    CheckHasAssetOutputDto checkHasAsset(CheckHasAssetInputDto inputDto) throws BizException;

    DeleteOutputDto deleteAsset(DeleteInputDto inputDto) throws BizException;

}
