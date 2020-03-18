package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

/**
 * Created by Administrator on 2020/2/9.
 */
public enum BbSymbolExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    ID_NULL_ERROR(0, "SUCCEED", ""),//id为空
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

    BbSymbolExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }


}
