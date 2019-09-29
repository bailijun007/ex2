package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class ContractException extends BaseBizException {

    public <T extends BizException> ContractException(T t) {
        super(t);
    }

    public ContractException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public ContractException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public ContractException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
