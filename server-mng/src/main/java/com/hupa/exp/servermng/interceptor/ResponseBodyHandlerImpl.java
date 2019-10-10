package com.hupa.exp.servermng.interceptor;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.component.LocaleComponent;
import com.hupa.exp.base.helper.request.RequestHelper;
import com.hupa.exp.base.helper.spring.SpringContextHelper;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;


public class ResponseBodyHandlerImpl implements HandlerMethodReturnValueHandler {

//@Autowired
//    private LocaleComponent localeComponent;


    private static Logger logger = LoggerFactory.getLogger(ResponseBodyHandlerImpl.class);

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyHandlerImpl(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }


    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return true;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {

        if(o instanceof BaseResultViaApiDto) {

            BaseResultViaApiDto dto = (BaseResultViaApiDto) o;

            if (dto.getCode() < 0) {
                try {
                    LocaleComponent localeComponent = SpringContextHelper.getBean(LocaleComponent.class);
                    String locale = RequestHelper.getHeaderByLocale(((ServletWebRequest) nativeWebRequest).getRequest());
                    String errMsg = localeComponent.findLocaleContent(locale, dto.getMsg());
                    dto.setMsg(errMsg);
                    logger.error("error request,code:"+dto.getCode()+",content:"+ JSON.toJSONString(dto));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }




        delegate.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
    }



//    @Bean
//    public ResponseBodyHandlerWrapBean getResponseBodyWrap() {
//        return new ResponseBodyHandlerWrapBean();
//    }
}
