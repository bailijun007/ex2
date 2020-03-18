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

    GetBbSymbolOutputDto getBbSymbol(GetBbSymbolInputDto inputDto) throws BbSymbolException;

    GetAllSymbolOutputDto getAllBbSymbolList(GetAllSymbolInputDto inputDto) throws BbSymbolException;

    GetAllActiveBbSymbolOutputDto getAllActiveBbSymbol(GetAllActiveBbSymbolInputDto inputDto) throws BizException;

    CheckHasBbSymbolOutputDto checkHasBbSymbol(CheckHasBbSymbolInputDto inputDto) throws MngException;

    GetBbSymbolListByAssetOutputDto GetBbSymbolListByAsset(GetBbSymbolListByAssetInputDto inputDto)throws BizException;

   /*
    CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto)throws BbSymbolException;
   */

    DeleteOutputDto deleteBbSymbol(DeleteInputDto inputDto) throws BizException;

    BbSymbolOutputDto getBbSymbolGroupNum(GetBbSymbolInputDto inputDto) throws BizException;

}
