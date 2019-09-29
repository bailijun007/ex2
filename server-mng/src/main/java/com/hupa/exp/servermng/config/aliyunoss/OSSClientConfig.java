package com.hupa.exp.servermng.config.aliyunoss;

import com.hupa.exp.servermng.config.AppSettingConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = AppSettingConfig.app_name +"."+AppSettingConfig.model+".oss")
public class OSSClientConfig {
    // endpoint
    private String endpoint ;
    // accessKey
    private String accessKeyId ;
    private String accessKeySecret;
    // 空间
    private String bucketName ;
    // 文件存储目录
    private String filedir ;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFiledir() {
        return filedir;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }
}
