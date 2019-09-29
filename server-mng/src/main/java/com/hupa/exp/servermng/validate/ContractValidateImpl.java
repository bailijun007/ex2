package com.hupa.exp.servermng.validate;

import com.hupa.exp.servermng.entity.contract.ContractInputDto;
import com.hupa.exp.servermng.enums.ContarctExceptionCode;
import com.hupa.exp.servermng.exception.ContractException;
import org.springframework.stereotype.Service;

@Service("contractValidateImpl")
public class ContractValidateImpl implements IValidate<ContractInputDto> {
    @Override
    public void validate(ContractInputDto obj) throws ContractException {
        if(obj.getPair()==null||obj.getPair().isEmpty())
            throw new ContractException(ContarctExceptionCode.Success);
    }
}
