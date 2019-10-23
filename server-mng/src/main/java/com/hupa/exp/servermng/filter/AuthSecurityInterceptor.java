package com.hupa.exp.servermng.filter;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.helper.request.RequestHelper;
import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpLocaleDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpLocalePo;
import com.hupa.exp.servermng.action.controller.api.ApiAreaController;
import com.hupa.exp.servermng.help.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthSecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpLocaleDao localeDao;

    private Logger logger = null;

    /**
     * 进入拦截器后首先进入的方法
     * 返回false则不再继续执行
     * 返回true则继续执行
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception
    {
        if(!request.getRequestURI().equals("/")&&!request.getRequestURI().contains("login"))
        {
            String webSession= RequestHelper.getHeaderByLoginToken(request);//获取前端ajax请求中的token
            //System.out.println(webSession);
            HttpSession session=request.getSession();
            Object token=session.getAttribute("token");
            if(token==null||webSession==null||!webSession.toString().equals(token.toString()))
            {
                //System.out.println("我证明用户没有登录");
                redirect(request,response);
                //response.sendRedirect(request.getContextPath()+"/login.html");
                return false;
            }

            // 从 HTTP 头中取得 Referer 值
//            String referer=request.getHeader("Referer");
//            if(referer==null|| !referer.contains("http://localhost:8081"))
//            {
//                System.out.println("我证明这是一个跨站伪造的请求");
//                redirect(request,response);
//            //response.sendRedirect(request.getContextPath()+"/login.html");
//                return false;
//            }
            //System.out.println("重置Session时间:"+ DateTime.now());
            session.setMaxInactiveInterval(1*60*60*2);
            //System.out.println("我证明用户已经登录");
        }
        return  true;
    }
    /**
     * 生成视图时执行，可以用来处理异常，并记录在日志中
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object arg2, Exception exception){
        //-----------------//
        logger=LoggerFactory.getLogger(arg2.toString());
    }

    /** -
     * 生成视图之前执行，可以修改ModelAndView
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object arg2, ModelAndView arg3)
            throws Exception{
        //----------------------------//
    }

    //对于请求是ajax请求重定向问题的处理方法
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTEXTPATH", basePath+"/login.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + "/login.html");
        }
    }

    public void checkRequest(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
                RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        if(body.contains("current_page"))
        {
            //重置返回值
            response.reset();
            //返回编码
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            BaseResultViaApiDto resp=new BaseResultViaApiDto();
            resp.setCode(-99999);
            ExpLocalePo localePo= localeDao.selectOnePo(-99999);
            if(localePo!=null)
                resp.setMsg(JSON.parseObject(localePo.getLocaleContent()).getString("zh_cn"));
            else
                resp.setMsg("server error");
            resp.setTime(System.currentTimeMillis());

            pw.write(JSON.toJSONString(resp));
            pw.flush();
            pw.close();
        }
        System.out.println("请求链接："+request.getRequestURL()+"请求方式："+request.getMethod()+"请求参数："+body);
        System.out.println("我是拦截器：我证明我进来了");

    }
}
