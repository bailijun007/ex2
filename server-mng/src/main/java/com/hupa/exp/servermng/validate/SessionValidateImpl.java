package com.hupa.exp.servermng.validate;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import com.hupa.exp.servermng.exception.LoginException;
import org.springframework.stereotype.Service;

@Service("sessionValidateImpl")
public class SessionValidateImpl implements IValidate<Object> {
    @Override
    public void validate(Object obj) throws BizException {
        if(obj==null)
            throw  new LoginException(LoginExceptionCode.TOKEN_NULL_ERROR);
    }
}
