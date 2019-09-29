package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

public class ImgException extends BaseBizException {

    public <T extends BizException> ImgException(T t) {
        super(t);
    }

    public ImgException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public ImgException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public ImgException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }
}
