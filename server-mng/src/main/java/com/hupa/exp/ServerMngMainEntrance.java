package com.hupa.exp;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration
@EnableDubbo(scanBasePackages = {
        "com.hupa.exp.id.def.transaction",
        "com.hupa.exp.account.def"})
public class ServerMngMainEntrance {

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ServerMngMainEntrance.class)
                .web(WebApplicationType.SERVLET)
                .initializers((ConfigurableApplicationContext context) -> {
                    ConfigurableEnvironment env = context.getEnvironment();
                    System.setProperty("logDir", env.getProperty("exp.mng.config.logDir"));
                    System.setProperty("serverId", env.getProperty("exp.mng.config.serverId"));
                })
                .run(args);
    }
}
