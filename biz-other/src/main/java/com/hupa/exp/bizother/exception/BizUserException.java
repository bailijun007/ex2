package com.hupa.exp.bizother.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class BizUserException extends BaseBizException {

    public <T extends BizException> BizUserException(T t) {
        super(t);
    }

    public BizUserException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public BizUserException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public BizUserException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
