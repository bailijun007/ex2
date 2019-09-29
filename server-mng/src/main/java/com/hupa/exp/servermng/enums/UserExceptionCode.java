package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum UserExceptionCode implements ExceptionDescribe {
    CREATE_SUCCESS(0, "SUCCEED", ""),//成功
    USERNAME_NULL_ERROR(-10011, "USERNAME_NULL_ERROR", ""),//用户名错误
    PWD_NULL_ERROR(-10012, "PWD_NULL_ERROR", ""),//密码错误
    EMAIL_ERROR(-10013,"EMAIL_ERROR","")
            ;
    private int code;
    private String constant;
    private String params;

    @Override
    public String getParams() {
        return this.params;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getConstant() {
        return this.constant;
    }

    UserExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }
}
