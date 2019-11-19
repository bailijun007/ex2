package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.dic.expv2.PcCandleIntervalDic;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcCandleDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcCandleIntervalCountPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcCandlePo;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsInputDto;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcCandleControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiPcCandleControllerServiceImpl implements IApiPcCandleControllerService {

    @Autowired
    private IPcCandleDao iPcCandleDao;

    @Override
    public PcCandleStatisticsOutputDto getPcCandleStatisticsData(PcCandleStatisticsInputDto inputDto) throws BizException {
        PcCandleStatisticsOutputDto outputDto=new PcCandleStatisticsOutputDto();
        List<PcCandleIntervalCountPo> list = iPcCandleDao.selectIntervalCount("pc_candle_2019",inputDto.getAsset(),inputDto.getSymbol());
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
