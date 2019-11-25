package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum MngExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    DUBBO_SERVER_ERROR(-90001, "DUBBO_SERVER_ERROR", ""),//连接Dubbo服务错误
    COLLECT_FEE_NUM_ERROR(-90002,"COLLECT_FEE_NUM_ERROR",""),
    TOKEN_NULL_ERROR(-10004,"TOKEN_NULL_ERROR",""),
    ASSET_EXIST_ERROR(-90003,"ASSET_EXIST_ERROR",""),
    CONTRACT_EXIST_ERROR(-90004,"CONTRACT_EXIST_ERROR",""),
    PWD_NULL_ERROR(-90005,"PWD_NULL_ERROR",""),
    PHONE_EXIST_ERROR_MNG(-90007,"PHONE_EXIST_ERROR_MNG",""),
    EMAIL_EXIST_ERROR_MNG(-90008,"EMAIL_EXIST_ERROR_MNG",""),
    TABLE_NOT_EXIST_ERROR(-90010,"TABLE_NOT_EXIST_ERROR",""),
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