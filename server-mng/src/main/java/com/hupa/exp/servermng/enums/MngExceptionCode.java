package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum MngExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    DUBBO_SERVER_ERROR(-90001, "DUBBO_SERVER_ERROR", ""),//连接Dubbo服务错误
    COLLECT_FEE_NUM_ERROR(-90002,"COLLECT_FEE_NUM_ERROR",""),
    TOKEN_NULL_ERROR(-10004,"TOKEN_NULL_ERROR","")

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

    MngExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }

}