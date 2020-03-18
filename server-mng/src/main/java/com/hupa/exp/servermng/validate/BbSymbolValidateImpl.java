package com.hupa.exp.servermng.validate;

import com.hupa.exp.servermng.entity.symbol.BbSymbolInputDto;
import com.hupa.exp.servermng.enums.BbSymbolExceptionCode;
import com.hupa.exp.servermng.exception.BbSymbolException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2020/2/9.
 */
@Service("bbSymbolValidateImpl")
public class BbSymbolValidateImpl implements IValidate<BbSymbolInputDto> {

    @Override
    public void validate(BbSymbolInputDto obj) throws BbSymbolException {
        if(obj.getSymbol()==null||obj.getSymbol().isEmpty())
            throw new BbSymbolException(BbSymbolExceptionCode.Success);
    }

}
