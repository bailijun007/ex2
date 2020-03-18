package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolException extends BaseBizException {

    public <T extends BizException> BbSymbolException(T t) {
        super(t);
    }

    public BbSymbolException(ExceptionDescribe exceptionDescribe) {
        super(exceptionDescribe);
    }

    public BbSymbolException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(exceptionDescribe, data);
    }

    public BbSymbolException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
        super(cause, exceptionDescribe, data);
    }


}
