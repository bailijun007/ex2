package com.hupa.exp.bizother.service.price.impl;

import com.hupa.exp.base.config.redis.LastPriceRedisConfig;
import com.hupa.exp.bizother.config.BizPriceSettingConfig;
import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IPcTradeAssetSymbolMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcTradeAssetSymbolMongoPo;
import com.hupa.exp.util.math.DecimalUtil;
import com.hupa.exp.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service(PcLastPriceBizImpl.serviceName)
public class PcLastPriceBizImpl implements ILastPriceBiz {

    public static final  String serviceName="pcLastPriceBizImpl";





    @Autowired
    private BizPriceSettingConfig settingConfig;

    @Autowired
    private LastPriceRedisConfig lastPriceRedisConfig;

    @Autowired
    private IPcTradeAssetSymbolMongoDao iPcTradeSymbolDao;


    @Override
    public BigDecimal get(String asset,String symbol) {

        String redisKey = getPcLastPriceRedisKey(asset, symbol);
        String lastPriceValue = RedisUtil.redisClientFactory(lastPriceRedisConfig).get(redisKey);

        if(!StringUtils.isEmpty(lastPriceValue))
            return new BigDecimal(lastPriceValue);


        PcTradeAssetSymbolMongoPo pcTradePairMongoPo = iPcTradeSymbolDao.selectOnePoByLast(asset, symbol);
        if(pcTradePairMongoPo ==null || pcTradePairMongoPo.getPrice()==null)
            return BigDecimal.ZERO;


        return pcTradePairMongoPo.getPrice();

    }

    @Override
    public void save(String asset,String symbol, BigDecimal price) {

        if(price == null)
            return;

        String redisKey = getPcLastPriceRedisKey(asset, symbol);
        RedisUtil.redisClientFactory(lastPriceRedisConfig).set(redisKey, DecimalUtil.trimZeroPlainString(price));
    }




    private String getPcLastPriceRedisKey(String asset, String symbol){

        //String tempRedisKey =  settingConfig.getPcLastPriceRedisKey();

        String tempRedisKey = "lastPrice:pc:{asset}:{symbol}";

        tempRedisKey = StringUtils.replace(tempRedisKey,"{asset}",asset);
        tempRedisKey = StringUtils.replace(tempRedisKey,"{symbol}",symbol);

        return tempRedisKey;
    }
}
