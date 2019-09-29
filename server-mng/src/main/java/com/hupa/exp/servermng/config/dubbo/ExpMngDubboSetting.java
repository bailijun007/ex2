/**
 * @author zw
 * @date 2019/7/9
 */
package com.hupa.exp.servermng.config.dubbo;


import com.hupa.exp.common.config.setting.BaseDubboSetting;
import com.hupa.exp.servermng.config.AppSettingConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = AppSettingConfig.app_name +"."+AppSettingConfig.model+".dubbo")
public class ExpMngDubboSetting  extends BaseDubboSetting{//
//    String registryAddress;
//    String serverName;
//    int protocolPort;
//    public int protocolTimeout;
//    public String dumpDirectory;
//
//    @PostConstruct
//    private void init(){
//        System.out.println(protocolPort);
//    }
//
//    public String getRegistryAddress() {
//        return registryAddress;
//    }
//
//    public void setRegistryAddress(String registryAddress) {
//        this.registryAddress = registryAddress;
//    }
//
//    public String getServerName() {
//        return serverName;
//    }
//
//    public void setServerName(String serverName) {
//        this.serverName = serverName;
//    }
//
//    public int getProtocolPort() {
//        return protocolPort;
//    }
//
//    public void setProtocolPort(int protocolPort) {
//        this.protocolPort = protocolPort;
//    }
//
//    public int getProtocolTimeout() {
//        return protocolTimeout;
//    }
//
//    public void setProtocolTimeout(int protocolTimeout) {
//        this.protocolTimeout = protocolTimeout;
//    }
//
//    public String getDumpDirectory() {
//        return dumpDirectory;
//    }
//
//    public void setDumpDirectory(String dumpDirectory) {
//        this.dumpDirectory = dumpDirectory;
//    }

}
