package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.coin.*;

public interface IApiCoinControllerService {
    CoinOutputDto createCoin(CoinInputDto inputDto) throws BizException;

    CoinOutputDto editCoin(CoinInputDto inputDto) throws BizException;

    GetCoinOutputDto getCoinById(GetCoinInputDto inputDto) throws BizException;

    CoinListOutputDto getCoinList(CoinListInputDto inputDto) throws BizException;

    SymbolListOutPutDto getSymbolList(SymbolListInputDto inputDto) throws BizException;

    CheckHasCoinOutputDto checkHasCoin(CheckHasCoinInputDto inputDto) throws BizException;

}
