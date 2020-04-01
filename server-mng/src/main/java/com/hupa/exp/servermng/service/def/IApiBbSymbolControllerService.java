package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.GetAllSymbolInputDto;
import com.hupa.exp.servermng.entity.contract.GetAllSymbolOutputDto;
import com.hupa.exp.servermng.entity.symbol.*;
import com.hupa.exp.servermng.exception.BbSymbolException;
import com.hupa.exp.servermng.exception.MngException;

/**
 * Created by Administrator on 2020/2/9.
 */
public interface IApiBbSymbolControllerService {


    BbSymbolOutputDto createOrEditBbSymbol(BbSymbolInputDto inputDto) throws BizException;

    BbSymbolListOutputDto getPosPageByParam(BbSymbolListInputDto inputDto) throws BbSymbolException;

    GetBbSymbolOutputDto getBbSymbol(BbSymbolInputDto inputDto) throws BbSymbolException;

    GetAllSymbolOutputDto getAllBbSymbolList(GetAllSymbolInputDto inputDto) throws BbSymbolException;

    BbSymbolOutputDto getAllActiveBbSymbol(BbSymbolInputDto inputDto) throws BizException;

    BbSymbolOutputDto checkHasBbSymbol(BbSymbolInputDto inputDto) throws MngException;

    BbSymbolOutputDto GetBbSymbolListByAsset(BbSymbolInputDto inputDto) throws BizException;

   /*
    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws BbSymbolException;
   */

    DeleteOutputDto deleteBbSymbol(DeleteInputDto inputDto) throws BizException;

    BbSymbolOutputDto getBbSymbolGroupNum(BbSymbolInputDto inputDto) throws BizException;

}
