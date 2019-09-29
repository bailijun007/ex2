package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.login.*;

import javax.servlet.http.HttpSession;


public interface IApiLoginControllerService {
    LoginOutputDto login(LoginInputDto inputDto,String verifyCode) throws Exception;

    LoginOutOutputDto loginOut(LoginOutInputDto inputDto) throws Exception;
}
