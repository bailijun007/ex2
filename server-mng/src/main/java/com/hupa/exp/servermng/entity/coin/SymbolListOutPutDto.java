package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.bizother.entity.account.CoinListBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class SymbolListOutPutDto extends BaseOutputDto {
    private CoinListBizBo symbolListBizBo;

    public CoinListBizBo getSymbolListBizBo() {
        return symbolListBizBo;
    }

    public void setSymbolListBizBo(CoinListBizBo symbolListBizBo) {
        this.symbolListBizBo = symbolListBizBo;
    }
}
