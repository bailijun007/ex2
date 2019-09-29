package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.bizaccount.entity.SymbolListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class SymbolListOutPutDto extends BaseOutputDto {
    private SymbolListBizBo symbolListBizBo;

    public SymbolListBizBo getSymbolListBizBo() {
        return symbolListBizBo;
    }

    public void setSymbolListBizBo(SymbolListBizBo symbolListBizBo) {
        this.symbolListBizBo = symbolListBizBo;
    }
}
