package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizlogin.entity.AdminLoginBizBo;
import com.hupa.exp.bizlogin.service.def.ILoginBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.login.LoginInputDto;
import com.hupa.exp.servermng.entity.login.LoginOutInputDto;
import com.hupa.exp.servermng.entity.login.LoginOutOutputDto;
import com.hupa.exp.servermng.entity.login.LoginOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiLoginControllerService;
import com.hupa.exp.servermng.validate.LoginValidateImpl;
import com.hupa.exp.util.request.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
public class  ApiLoginControllerServiceImpl implements IApiLoginControllerService {

    @Autowired
    private LoginValidateImpl loginValidate;
   @Autowired
    private ILoginBiz iLoginBiz;
@Autowired
    SessionHelper sessionHelper;
    @Override
    public LoginOutputDto login(LoginInputDto inputDto, String verifyCode) throws BizException {
        LoginOutputDto outputDto = new LoginOutputDto();
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        String loginIp= RequestUtil.getRealIp(request);
        String loginAddress="中国";
//        try {
//            loginAddress=RequestUtil.getAddresses("ip="+loginIp,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
          loginValidate.validate(inputDto,verifyCode);//验证参数
        AdminLoginBizBo loginBizBo=iLoginBiz.adminLogin(inputDto.getUserName(),
                inputDto.getPwd(),loginIp,loginAddress);
       if(loginBizBo!=null)//调用登陆验证方法
       {
           outputDto.setLoginBizBo(loginBizBo);
           outputDto.setTime(String.valueOf(System.currentTimeMillis()));
       }
        return outputDto;
    }

    @Override
    public LoginOutOutputDto loginOut(LoginOutInputDto inputDto) throws BizException {
       sessionHelper.cleanToken();
        LoginOutOutputDto outOutputDto=new LoginOutOutputDto();
        return outOutputDto;
    }


}
