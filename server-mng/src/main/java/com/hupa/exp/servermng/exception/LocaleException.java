package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class LocaleException extends BaseBizException {

    public <T extends BizException> LocaleException(T t) {
        super(t);
    }

    public LocaleException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public LocaleException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public LocaleException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
