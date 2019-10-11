package com.hupa.exp.bizother.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = AppSettingConfig.app_name +"."+AppSettingConfig.price_model+".config")
public class BizPriceSettingConfig {

    public String getPcMakePriceRedisKey() {
        return pcMakePriceRedisKey;
    }

    public void setPcMakePriceRedisKey(String pcMakePriceRedisKey) {
        this.pcMakePriceRedisKey = pcMakePriceRedisKey;
    }


    public String getPcLastPriceRedisKey() {
        return pcLastPriceRedisKey;
    }

    public void setPcLastPriceRedisKey(String pcLastPriceRedisKey) {
        this.pcLastPriceRedisKey = pcLastPriceRedisKey;
    }




    public String getPcIndexPriceRedisKey() {
        return pcIndexPriceRedisKey;
    }

    public void setPcIndexPriceRedisKey(String pcIndexPriceRedisKey) {
        this.pcIndexPriceRedisKey = pcIndexPriceRedisKey;
    }


    private String pcLastPriceRedisKey;


    private String pcMakePriceRedisKey;


    private String pcIndexPriceRedisKey;

}
