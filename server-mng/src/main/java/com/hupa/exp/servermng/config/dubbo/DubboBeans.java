/**
 * @author zw
 * @date 2019/7/9
 */
package com.hupa.exp.servermng.config.dubbo;


import org.apache.dubbo.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class DubboBeans {

    @Autowired
    private DubboConfig dubboConfig;

    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(dubboConfig.getProtocolPort());
        protocolConfig.setDispatcher("execution");
        protocolConfig.setThreadpool("cached");
//        System.out.println("默认protocolConfig：" + protocolConfig.hashCode());
        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(dubboConfig.getProtocolTimeout());
        consumerConfig.setRetries(0);
        consumerConfig.setCheck(false);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboConfig.getRegistryAddress());
        registryConfig.setFile(dubboConfig.getDumpDirectory() + File.separator + "cache");
        registryConfig.setClient("curator");
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        Map<String, String> m = new HashMap<>();
        m.put("dump.directory", dubboConfig.getDumpDirectory());
        applicationConfig.setParameters(m);
        applicationConfig.setName(dubboConfig.getServerName());
        return applicationConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

}