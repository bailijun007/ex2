package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.config.redis.CandleRedisConfig;
import com.hupa.exp.base.config.redis.Db5RedisConfig;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.klineconfig.def.IKlineConfigService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpKlineRequestConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.BbCandlePo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpKlineRequestConfigPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.*;
import com.hupa.exp.servermng.help.BbCandleHelper;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiKlineConfigControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ApiKlineConfigControllerServiceImpl implements IApiKlineConfigControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiKlineConfigControllerServiceImpl.class);

    @Autowired
    IKlineConfigService configService;

    @Autowired
    private IExpKlineRequestConfigDao iExpKlineRequestConfigDao;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private Db5RedisConfig candleRedisConfig;

    @Autowired
    private BbCandleHelper bbCandleHelper;

    @Override
    public KlineConfigOutputDto createKlineConfig(KlineConfigInputDto inputDto) throws BizException {

        ExpKlineConfigBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpKlineConfigBizBo.class);
        long id= configService.createKlineConfig(bo);
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        outputDto.setId(id);
        return outputDto;
    }

    @Override
    public KlineConfigInfoOutputDto queryKlineConfigById(KlineConfigInfoInputDto inputDto) throws BizException {
        ExpKlineConfigBizBo bo=configService.queryKlineConfigById(inputDto.getId());
        KlineConfigInfoOutputDto outputDto=new KlineConfigInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setSymbol(bo.getSymbol().equals("null")?"":bo.getSymbol());
        outputDto.setAsset(bo.getAsset().equals("null")?"":bo.getAsset());
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setKlineInterval(bo.getKlineInterval());
        outputDto.setStatTime(String.valueOf(bo.getStatTime()));
        outputDto.setEndTime(String.valueOf(bo.getEndTime()));
        outputDto.setType(String.valueOf(bo.getType()));
        outputDto.setKlineType(String.valueOf(bo.getKlineType()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public KlineConfigOutputDto editKlineConfig(KlineConfigInputDto inputDto) throws BizException {
        ExpKlineConfigBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpKlineConfigBizBo.class);
        ExpKlineConfigBizBo beforeBo= configService.queryKlineConfigById(bo.getId());
        configService.editKlineConfig(bo);

        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.EarningRate.toString(), OperationType.Delete.toString(),
                JSON.toJSONString(beforeBo), JSON.toJSONString(bo));
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        return outputDto;
    }

    @Override
    public KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws BizException {
        KlineConfigListOutputDto outputDto=new KlineConfigListOutputDto();
        ExpKlineConfigListBizBo boList=configService.queryKlineConfigList(inputDto.getKlineType(), inputDto.getCurrentPage(),inputDto.getPageSize());
        List<KlineConfigListOutputPage> pageList=new ArrayList<>();
        for(ExpKlineConfigBizBo bo:boList.getRows())
        {
            KlineConfigListOutputPage po=new KlineConfigListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol().equals("null")?"":bo.getSymbol());
            po.setAsset(bo.getAsset().equals("null")?"":bo.getAsset());
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setKlineInterval(bo.getKlineInterval());
            po.setStatTime(String.valueOf(bo.getStatTime()));
            po.setEndTime(String.valueOf(bo.getEndTime()));
            po.setType(String.valueOf(bo.getType()));
            po.setKlineType(String.valueOf(bo.getKlineType()));
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(boList.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteKlineConfig(DeleteInputDto inputDto) throws BizException {
            ExpUserBizBo user=sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.KlineConfig.toString(), OperationType.Delete.toString(),
                    inputDto.getIds(),"");
            String[] ids=inputDto.getIds().split(",");
            for(String id:ids)
            {
                iExpKlineRequestConfigDao.deleteById(Long.parseLong(id));
            }
            DeleteOutputDto outputDto=new DeleteOutputDto();
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
            return outputDto;

    }

    public RepairKlineListOutputDto getBbCandlePoList(KlineConfigInfoInputDto inputDto) throws BizException{
        RepairKlineListOutputDto output = new RepairKlineListOutputDto();
        try {
               ExpKlineRequestConfigPo expKlineRequestConfigPo = iExpKlineRequestConfigDao.selectPoById(inputDto.getId());
                String asset = expKlineRequestConfigPo.getAsset();//资产
                String symbol = expKlineRequestConfigPo.getSymbol();//交易对
                if (expKlineRequestConfigPo.getStatTime() != null && expKlineRequestConfigPo.getEndTime()!=null){
                    long statTime = expKlineRequestConfigPo.getStatTime();
                    long endTime = expKlineRequestConfigPo.getEndTime() != null ? expKlineRequestConfigPo.getEndTime() : System.currentTimeMillis();//结束时间
                    String[] intervalArr = expKlineRequestConfigPo.getKlineInterval().split(",");//时间区间
                    for (String interval : intervalArr) {
                        if (interval.equals("1")) {
                            //1、获取redis中已存的数据
                            List<BbCandlePo> redisList = new ArrayList<>();
                            Map<String, BbCandlePo> redisMap = new HashMap<>();
                            statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                            endTime = bbCandleHelper.getIntervalPointTime(endTime, endTime, interval);   // 1575951360000 2019/12/10 12:16:0
                            //String redisKey = MessageFormat.format(bbCandleConfig.getBbCandleRedisKey(), asset, symbol, interval);
                            // String redisKey = "bb:kline2Trade:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                            String redisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                            Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(redisKey, String.valueOf(statTime), String.valueOf(endTime));
                            if (lists != null && lists.size() > 0) {
                                List<String> listRedis = new ArrayList<>(lists);
                                for (String str : listRedis) {
                                    String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume
                                    BbCandlePo bbCandlePo = new BbCandlePo();
                                    bbCandlePo.setOpenTime(Long.parseLong(array[0].substring(1)));
                                    bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                                    bbCandlePo.setHigh(new BigDecimal(array[2]));
                                    bbCandlePo.setLow(new BigDecimal(array[3]));
                                    bbCandlePo.setClose(new BigDecimal(array[4]));
                                    bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                                    redisMap.put(String.valueOf(Long.parseLong(array[0].substring(1))), bbCandlePo);
                                }
                            }
                            //2、组合数据返回给前端
                            while (statTime <= endTime) {
                                BbCandlePo candlePo = redisMap.get(String.valueOf(statTime));
                                if (candlePo == null) {
                                    BbCandlePo bbCandlePo = new BbCandlePo();
                                    bbCandlePo.setOpenTime(statTime);
                                } else {
                                    redisList.add(candlePo);
                                }
                                long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                                statTime = nextTime;
                            }
                            List<RepairKlineOutputDto> out = new ArrayList<>();
                            for (BbCandlePo candlePo:  redisList ){
                                RepairKlineOutputDto outputDto = new RepairKlineOutputDto();
                                outputDto.setOpenTime(candlePo.getOpenTime());
                                outputDto.setHigh(candlePo.getHigh());
                                outputDto.setLow(candlePo.getLow());
                                outputDto.setCloseTime(candlePo.getCloseTime());
                                outputDto.setVolume(candlePo.getVolume());
                                out.add(outputDto);
                            }
                            out.sort(Comparator.comparing(RepairKlineOutputDto::getOpenTime).reversed());
                            output.setRows(out);
                            output.setTotal(out.size());
                        }
                    }
                }
        } catch (Exception e) {
            logger.info("bb" + e.getMessage());
        }
        return output;
    }



    @Override
    public KlineConfigOutputDto sendKlineConfig(KlineInfoInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        List<RepairKlineOutputDto> redisMap = inputDto.getRows();
        String interval =String.valueOf("1");
        String dataRedisKey = "bb:kline2Trade:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + interval;//bb:kline2Trade:%{asset}:%{symbol}:%{freq}
       if(redisMap!=null && redisMap.size()>0){
           List<BbCandlePo> redLists = new ArrayList<>();
           for(RepairKlineOutputDto outDto :redisMap){
               BbCandlePo bbCandlePo = new BbCandlePo();
               bbCandlePo.setOpenTime(outDto.getOpenTime());
               bbCandlePo.setOpen(outDto.getOpen());
               bbCandlePo.setHigh(outDto.getHigh());
               bbCandlePo.setLow(outDto.getLow());
               bbCandlePo.setClose(outDto.getClose());
               bbCandlePo.setVolume(outDto.getVolume());
               redLists.add(bbCandlePo);
           }
           //3、发事件通知
           redLists.sort(Comparator.comparing(BbCandlePo::getOpenTime).reversed());
           Map<String, Double> dataMap = new HashMap<>();
           Long statScore = null;
           Long endScore = null;
           redLists.sort(Comparator.comparing(BbCandlePo::getOpenTime));
           for(BbCandlePo po : redLists){
               statScore = (null == statScore) ? po.getOpenTime() : statScore;
               BigDecimal[] bigDecimals = new BigDecimal[6];
               bigDecimals[0] = new BigDecimal(po.getOpenTime());
               bigDecimals[1] = DecimalUtil.trimZero(po.getOpen() == null ? BigDecimal.ZERO : po.getOpen());
               bigDecimals[2] = DecimalUtil.trimZero(po.getHigh() == null ? BigDecimal.ZERO : po.getHigh());
               bigDecimals[3] = DecimalUtil.trimZero(po.getLow() == null ? BigDecimal.ZERO : po.getLow());
               bigDecimals[4] = DecimalUtil.trimZero(po.getClose() == null ? BigDecimal.ZERO : po.getClose());
               bigDecimals[5] = DecimalUtil.trimZero(po.getVolume() == null ? BigDecimal.ZERO : po.getVolume());
               double score = Double.parseDouble(String.valueOf(po.getOpenTime()));
               dataMap.put(JsonUtil.toJsonString(bigDecimals), score);
               endScore = po.getOpenTime();
           }
           RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(dataRedisKey, String.valueOf(statScore), String.valueOf(endScore));
           RedisUtil.redisClientFactory(candleRedisConfig).zAdd(dataRedisKey, dataMap);
           //RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(redisKey, String.valueOf(statScore), String.valueOf(endScore));
           //RedisUtil.redisClientFactory(candleRedisConfig).zAdd(redisKey, dataMap);
       }
       outputDto.setId(Long.parseLong("100"));
       outputDto.setBn(true);
       outputDto.setTime(String.valueOf(System.currentTimeMillis()));
       return outputDto;
    }








}
