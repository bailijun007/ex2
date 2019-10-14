package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;



public class MongoDbException extends BaseBizException {

    public <T extends BizException> MongoDbException(T t) {
        super(t);
    }

    public MongoDbException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public MongoDbException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public MongoDbException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}