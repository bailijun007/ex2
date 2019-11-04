package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcAssetChangeInputDto extends BaseInputDto {
    private long id;
    private String asset;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }
}
