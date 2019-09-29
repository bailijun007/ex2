package com.hupa.exp;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class BizOtherMainEntrance {

    public static void main(String[] args){

        stopGracefully();

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(BizOtherMainEntrance.class)
                .web(WebApplicationType.NONE)
                .run(args);

    }

    private static void stopGracefully() {


        System.out.println("添加 hook ------------------------------------->");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.out.println("等待程序退出--------------------------------->");

                System.out.println("程序退出------------------------------------------>");
            }
        });
    }
}
