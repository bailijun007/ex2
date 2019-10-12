package com.hupa.exp;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class ServerProcTestMainEntrance {

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ServerProcTestMainEntrance.class)
                .web(WebApplicationType.NONE)
                .initializers((ConfigurableApplicationContext context) -> {
                    ConfigurableEnvironment env = context.getEnvironment();
                    System.setProperty("logDir", env.getProperty("exp.mng.config.logDir"));
                    System.setProperty("serverId", env.getProperty("exp.mng.config.serverId"));
                })
                .run(args);
    }
}
