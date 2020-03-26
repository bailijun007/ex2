package com.hupa.exp.servermng.help;

import com.hupa.exp.base.config.redis.CandleRedisConfig;
import com.hupa.exp.base.dic.expv2.PcCandleIntervalDic;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/20.
 */
@Service
public class BbCandleHelper {

    private Logger logger = LoggerFactory.getLogger(BbCandleHelper.class);

    @Autowired
    private CandleRedisConfig candleRedisConfig;

    //@Autowired
    //private BbCandleConfig bbCandleConfig;

    public void writeToRedisEvent(String redisKey, Map<String, Double> member2score) {
        if (null == member2score || member2score.isEmpty()) {
            return;
        }
        ArrayList<Double> doubles = new ArrayList<>(member2score.values());
        doubles.sort(Comparator.naturalOrder());
        double startTimeInMs = doubles.get(0);
        double endTimeInMs = doubles.get(doubles.size() - 1);
        try {
            RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(redisKey, String.valueOf(startTimeInMs), String.valueOf(endTimeInMs));
            RedisUtil.redisClientFactory(candleRedisConfig).zAdd(redisKey, member2score);
        } catch (Exception e) {
            logger.error("K线数据插入Redis错误:" + startTimeInMs + " -> " + endTimeInMs, e);
        }
    }

    public void writeToRedisEvent(String redisKey, String time, String endTime) {
        BigDecimal[] bigDecimals = new BigDecimal[2];
        try {
            if (time == null) {
                return;
            }
            bigDecimals[0] = new BigDecimal(time);
            bigDecimals[1] = new BigDecimal(endTime);
            RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(redisKey, String.valueOf(time), String.valueOf(time));
            RedisUtil.redisClientFactory(candleRedisConfig).zAdd(redisKey, Double.parseDouble(time), JsonUtil.toJsonString(bigDecimals));
        } catch (Exception e) {
            logger.error("K线数据插入Redis错误:" + JsonUtil.toJsonString(bigDecimals) + " Exception:" + e);
        }
    }

    public void writeToRedisRevoke(String redisKey, String time, String endTime) {
        BigDecimal[] bigDecimals = new BigDecimal[3];
        try {
            if (time == null) {
                return;
            }
            bigDecimals[0] = new BigDecimal("0");
            bigDecimals[1] = new BigDecimal(time);
            bigDecimals[2] = new BigDecimal(endTime);
            RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(redisKey, String.valueOf(time), String.valueOf(time));
            RedisUtil.redisClientFactory(candleRedisConfig).zAdd(redisKey, Double.parseDouble(time), JsonUtil.toJsonString(bigDecimals));
        } catch (Exception e) {
            logger.error("K线数据插入Redis错误:" + JsonUtil.toJsonString(bigDecimals) + " Exception:" + e);
        }
    }


   /* public void writeBbCandlePoToRedis(String asset, String symbol, String interval, String time, BbCandlePo candlePo) {
        BigDecimal[] bigDecimals = new BigDecimal[6];
        try {
            if(time==null){
                return;
            }
            bigDecimals[0] = new BigDecimal(time);
            bigDecimals[1] = DecimalUtil.trimZero(candlePo.getOpen()==null ? BigDecimal.ZERO : candlePo.getOpen());
            bigDecimals[2] = DecimalUtil.trimZero(candlePo.getHigh()==null ? BigDecimal.ZERO : candlePo.getHigh());
            bigDecimals[3] = DecimalUtil.trimZero(candlePo.getLow()==null ? BigDecimal.ZERO : candlePo.getLow());
            bigDecimals[4] = DecimalUtil.trimZero(candlePo.getClose()==null ? BigDecimal.ZERO : candlePo.getClose());
            bigDecimals[5] = DecimalUtil.trimZero(candlePo.getVolume()==null ? BigDecimal.ZERO : candlePo.getVolume());
            String redisKey = MessageFormat.format(bbCandleConfig.getBbCandleRedisKey(), asset, symbol, interval);
            RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(redisKey, String.valueOf(time), String.valueOf(time));
            RedisUtil.redisClientFactory(candleRedisConfig).zAdd(redisKey, Double.parseDouble(time), JsonUtil.toJsonString(bigDecimals));
            //logger.info("插入Redis数据["+redisKey+"]["+JsonUtil.toJsonString(bigDecimals)+"]");
        } catch (Exception e) {
            // logger.error("K线数据插入Redis错误  Data:" + JsonUtil.toJsonString(bigDecimals) + " Reason:" + e);
            System.out.println("K线数据插入Redis错误  Data:" + JsonUtil.toJsonString(bigDecimals)+",Exception"+e);
        }
    }*/

    /**
     * 根据当前时间currentTime,计算出属于哪个k线时间点,返回时间戳
     * 比如当前时间2018年8点16分,那么如果是5分钟k线,返回时间应该是2018年8点15分
     *
     * @param currentTime
     * @param firstTradeTime
     * @param interval
     * @return
     */
    public long getIntervalPointTime(long currentTime, long firstTradeTime, String interval) throws Exception {
        long intervalTime = getIntervalMills(interval, currentTime);
        String strKlineStartTime = StringUtils.EMPTY;

//        Long freq = Long.valueOf(intervalTime);
//        long l = TimeUnit.MINUTES.toMillis(freq * (TimeUnit.MILLISECONDS.toMinutes(currentTime) / freq));

        if (interval.equals(PcCandleIntervalDic.interval_min_1)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_3)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_5)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_15)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_30)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_1)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_2)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_4)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_6)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_8)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_12)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_day_1)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_day_3)) {
            strKlineStartTime = new DateTime(firstTradeTime).toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_week_1)) {
            strKlineStartTime = new DateTime(firstTradeTime).dayOfWeek().withMinimumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd") + " 00:00:00";
        } else if (interval.equals(PcCandleIntervalDic.interval_month_1)) {
            strKlineStartTime = new DateTime(currentTime).toString("yyyy-MM") + "-01 00:00:00";
        }
        if (StringUtils.isEmpty(strKlineStartTime)) {
            throw new Exception("getKlinePointDate 方法出错! strKlineStartTime is null");
        }
        long CandlePointTime = DateTime.parse(strKlineStartTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        DateTime dt = new DateTime(currentTime);
        //当前时间月份最小一天
//        DateTime minDayOfWeek =  dt.dayOfWeek().withMinimumValue().millisOfDay().withMinimumValue();
//
//        String strStartTime = new DateTime(currentTime).toString();
//        String strCandlePointTime = new DateTime(CandlePointTime).toString();

        while (currentTime >= CandlePointTime + intervalTime) {
            CandlePointTime = CandlePointTime + intervalTime;
            //String tmpKlinePointTime = new DateTime(CandlePointTime).toString();
        }
        return CandlePointTime;
    }


    /**
     * 根据不同的interval转换成时间戳
     * 比如一分钟:返回60000毫秒
     * 如果是月就需要传当前时间,月有30和31天
     *
     * @param interval
     * @param currentTime
     * @return
     * @throws Exception
     */
    public long getIntervalMills(String interval, Long currentTime) throws Exception {

        Long intervalTimeByLong = null;
        //1分钟的时间
        long minTime = 1 * 1000 * 60;

        if (interval.equals(PcCandleIntervalDic.interval_min_1)) {
            intervalTimeByLong = minTime * 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_3)) {
            intervalTimeByLong = minTime * 3;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_5)) {
            intervalTimeByLong = minTime * 5;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_15)) {
            intervalTimeByLong = minTime * 15;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_30)) {
            intervalTimeByLong = minTime * 30;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_1)) {
            intervalTimeByLong = minTime * 60;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_2)) {
            intervalTimeByLong = minTime * 60 * 2;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_4)) {
            intervalTimeByLong = minTime * 60 * 4;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_6)) {
            intervalTimeByLong = minTime * 60 * 6;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_8)) {
            intervalTimeByLong = minTime * 60 * 8;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_12)) {
            intervalTimeByLong = minTime * 60 * 12;
        } else if (interval.equals(PcCandleIntervalDic.interval_day_1)) {
            intervalTimeByLong = minTime * 60 * 24;
        } else if (interval.equals(PcCandleIntervalDic.interval_day_3)) {
            intervalTimeByLong = minTime * 60 * 24 * 3;
        } else if (interval.equals(PcCandleIntervalDic.interval_week_1)) {
            intervalTimeByLong = minTime * 60 * 24 * 7;
        } else if (interval.equals(PcCandleIntervalDic.interval_month_1)) {
            DateTime dt = new DateTime(currentTime);
            //当前时间月份最小一天
            DateTime minDayOfMonth = dt.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue();
            //当前时间月份最大一天
            DateTime maxDayOfMonth = dt.plusMonths(1).dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue();
            intervalTimeByLong = maxDayOfMonth.getMillis() - minDayOfMonth.getMillis();
        }
        if (intervalTimeByLong == null)
            throw new Exception("getIntervalTime 方法 intervalTimeByLong is null");
        return intervalTimeByLong;

    }

}
