package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class LoginException extends BaseBizException {

    public <T extends BizException> LoginException(T t) {
        super(t);
    }

    public LoginException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public LoginException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public LoginException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
