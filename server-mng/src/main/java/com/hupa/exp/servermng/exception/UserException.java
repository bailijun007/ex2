package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class UserException extends BaseBizException {

    public <T extends BizException> UserException(T t) {
        super(t);
    }

    public UserException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public UserException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public UserException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
