package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum MongoDbExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    MONGO_DB_HAS_NULL_DATA(-50001, "MONGO_DB_HAS_NULL_DATA", ""),//MongoDb中没有数据
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

    MongoDbExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }

}