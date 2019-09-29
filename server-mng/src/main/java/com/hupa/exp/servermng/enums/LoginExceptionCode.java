package com.hupa.exp.servermng.enums;


import com.hupa.exp.common.exception.ExceptionDescribe;

public enum LoginExceptionCode implements ExceptionDescribe {
    LoginSuccess(0, "SUCCEED", ""),//成功
    USERNAME_NULL_ERROR(-10001, "USERNAME_ERROR", ""),//用户名错误
    PWD_NULL_ERROR(-10002, "PWD_ERROR", ""),//密码错误
    VERIFYCODE_NULL_ERROR(-10003,"VERIFY_CODE_ERROR",""),
    TOKEN_NULL_ERROR(-10004,"TOKEN_NULL_ERROR",""),

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

    LoginExceptionCode(int code, String constant, String params) {
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
