package com.hupa.exp.bizother.service.login.impl;


import com.hupa.exp.base.helper.security.SecurityPwdHelper;
import com.hupa.exp.bizother.entity.login.AdminLoginBizBo;
import com.hupa.exp.bizother.enums.BizUserExceptionCode;
import com.hupa.exp.bizother.exception.BizUserException;
import com.hupa.exp.bizother.service.login.def.ILoginBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpUserPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginBizImpl implements ILoginBiz {

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private SecurityPwdHelper securityPwdHelper;

    @Override
    public AdminLoginBizBo adminLogin(String userName, String loginPwd, String loginIp, String ipAddress) throws BizException {
       //redisUtil.set("123","123");
        String pwd=securityPwdHelper.getMd5Pwd("PwdMD5Key",loginPwd);
        ExpUserPo expUserPo=iExpUserDao.login(userName,pwd);
        if(expUserPo==null)
            throw new BizUserException(BizUserExceptionCode.USERNAME_OR_PWD_ERROR);
        AdminLoginBizBo loginBizBo= ConventObjectUtil.conventObject(expUserPo,AdminLoginBizBo.class);
        String token= getToken(userName,loginPwd);
        loginBizBo.setToken(token);
        //loginBizBo.setExpUserBo(ConventObjectUtil.conventObject(expUserPo,loginBizBo.getExpUserBo().getClass()));
        return loginBizBo;
    }



    public String getToken(String userName,String loginPwd) throws BizException {
        String token="";
        //后续修改
        try {
            //GuM5ZieaeKBzQgdG
            token = SecurityUtil.hmacSha256("expv2",userName+loginPwd+System.currentTimeMillis());
        } catch (NoSuchAlgorithmException e) {
            throw new BizUserException(BizUserExceptionCode.CREATE_TOKEN_ERROR);
        } catch (InvalidKeyException e) {
            throw new BizUserException(BizUserExceptionCode.CREATE_TOKEN_ERROR);
        }
        return token;
    }
}
