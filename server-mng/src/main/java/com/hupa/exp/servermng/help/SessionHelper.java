package com.hupa.exp.servermng.help;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.validate.SessionValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SessionHelper {
    @Autowired
    private SessionValidateImpl sessionValidate;

    @Autowired
    private IExpOperationLogService logService;

    public Object getToken()
    {
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        HttpSession session=request.getSession();
        return  session.getAttribute("token");
    }

    public void cleanToken()
    {
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        HttpSession session=request.getSession();
         session.removeAttribute("token");
    }
    public ExpUserBizBo getUserInfoBySession() throws BizException {
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        HttpSession session=request.getSession();
        Object token=session.getAttribute("token");
        sessionValidate.validate(token);
        Object userStr=session.getAttribute(String.valueOf(token));
        JSONObject userInfo = JSON.parseObject(String.valueOf(userStr));
        ExpUserBizBo ExpUserBo= ConventObjectUtil.conventObject(userInfo,ExpUserBizBo.class);
        return ExpUserBo;
    }
}
