package com.hupa.exp.servermng.exception;

import com.hupa.exp.common.exception.BaseBizException;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.exception.ExceptionDescribe;

import java.util.Map;


    public class MngException extends BaseBizException {

        public <T extends BizException> MngException(T t) {
            super(t);
        }

        public MngException(ExceptionDescribe exceptionDescribe) {
            super(exceptionDescribe);
        }

        public MngException(ExceptionDescribe exceptionDescribe, Map<String, String> data) {
            super(exceptionDescribe, data);
        }

        public MngException(Throwable cause, ExceptionDescribe exceptionDescribe, Map<String, String> data) {
            super(cause, exceptionDescribe, data);
        }
    }

