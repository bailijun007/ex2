package com.hupa.exp.bizother.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum BizUserExceptionCode implements ExceptionDescribe {
    LoginSuccess(0, "SUCCEED", ""),//成功
    USERNAME_OR_PWD_ERROR(-10005, "USERNAME_OR_PWD_ERROR", ""),//用户名或密码错误
    PWD_ENCRY_ERROR(-10005, "PWD_ENCRY_ERROR", ""),//加密错误

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

    BizUserExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }
}
