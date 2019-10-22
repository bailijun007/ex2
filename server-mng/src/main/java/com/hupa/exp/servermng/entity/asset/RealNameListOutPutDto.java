package com.hupa.exp.servermng.entity.asset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class RealNameListOutPutDto extends BaseOutputDto {
    @JsonProperty("asset_list")
    private AssetListBizBo assetList;

    public AssetListBizBo getAssetList() {
        return assetList;
    }

    public void setAssetList(AssetListBizBo assetList) {
        this.assetList = assetList;
    }
}
