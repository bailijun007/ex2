package com.hupa.exp.servermng.action.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultViewController {

        //配置默认页面
        @RequestMapping({"", "login"})
        public ModelAndView login() {
            ModelAndView mv = new ModelAndView();
            mv.addObject("type", "转发返回ModelAndView");
            mv.setViewName("/login.html");
            return mv;
        }
}
