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
    private ExpMngDubboSetting dubboSetting;

    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(dubboSetting.getProtocolPort());
        protocolConfig.setDispatcher("execution");
        protocolConfig.setThreadpool("cached");
//        System.out.println("默认protocolConfig：" + protocolConfig.hashCode());
        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(dubboSetting.getProtocolTimeout());
        consumerConfig.setRetries(0);
       consumerConfig.setCheck(false);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboSetting.getRegistryAddress());
        registryConfig.setFile(dubboSetting.getDumpDirectory() + File.separator + "cache");
        registryConfig.setClient("curator");
        registryConfig.setGroup(dubboSetting.getGroup());
        return registryConfig;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        Map<String, String> m = new HashMap<>();
        m.put("dump.directory", dubboSetting.getDumpDirectory());
        applicationConfig.setParameters(m);
        applicationConfig.setName(dubboSetting.getServerName());
        return applicationConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
//        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

}