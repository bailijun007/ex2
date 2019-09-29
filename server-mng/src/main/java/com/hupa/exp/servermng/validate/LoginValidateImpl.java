package com.hupa.exp.servermng.validate;

import com.hupa.exp.bizlogin.exception.BizLoginException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.login.LoginInputDto;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import org.springframework.stereotype.Service;


@Service("loginValidateImpl")
public class LoginValidateImpl implements ILoginValidate<LoginInputDto> {
    @Override
    public void validate(LoginInputDto obj,String verifyCode) throws
            BizException {
        if(obj.getUserName()==null||obj.getUserName().isEmpty())
            throw new BizLoginException(LoginExceptionCode.USERNAME_NULL_ERROR);
        if(obj.getPwd()==null||obj.getPwd().isEmpty())
            throw new BizLoginException(LoginExceptionCode.PWD_NULL_ERROR);
        if(obj.getVerifyCode()==null||obj.getVerifyCode().isEmpty()||!obj.getVerifyCode().equals(verifyCode))
            throw new BizLoginException(LoginExceptionCode.VERIFYCODE_NULL_ERROR);
    }
}
