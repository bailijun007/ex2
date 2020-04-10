package com.hupa.exp;


import com.gitee.hupadev.base.spring.autoconfigure.mvc.MvcConfig;
import com.hupa.exp.servermng.filter.CorsFilter;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

@EnableFeignClients({"com.hp.sh.expv3"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,MvcConfig.class,MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootConfiguration
@EnableDubbo(scanBasePackages = {"com.hupa.exp.account.def"})
//@EnableAutoConfiguration
public class ServerMngMainEntrance {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ServerMngMainEntrance.class)
                .web(WebApplicationType.SERVLET)
                .initializers((ConfigurableApplicationContext context) -> {
                    ConfigurableEnvironment env = context.getEnvironment();
                    System.setProperty("logDir", env.getProperty("exp.mng.config.logDir"));
                    System.setProperty("serverId", env.getProperty("exp.mng.config.serverId"));
                })
                .run(args);
    }

    //添加一个基本过滤器，用于设置编码，调试观察请求数据
    @Bean
    public FilterRegistrationBean registerBaseFilter() {
        //kkk
        //
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/v1/http/*");
        registration.setOrder(1);
        return registration;
    }

}
