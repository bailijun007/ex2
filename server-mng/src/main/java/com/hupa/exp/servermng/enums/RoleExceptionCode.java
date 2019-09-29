package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum RoleExceptionCode implements ExceptionDescribe {
    CREATE_SUCCESS(0, "SUCCEED", ""),//成功
    ROLENAME_NULL_ERROR(-10021, "ROLENAME_NULL_ERROR", ""),//角色名为空
    DESC_NULL_ERROR(-10022, "DESC_NULL_ERROR", ""),//描述为空
    HAS_NULL_MODEL_ERROR(-10023,"HAS_NULL_MODEL_ERROR","")//没查询到对象
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

    RoleExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }
}
