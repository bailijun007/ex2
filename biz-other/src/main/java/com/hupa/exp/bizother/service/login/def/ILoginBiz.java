package com.hupa.exp.bizother.service.login.def;

import com.hupa.exp.bizother.entity.login.AdminLoginBizBo;
import com.hupa.exp.common.exception.BizException;

public interface ILoginBiz {

    //@Deprecated
    AdminLoginBizBo adminLogin(String userName, String loginPwd, String loginIp, String ipAddress) throws BizException;





}
