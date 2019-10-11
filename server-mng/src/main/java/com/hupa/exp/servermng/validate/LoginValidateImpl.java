package com.hupa.exp.servermng.validate;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.login.LoginInputDto;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import com.hupa.exp.servermng.exception.LoginException;
import org.springframework.stereotype.Service;


@Service("loginValidateImpl")
public class LoginValidateImpl implements ILoginValidate<LoginInputDto> {
    @Override
    public void validate(LoginInputDto obj,String verifyCode) throws
            BizException {
        if(obj.getUserName()==null||obj.getUserName().isEmpty())
            throw new LoginException(LoginExceptionCode.USERNAME_NULL_ERROR);
        if(obj.getPwd()==null||obj.getPwd().isEmpty())
            throw new LoginException(LoginExceptionCode.PWD_NULL_ERROR);
        if(obj.getVerifyCode()==null||obj.getVerifyCode().isEmpty()||!obj.getVerifyCode().equals(verifyCode))
            throw new LoginException(LoginExceptionCode.VERIFYCODE_NULL_ERROR);
    }
}
