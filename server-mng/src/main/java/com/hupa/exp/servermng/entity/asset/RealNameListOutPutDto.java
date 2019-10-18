package com.hupa.exp.servermng.entity.asset;

import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class RealNameListOutPutDto extends BaseOutputDto {
    private AssetListBizBo RealNameListBizBo;

    public AssetListBizBo getRealNameListBizBo() {
        return RealNameListBizBo;
    }

    public void setRealNameListBizBo(AssetListBizBo realNameListBizBo) {
        this.RealNameListBizBo = realNameListBizBo;
    }
}
