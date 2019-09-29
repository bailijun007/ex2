package com.hupa.exp.servermng.action.controller.api;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.login.LoginInputDto;
import com.hupa.exp.servermng.entity.login.LoginOutInputDto;
import com.hupa.exp.servermng.entity.login.LoginOutOutputDto;
import com.hupa.exp.servermng.entity.login.LoginOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.impl.ApiLoginControllerServiceImpl;
import com.hupa.exp.util.auth.AuthCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Api(tags = {"apiLoginController"})
@RestController
@RequestMapping(path = "/v1/http/login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Component
public class ApiLoginController {//extends LoginFilterImpl
@Autowired
    private ApiLoginControllerServiceImpl apiLoginControllerService;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);

//    public ApiLoginController(HttpServletRequest httpServletRequest, String token) throws LoginException {
//        super(httpServletRequest, token);
//    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "loginUser",value = "用户登录",paramType = "obj",dataType = "Obj")
    @PostMapping("")
    public BaseResultViaApiDto<LoginInputDto,LoginOutputDto> login(@RequestBody LoginInputDto inputDto) {//,HttpServletRequest request
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        LoginOutputDto outputDto=new LoginOutputDto();
        HttpSession session=request.getSession();

        Object verifyCode= session.getAttribute("verifyCode");//获取session中的验证码
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        try {
                outputDto= apiLoginControllerService.login(inputDto,String.valueOf(verifyCode));
                session.setAttribute( "token", outputDto.getLoginBizBo().getToken());//方便判断token是否存在
                session.setMaxInactiveInterval(1*60*60*2);
                //将返回的token作为key,用户对象作为value
                session.setAttribute(outputDto.getLoginBizBo().getToken() , JSON.toJSONString(outputDto.getLoginBizBo()));
        }catch (BizException e)
        {
            return   BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "退出登陆")
    @ApiImplicitParam(name = "loginUser",value = "用户登录",paramType = "obj",dataType = "Obj")
    @PostMapping("/sign_out")
    public BaseResultViaApiDto<LoginOutInputDto,LoginOutOutputDto> loginOut() {//,HttpServletRequest request
        ServletRequestAttributes attr=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attr.getRequest();
        LoginOutOutputDto outputDto=new LoginOutOutputDto();
        LoginOutInputDto inputDto=new LoginOutInputDto();
        HttpSession session=request.getSession();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        try {
            apiLoginControllerService.loginOut(inputDto);
        }catch (BizException e)
        {
            return   BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

//    /**
//     * 验证token是否存在    暂时没用   调用Api的时候通过result验证一下就好了
//     */
//    @ApiOperation(value = "验证token是否存在")
//    @ApiImplicitParam(name = "existlogin",value = "验证token是否存在",paramType = "obj",dataType = "Obj")
//    @PostMapping("/existlogin")
//    public BaseResultViaApiDto<ExistLoginInputDto,ExistLoginOutputDto> existlogin(@RequestBody ExistLoginInputDto inputDto, HttpServletRequest request) {
//        HttpSession session =request.getSession();
//        ExistLoginOutputDto outputDto=new ExistLoginOutputDto();
//        try{
//            apiLoginControllerService.existLogin(inputDto,session);
//
//        }catch (BizException e)
//        {
//            return   BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
//        }
//        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
//    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "获取登录验证码")
    @ApiImplicitParam(name = "get_verify_code",value = "获取登录验证码",paramType = "null",dataType = "null")
    @GetMapping("/get_verify_code")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        AuthCodeUtil.getAuthCodeHelper().outputToWebClient(request,response);
    }




}
