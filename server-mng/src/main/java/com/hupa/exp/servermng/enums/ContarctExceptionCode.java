package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum ContarctExceptionCode implements ExceptionDescribe {
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

    ContarctExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }

//    public static Map<String, String> buildMap_PASSWORD_ISNULL_EXCEPTION(String passWord) {
//        return new HashMap<String, String>() {{
//            put("passWord", passWord);
//        }};
//    }
}
