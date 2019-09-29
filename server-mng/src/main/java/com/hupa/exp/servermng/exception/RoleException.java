package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class RoleException extends BaseBizException {

    public <T extends BizException> RoleException(T t) {
        super(t);
    }

    public RoleException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public RoleException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public RoleException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
