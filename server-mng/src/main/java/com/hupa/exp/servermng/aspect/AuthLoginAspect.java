package com.hupa.exp.servermng.aspect;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.annotation.CheckLogin;
import com.hupa.exp.servermng.entity.login.LoginInputDto;
import com.hupa.exp.servermng.entity.login.LoginOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.aspect
 * @email yangyang@dalaoyang.cn
 * @date 2018/9/9
 */
@Aspect
@Component
public class AuthLoginAspect {

    @Autowired
    private SessionHelper sessionHelper;

    @Around("@annotation(checkLogin)")
    public BaseResultViaApiDto<LoginInputDto,LoginOutputDto> around(ProceedingJoinPoint joinPoint, CheckLogin checkLogin) throws BizException {
        System.out.println("方法开始时间是:"+new Date());
        //try {
            sessionHelper.getUserInfoBySession();
//        } catch (BizException e) {
//            throw new LoginException(LoginExceptionCode.TOKEN_NULL_ERROR);
//        }
//        Object o = null;
//        try {
//            o = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        System.out.println("方法结束时间是:"+new Date()) ;
        //return o;
        return null;
    }
}
