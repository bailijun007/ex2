package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class MenuException extends BaseBizException {

    public <T extends BizException> MenuException(T t) {
        super(t);
    }

    public MenuException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public MenuException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public MenuException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}