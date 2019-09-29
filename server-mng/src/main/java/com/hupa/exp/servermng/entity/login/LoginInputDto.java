package com.hupa.exp.servermng.entity.login;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.sql.Struct;

public class LoginInputDto  extends BaseInputDto{
    private String userName;
    private String pwd;
    private String verifyCode;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
