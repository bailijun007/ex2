package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db5RedisConfig;
import com.hupa.exp.base.dic.expv2.PcCandleIntervalDic;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcCandleDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcCandleIntervalCountPo;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsInputDto;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.CandleBo;
import com.hupa.exp.servermng.entity.klineconfig.CandleVo;
import com.hupa.exp.servermng.help.BbCandleHelper;
import com.hupa.exp.servermng.service.def.IApiPcCandleControllerService;
import com.hupa.exp.util.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiPcCandleControllerServiceImpl implements IApiPcCandleControllerService {

    private Logger logger = LoggerFactory.getLogger(ApiPcCandleControllerServiceImpl.class);
    @Autowired
    private IPcCandleDao iPcCandleDao;

    @Autowired
    private Db5RedisConfig candleRedisConfig;

    @Autowired
    private BbCandleHelper bbCandleHelper;


    public PcCandleStatisticsOutputDto getBbCandleStatisticsData(PcCandleStatisticsInputDto inputDto) throws BizException {
        PcCandleStatisticsOutputDto outputDto=new PcCandleStatisticsOutputDto();
        try{
            String redisKey = null;
            if(inputDto.getType()!=null){
                if(inputDto.getType().equals("bb")){
                    redisKey = "candle:bb:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + inputDto.getInterval();
                }else if(inputDto.getType().equals("pc")){
                    redisKey = "candle:pc:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + inputDto.getInterval();
                }else{
                    return outputDto;
                }
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<CandleVo> list = new ArrayList<>();
                Long statTime = DateTime.parse(inputDto.getYear()+"-"+inputDto.getMonth(), DateTimeFormat.forPattern("yyyy-MM")).getMillis();
                if(StringUtils.isNotEmpty(inputDto.getDay())){
                    statTime = DateTime.parse(inputDto.getYear()+"-"+inputDto.getMonth()+"-"+inputDto.getDay(), DateTimeFormat.forPattern("yyyy-MM-dd")).getMillis();
                }
                statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, inputDto.getInterval());   // 1575951360000 2019/12/10 12:16:0
                DateTime dataTime = new DateTime(statTime);
                Long endTime = dataTime.plusMonths(1).getMillis()-1;
                if(StringUtils.isNotEmpty(inputDto.getDay())){
                    endTime = dataTime.plusDays(1).getMillis()-1;
                }
                CandleVo candleVo = null;//PcCandleIntervalCountPo candlePo = new PcCandleIntervalCountPo();
                List<CandleBo> dataLists = new ArrayList<>();
                List<String> tradeTimeList = new ArrayList<>();
                List<String> volumeList = new ArrayList<>();
                HashMap<String,CandleBo> hashMap = new HashMap();
                while (dataTime.getMillis() <=  endTime) {
                    candleVo = new CandleVo();
                    candleVo.setTime(sdf.format(new Date(dataTime.getMillis())));
                    candleVo.setAsset(inputDto.getAsset());
                    candleVo.setSymbol(inputDto.getSymbol());
                    candleVo.setInterval(inputDto.getInterval());
                    candleVo.setIntervalCount(0);
                    Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(redisKey, String.valueOf(dataTime.getMillis()), String.valueOf(dataTime.plusHours(1).getMillis()-1));
                    if (lists != null && lists.size() > 0) {
                        List<String> listRedis = new ArrayList<>(lists);
                        CandleBo candleBo = null;
                        for (String str : listRedis) {
                            String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume
                            candleBo = new CandleBo();
                            candleBo.setOpen(new BigDecimal(array[1]));
                            candleBo.setHigh(new BigDecimal(array[2]));
                            candleBo.setLow(new BigDecimal(array[3]));
                            candleBo.setClose(new BigDecimal(array[4]));
                            candleBo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                            candleBo.setTradeTime(Long.parseLong(array[0].substring(1)));
                            if(candleBo!=null){
                                hashMap.put(array[0].substring(1),candleBo);
                            }
                            //dataLists.add(candleBo);
                            //tradeTimeList.add(sdf.format(new Date(Long.parseLong(array[0].substring(1)))));
                            //volumeList.add(array[5].substring(0, array[5].length() - 1));
                        }
                    }
                    candleVo.setIntervalCount(lists.size());
                    list.add(candleVo);
                    dataTime = dataTime.plusHours(1);
                }

                while (statTime < endTime) {
                    statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, inputDto.getInterval());   // 1575951360000 2019/12/10 12:16:0
                    long nextTime = statTime + bbCandleHelper.getIntervalMills(inputDto.getInterval(), statTime);// 1575951420000 2019/12/10 12:17:0
                    CandleBo candleBo = hashMap.get(String.valueOf(statTime));
                    if(candleBo==null){
                        candleBo = new CandleBo();
                   /* candleBo.setOpen(new BigDecimal("0"));
                    candleBo.setHigh(new BigDecimal("0"));
                    candleBo.setLow(new BigDecimal("0"));
                    candleBo.setClose(new BigDecimal("0"));*/
                        candleBo.setVolume(new BigDecimal("0"));
                        candleBo.setTradeTime(statTime);
                    }
                    dataLists.add(candleBo);
                    tradeTimeList.add(sdf.format(new Date(statTime)));
                    volumeList.add(candleBo.getVolume()==null?"":String.valueOf(candleBo.getVolume()));
                    statTime = nextTime;
                }

                Map<String, List<CandleVo>> map = list.stream().collect(Collectors.groupingBy(CandleVo::getInterval));
                Map<Integer, PcCandleStatisticsOutputDto.PcCandleData> intervalMap=new HashMap<>();
                map.forEach((intervalKey, intervalValue) -> {
                    List<String> timeList = intervalValue.stream().map(CandleVo::getTime).collect(Collectors.toList());
                    List<Integer> intervalCountList = intervalValue.stream().map(CandleVo::getIntervalCount).collect(Collectors.toList());
                    List<String> colorList = new ArrayList<>();
                    intervalValue.forEach(valuePo->{
                        if(ConventIntervalToInteger(valuePo.getInterval())==valuePo.getIntervalCount()){
                            colorList.add("green");
                        } else{
                            colorList.add("red");
                        }
                    });
                    PcCandleStatisticsOutputDto.PcCandleData bo = new PcCandleStatisticsOutputDto.PcCandleData();
                    bo.setTimeList(timeList);
                    bo.setCountList(intervalCountList);
                    bo.setColorList(colorList);
                    bo.setVolumeLists(volumeList);
                    bo.setTimeLists(tradeTimeList);
                    bo.setDataLists(dataLists);
                    bo.setAssetSymbol(inputDto.getAsset() + "_" +  inputDto.getSymbol() + "_" + ConventIntervalToDate(intervalKey));
                    intervalMap.put(Integer.parseInt(intervalKey),bo);
                });
                Map<Integer,PcCandleStatisticsOutputDto.PcCandleData> resultMap = new LinkedHashMap<>();
                intervalMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(m->resultMap.put(m.getKey(),m.getValue()));
                outputDto.setCandleStatisticsData(resultMap);
            }
        }catch (Exception e){
            logger.info("ApiPcCandleControllerServiceImpl getBbCandleStatisticsData Exception"+e.getMessage(),e);
        }
        return outputDto;
    }





    @Override
    public PcCandleStatisticsOutputDto getPcCandleStatisticsData(PcCandleStatisticsInputDto inputDto) throws BizException {

        PcCandleStatisticsOutputDto outputDto=new PcCandleStatisticsOutputDto();
        List<PcCandleIntervalCountPo> list = iPcCandleDao.selectIntervalCount("pc_candle_2020",inputDto.getAsset(),inputDto.getSymbol());
        Map<String, Map<String, Map<String, List<PcCandleIntervalCountPo>>>> map1 = list.stream().collect(
                Collectors.groupingBy(PcCandleIntervalCountPo::getAsset,
                        Collectors.groupingBy(PcCandleIntervalCountPo::getSymbol,
                                Collectors.groupingBy(PcCandleIntervalCountPo::getInterval)
                        )));

        Map<String, Map<String,Map<Integer, PcCandleStatisticsOutputDto.PcCandleData>>> assetMap=new HashMap<>();
        map1.forEach((assetKey, assetValue) -> {
            Map<String,Map<Integer, PcCandleStatisticsOutputDto.PcCandleData>> symbolMap=new HashMap<>();
            assetValue.forEach((symbolKey, SymbolValue) -> {
                Map<Integer, PcCandleStatisticsOutputDto.PcCandleData> intervalMap=new HashMap<>();
                SymbolValue.forEach((intervalKey, intervalValue) -> {
                    PcCandleStatisticsOutputDto.PcCandleData bo = new PcCandleStatisticsOutputDto.PcCandleData();
                    List<String> timeList = intervalValue.stream().map(PcCandleIntervalCountPo::getTime).collect(Collectors.toList());
                    List<Integer> intervalCountList = intervalValue.stream().map(PcCandleIntervalCountPo::getIntervalCount).collect(Collectors.toList());
                    List<String> colorList=new ArrayList<>();
                    intervalValue.forEach(valuePo->{
                        if(ConventIntervalToInteger(valuePo.getInterval())==valuePo.getIntervalCount())
                            colorList.add("green");
                        else
                            colorList.add("red");
                    });
                    bo.setCountList(intervalCountList);
                    bo.setTimeList(timeList);
                    bo.setColorList(colorList);
                    bo.setAssetSymbol(assetKey + "_" + symbolKey + "_" + ConventIntervalToDate(intervalKey));
                    intervalMap.put(Integer.parseInt(intervalKey),bo);
                });
                //Map排序
                Map<Integer,PcCandleStatisticsOutputDto.PcCandleData> resultMap=new LinkedHashMap<>();
                intervalMap.entrySet().stream().
                        sorted(Map.Entry.comparingByKey()).
                        forEachOrdered(m->resultMap.put(m.getKey(),m.getValue()));
                symbolMap.put(symbolKey,resultMap);
            });
            assetMap.put(assetKey,symbolMap);
        });
        outputDto.setPcCandleStatisticsData(assetMap);
        return outputDto;
    }

    public String ConventIntervalToDate(String interval) {
        if (interval.equals(PcCandleIntervalDic.interval_min_1)) {
            return "1分钟";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_3)) {
            return "3分钟";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_5)) {
            return "5分钟";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_15)) {
            return "15分钟";
        } else if (interval.equals(PcCandleIntervalDic.interval_min_30)) {
            return "30分钟";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_1)) {
            return "1小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_2)) {
            return "2小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_4)) {
            return "4小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_6)) {
            return "6小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_8)) {
            return "8小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_12)) {
           return "12小时";
        } else if (interval.equals(PcCandleIntervalDic.interval_day_1)) {
            return "1天";
        } else if (interval.equals(PcCandleIntervalDic.interval_day_3)) {
            return "3天";
        } else if (interval.equals(PcCandleIntervalDic.interval_week_1)) {
            return "1周";
        } else if (interval.equals(PcCandleIntervalDic.interval_month_1)) {
            return "1月";
        }
        return "";
    }

    public Integer ConventIntervalToInteger(String interval) {
        if (interval.equals(PcCandleIntervalDic.interval_min_1)) {
            return 60;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_3)) {
            return 20;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_5)) {
            return 12;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_15)) {
            return 4;
        } else if (interval.equals(PcCandleIntervalDic.interval_min_30)) {
            return 2;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_1)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_2)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_4)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_6)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_8)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_hour_12)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_day_1)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_day_3)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_week_1)) {
            return 1;
        } else if (interval.equals(PcCandleIntervalDic.interval_month_1)) {
            return 1;
        }
        return 0;
    }
}
