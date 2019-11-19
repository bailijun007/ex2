package com.hupa.exp.servermng.entity.contract;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetContractListByAssetInputDto extends BaseInputDto {
    private String asset;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }
}
