package com.hupa.exp.servermng.entity.login;

import com.hupa.exp.bizother.entity.login.AdminLoginBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class LoginOutputDto  extends BaseOutputDto {

    private AdminLoginBizBo loginBizBo;

    public AdminLoginBizBo getLoginBizBo() {
        return loginBizBo;
    }

    public void setLoginBizBo(AdminLoginBizBo loginBizBo) {
        this.loginBizBo = loginBizBo;
    }
}
