package com.hupa.exp.bizother.service.price.impl;

import com.hupa.exp.base.config.redis.LastPriceRedisConfig;
import com.hupa.exp.bizother.config.BizPriceSettingConfig;
import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
import com.hupa.exp.util.math.DecimalUtil;
import com.hupa.exp.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Service(PcLastPriceBizImpl.serviceName)
public class PcLastPriceBizImpl implements ILastPriceBiz {

    public static final  String serviceName="pcLastPriceBizImpl";

    @Autowired
    private BizPriceSettingConfig settingConfig;

    @Autowired
    private LastPriceRedisConfig lastPriceRedisConfig;


    @Override
    public BigDecimal get(String pair) {
        return queryLastPrice(pair);
    }

    @Override
    public void save(String pair, BigDecimal price) {

        if(price == null)
            return;

        String redisKey = getPcLastPriceRedisKey(pair);
        RedisUtil.redisClientFactory(lastPriceRedisConfig).set(redisKey, DecimalUtil.trimZeroPlainString(price));
    }

    @Override
    public BigDecimal calc(String pair) {
        return null;
    }


    private BigDecimal queryLastPrice(String pair){

        String redisKey = getPcLastPriceRedisKey(pair);
        String lastPriceValue = RedisUtil.redisClientFactory(lastPriceRedisConfig).get(redisKey);

        if(StringUtils.isEmpty(lastPriceValue))
            return null;



        return new BigDecimal(lastPriceValue);
    }

    private String getPcLastPriceRedisKey(String pair){

        String k =  settingConfig.getPcLastPriceRedisKey();

        k = MessageFormat.format(k,pair);

        return k;
    }
}
