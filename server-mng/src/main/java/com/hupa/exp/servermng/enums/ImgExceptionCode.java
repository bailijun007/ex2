package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum  ImgExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    VALIDATE_HAS_CODE_ERROR(-19999, "VALIDATE_HAS_CODE_ERROR", ""),//id为空
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

    ImgExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }

}
