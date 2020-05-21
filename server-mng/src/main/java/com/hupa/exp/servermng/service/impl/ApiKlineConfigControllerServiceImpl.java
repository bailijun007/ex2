package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hp.sh.expv3.api.QueryKlineDataByThirdDataControllerApi;
import com.hupa.exp.base.config.redis.Db5RedisConfig;
import com.hupa.exp.base.dic.expv2.PcCandleIntervalDic;
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
import com.hupa.exp.daomysql.config.Expv2MySqlConfig;
import com.hupa.exp.daomysql.dao.expv2.def.IBbCandleDao;
import com.hupa.exp.daomysql.dao.expv2.def.IDataBaseDao;
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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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

    @Autowired
    private IBbCandleDao bbCandleDao;

    @Autowired
    private IDataBaseDao iDataBaseDao;

    @Autowired
    private Expv2MySqlConfig expv2MySqlConfig;

    @Autowired
    private QueryKlineDataByThirdDataControllerApi queryKlineDataByThirdDataController;

    public static final String BB_TASK_REDIS = "from_exp:bbKlineTask:";//redis的key前罪名 kline:task:from_exp:BB:  %{asset}:%{symbol}:%{freq}

    public static final String PC_TASK_REDIS = "from_exp:pcKlineTask:";//redis的key前罪名 kline:task:from_exp:BB:  %{asset}:%{symbol}:%{freq}

    //private static final String TABLEName="bb_candle_";

    private static final String CANDLE_TABLE_NAME = "candle_bb_";

    @Autowired
    private IBbCandleDao iBbCandleDao;


    @Override
    public KlineConfigOutputDto createKlineConfig(KlineConfigInputDto inputDto) throws BizException {

        ExpKlineConfigBizBo bo = ConventObjectUtil.conventObject(inputDto, ExpKlineConfigBizBo.class);
        long id = configService.createKlineConfig(bo);
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setId(id);
        return outputDto;
    }

    @Override
    public KlineConfigInfoOutputDto queryKlineConfigById(KlineConfigInfoInputDto inputDto) throws BizException {
        ExpKlineConfigBizBo bo = configService.queryKlineConfigById(inputDto.getId());
        KlineConfigInfoOutputDto outputDto = new KlineConfigInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setSymbol(bo.getSymbol().equals("null") ? "" : bo.getSymbol());
        outputDto.setAsset(bo.getAsset().equals("null") ? "" : bo.getAsset());
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
        ExpKlineConfigBizBo bo = ConventObjectUtil.conventObject(inputDto, ExpKlineConfigBizBo.class);
        ExpKlineConfigBizBo beforeBo = configService.queryKlineConfigById(bo.getId());
        configService.editKlineConfig(bo);

        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.EarningRate.toString(), OperationType.Delete.toString(),
                JSON.toJSONString(beforeBo), JSON.toJSONString(bo));
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        return outputDto;
    }

    @Override
    public KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws BizException {
        KlineConfigListOutputDto outputDto = new KlineConfigListOutputDto();
        ExpKlineConfigListBizBo boList = configService.queryKlineConfigList(inputDto.getKlineType(), inputDto.getCurrentPage(), inputDto.getPageSize());
        List<KlineConfigListOutputPage> pageList = new ArrayList<>();
        for (ExpKlineConfigBizBo bo : boList.getRows()) {
            KlineConfigListOutputPage po = new KlineConfigListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol().equals("null") ? "" : bo.getSymbol());
            po.setAsset(bo.getAsset().equals("null") ? "" : bo.getAsset());
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
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.KlineConfig.toString(), OperationType.Delete.toString(),
                inputDto.getIds(), "");
        String[] ids = inputDto.getIds().split(",");
        for (String id : ids) {
            iExpKlineRequestConfigDao.deleteById(Long.parseLong(id));
        }
        DeleteOutputDto outputDto = new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;

    }


    /**
     * 修补实时交易数据
     * 发通知到redis中，修补一段时间内的K线
     * 从什么时候修补到什么时候的K线数据）
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    public KlineConfigOutputDto repairKlineConfig(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setBn(false);
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = String.valueOf("1");
            Long statTime = inputDto.getStatTime();
            Long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();//结束时间
            if (statTime != null && endTime != null && asset != null && symbol != null) {
                //String candleRedisKey = BB_TASK_REDIS + asset + ":" + symbol + ":" + interval;
                String candleRedisKey = null;
                if (inputDto.getKlineType() == 0) {
                    candleRedisKey = BB_TASK_REDIS + asset + ":" + symbol + ":" + interval;
                } else if (inputDto.getKlineType() == 1) {
                    candleRedisKey = PC_TASK_REDIS + asset + ":" + symbol + ":" + interval;
                }
                if (candleRedisKey != null) {

                    Map<String, Double> dataEventMap = new HashMap<>();

                    while (statTime <= endTime) {
                        statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                        dataEventMap.put("" + statTime, Long.valueOf(statTime).doubleValue());
                        long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                        statTime = nextTime;
                        if (dataEventMap.size() == 1440) {
                            bbCandleHelper.writeToRedisEvent(candleRedisKey, dataEventMap);
                            dataEventMap.clear();
                        }
                    }

                    if (dataEventMap.size() > 0) {
                        bbCandleHelper.writeToRedisEvent(candleRedisKey, dataEventMap);
                        dataEventMap.clear();
                    }
                    outputDto.setBn(true);
                    outputDto.setMsg("操作成功");
                }
            } else {
                outputDto.setBn(false);
                outputDto.setMsg("没有设置补充K线时间");
            }
        } catch (Exception e) {
            logger.error("repairKlineConfig Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    /**
     * 按时间查询的
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public RepairKlineListOutputDto getIntervalKline(KlineConfigInputDto inputDto) throws BizException {
        RepairKlineListOutputDto output = new RepairKlineListOutputDto();
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = "1";
            long statTime = inputDto.getStatTime();
            long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();//结束时间
            if (inputDto.getStatTime() != null && inputDto.getEndTime() != null && asset != null && symbol != null) {
                //String redisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                String redisKey = null;
                if (inputDto.getKlineType() == 0) {
                    redisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                } else if (inputDto.getKlineType() == 1) {
                    redisKey = "candle:pc:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                }
                if (redisKey != null) {
                    //1、获取redis中已存的数据
                    List<BbCandlePo> redisList = new ArrayList<>();
                    Map<String, BbCandlePo> redisMap = new HashMap<>();
                    statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                    endTime = bbCandleHelper.getIntervalPointTime(endTime, endTime, interval);   // 1575951360000 2019/12/10 12:16:0
                    DateTime dt = new DateTime(statTime);
                    dt = dt.plusHours(2);
                    if (dt.getMillis() < endTime) {
                        endTime = dt.getMillis();
                        endTime = bbCandleHelper.getIntervalPointTime(endTime, endTime, interval);   // 1575951360000 2019/12/10 12:16:0
                    }
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
                            candlePo = new BbCandlePo();
                            candlePo.setOpenTime(statTime);
                        }
                        redisList.add(candlePo);
                        long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                        statTime = nextTime;
                    }
                    List<RepairKlineOutputDto> out = new ArrayList<>();
                    for (BbCandlePo candlePo : redisList) {
                        RepairKlineOutputDto outputDto = new RepairKlineOutputDto();
                        outputDto.setOpenTime(candlePo.getOpenTime());
                        outputDto.setOpen(candlePo.getOpen());
                        outputDto.setHigh(candlePo.getHigh());
                        outputDto.setLow(candlePo.getLow());
                        outputDto.setClose(candlePo.getClose());
                        outputDto.setVolume(candlePo.getVolume());
                        out.add(outputDto);
                    }
                    out.sort(Comparator.comparing(RepairKlineOutputDto::getOpenTime).reversed());
                    output.setRows(out);
                    output.setTotal(out.size());
                }
            }
        } catch (Exception e) {
            logger.info("getIntervalKline Exception" + e.getMessage());
        }
        return output;
    }


    /**
     * 手动提交 修补K线
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public KlineConfigOutputDto getManualKline(KlineConfigInputDto inputDto) throws BizException {//KlineInfoInputDto
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        List<RepairKlineOutputDto> redisMap = inputDto.getRows();
        String interval = String.valueOf("1");
        String asset = inputDto.getAsset();//资产
        String symbol = inputDto.getSymbol();//交易对
        if (asset != null && symbol != null) {
            String dataRedisKey = null;
            if (inputDto.getKlineType() == 0) {
                dataRedisKey = "bb:kline2Trade:" + asset + ":" + symbol + ":" + interval;
            } else if (inputDto.getKlineType() == 1) {
                dataRedisKey = "pc:kline2Trade:" + asset + ":" + symbol + ":" + interval;
            }

            //之前数据：记日志
            String redisKey = null;
            if (inputDto.getKlineType() == 0) {
                redisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
            } else if (inputDto.getKlineType() == 1) {
                redisKey = "candle:pc:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
            }
            KlineConfigInputDto beforeBo = new KlineConfigInputDto();
            beforeBo.setAsset(asset);
            beforeBo.setKlineType(inputDto.getKlineType());
            beforeBo.setSymbol(symbol);
            if (redisMap.size() > 0) {
                redisMap.sort(Comparator.comparing(RepairKlineOutputDto::getOpenTime));
                Long statTime = redisMap.get(0).getOpenTime();
                Long endTime = redisMap.get(redisMap.size() - 1).getOpenTime();
                Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(redisKey, String.valueOf(statTime), String.valueOf(endTime));
                if (lists != null && lists.size() > 0) {
                    List<RepairKlineOutputDto> rowLists = new ArrayList<>();
                    List<String> listRedis = new ArrayList<>(lists);
                    for (String str : listRedis) {
                        String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume
                        RepairKlineOutputDto bbCandlePo = new RepairKlineOutputDto();
                        bbCandlePo.setOpenTime(Long.parseLong(array[0].substring(1)));
                        bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                        bbCandlePo.setHigh(new BigDecimal(array[2]));
                        bbCandlePo.setLow(new BigDecimal(array[3]));
                        bbCandlePo.setClose(new BigDecimal(array[4]));
                        bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                        rowLists.add(bbCandlePo);
                    }
                    beforeBo.setRows(rowLists);
                }
            }
            //String dataRedisKey = "bb:kline2Trade:" + asset + ":" + symbol + ":" + interval;
            if (dataRedisKey != null) {
                if (redisMap != null && redisMap.size() > 0) {
                    List<BbCandlePo> redLists = new ArrayList<>();
                    for (RepairKlineOutputDto outDto : redisMap) {
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
                    for (BbCandlePo po : redLists) {
                        statScore = (null == statScore) ? po.getOpenTime() : statScore;
                        BigDecimal[] bigDecimals = new BigDecimal[7];
                        bigDecimals[0] = new BigDecimal("1");
                        bigDecimals[1] = new BigDecimal(po.getOpenTime());
                        bigDecimals[2] = DecimalUtil.trimZero(po.getOpen() == null ? BigDecimal.ZERO : po.getOpen());
                        bigDecimals[3] = DecimalUtil.trimZero(po.getHigh() == null ? BigDecimal.ZERO : po.getHigh());
                        bigDecimals[4] = DecimalUtil.trimZero(po.getLow() == null ? BigDecimal.ZERO : po.getLow());
                        bigDecimals[5] = DecimalUtil.trimZero(po.getClose() == null ? BigDecimal.ZERO : po.getClose());
                        bigDecimals[6] = DecimalUtil.trimZero(po.getVolume() == null ? BigDecimal.ZERO : po.getVolume());
                        double score = Double.parseDouble(String.valueOf(po.getOpenTime()));
                        dataMap.put(JsonUtil.toJsonString(bigDecimals), score);
                        endScore = po.getOpenTime();
                    }
                    RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(dataRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                    RedisUtil.redisClientFactory(candleRedisConfig).zAdd(dataRedisKey, dataMap);
                    //记日志
                    logService.createOperationLog(user.getId(), user.getUserName(), "RepairKline",
                            OperationType.Insert.toString(), JsonUtil.toJsonString(beforeBo),
                            JsonUtil.toJsonString(inputDto));
                }
            }
        }
        outputDto.setBn(true);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 撤销某个时间段k线数据
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public KlineConfigOutputDto getRevokeKline(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        try {
            ExpUserBizBo user = sessionHelper.getUserInfoBySession();
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = "1";
            long statTime = inputDto.getStatTime();
            long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();//结束时间
            if (asset != null && symbol != null) {
                String dataRedisKey = null;
                if (inputDto.getKlineType() == 0) {
                    dataRedisKey = "bb:kline2Trade:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + interval;
                } else if (inputDto.getKlineType() == 1) {
                    dataRedisKey = "pc:kline2Trade:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + interval;
                }

                //之前数据：记日志
                String redisKey = null;
                if (inputDto.getKlineType() == 0) {
                    redisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                } else if (inputDto.getKlineType() == 1) {
                    redisKey = "candle:pc:" + asset + ":" + symbol + ":" + interval;//candle:bb:{0}:{1}:{2}
                }
                KlineConfigInputDto beforeBo = new KlineConfigInputDto();
                beforeBo.setAsset(asset);
                beforeBo.setKlineType(inputDto.getKlineType());
                beforeBo.setSymbol(symbol);
                Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(redisKey, String.valueOf(statTime), String.valueOf(endTime));
                if (lists != null && lists.size() > 0) {
                    List<RepairKlineOutputDto> rowLists = new ArrayList<>();
                    List<String> listRedis = new ArrayList<>(lists);
                    for (String str : listRedis) {
                        String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume
                        RepairKlineOutputDto bbCandlePo = new RepairKlineOutputDto();
                        bbCandlePo.setOpenTime(Long.parseLong(array[0].substring(1)));
                        bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                        bbCandlePo.setHigh(new BigDecimal(array[2]));
                        bbCandlePo.setLow(new BigDecimal(array[3]));
                        bbCandlePo.setClose(new BigDecimal(array[4]));
                        bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                        rowLists.add(bbCandlePo);
                    }
                    beforeBo.setRows(rowLists);
                }

                if (dataRedisKey != null) {
                    while (statTime <= endTime) {
                        statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                        long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                        //bbCandleHelper.writeToRedisRevoke(dataRedisKey, String.valueOf(statTime), String.valueOf(statTime));
                        BigDecimal[] bigDecimals = new BigDecimal[3];
                        bigDecimals[0] = DecimalUtil.trimZero(BigDecimal.ZERO);
                        bigDecimals[1] = new BigDecimal(statTime);
                        bigDecimals[2] = new BigDecimal(endTime);
                        RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(dataRedisKey, String.valueOf(statTime), String.valueOf(endTime));
                        RedisUtil.redisClientFactory(candleRedisConfig).zAdd(dataRedisKey, Double.parseDouble(String.valueOf(statTime)), JsonUtil.toJsonString(bigDecimals));
                        statTime = nextTime;
                    }
                    outputDto.setBn(true);
                    outputDto.setMsg("操作成功");
                    //记日志
                    logService.createOperationLog(user.getId(), user.getUserName(), "RepairKline",
                            OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo),
                            JsonUtil.toJsonString(inputDto));
                }
            } else {
                outputDto.setBn(false);
                outputDto.setMsg("撤销参数有错");
            }
        } catch (Exception e) {
            logger.error("getRevokeKline Exception" + e.getMessage(), e);
        }
        outputDto.setBn(true);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    /**
     * 重置某个时间段K线数据
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public KlineConfigOutputDto getResetKline(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setBn(false);
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = inputDto.getKlineInterval();
            long statTime = inputDto.getStatTime();
            long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();//结束时间
            if (asset != null && symbol != null && interval != null) {
                String dataRedisKey = null;
                if (inputDto.getKlineType() == 0) {
                    dataRedisKey = "bb:kline:updateEvent:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + interval;
                } else if (inputDto.getKlineType() == 1) {
                    dataRedisKey = "pc:kline:updateEvent:" + inputDto.getAsset() + ":" + inputDto.getSymbol() + ":" + interval;
                }
                if (dataRedisKey != null) {
                    Map<String, Double> dataEventMap = new HashMap<>();
                    while (statTime <= endTime) {
                        statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                        dataEventMap.put("" + statTime, Long.valueOf(statTime).doubleValue());
                        long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                        statTime = nextTime;
                        if (dataEventMap.size() == 1440) {
                            bbCandleHelper.writeToRedisEvent(dataRedisKey, dataEventMap);
                            dataEventMap.clear();
                        }
                    }
                    if (dataEventMap.size() > 0) {
                        bbCandleHelper.writeToRedisEvent(dataRedisKey, dataEventMap);
                        dataEventMap.clear();
                    }
                    outputDto.setBn(true);
                    outputDto.setMsg("操作成功");
                }
            } else {
                outputDto.setBn(false);
                outputDto.setMsg("重置参数有错");
            }
        } catch (Exception e) {
            logger.error("getResetKline Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public KlineConfigOutputDto repairKlineByThirdData(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setBn(false);
        try {
            String tableName = inputDto.getTableName();
            Integer klineType = inputDto.getKlineType();
            String asset = inputDto.getAsset();
            //交易对
            String symbol = inputDto.getSymbol();
            String klineInterval = inputDto.getKlineInterval();
//            Long statTime = inputDto.getStatTime();
//            Long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();//结束时间
            Long statTime =inputDto.getStatTimeInMs();
            Long endTime =inputDto.getEndTimeInMs();
            if (tableName != null && symbol != null && statTime != null && endTime != null) {
                //klineType前段参数 0:币币,1:合约，后端：1币币，2：合约，这里需要做转化
                Integer type = null;
                if (klineType == 0) {
                    type = 1;
                }else if(klineType == 1){
                    type = 2;
                }
                queryKlineDataByThirdDataController.queryKlineDataByThirdData(tableName, type, asset, symbol, klineInterval, statTime, endTime);
                outputDto.setBn(true);
                outputDto.setMsg("操作成功");
            } else {
                outputDto.setBn(false);
                outputDto.setMsg("重置参数有错");
            }
        } catch (Exception e) {
            logger.error("getResetKline Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    /**
     * 修补第三方历史K线数据
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    public KlineConfigOutputDto getThirdDataKline(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setBn(false);
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = String.valueOf("1");
            Long statTime = inputDto.getStatTime();
            Long endTime = inputDto.getEndTime() == null ? System.currentTimeMillis() : inputDto.getEndTime();
            String table_Name = null;
            if (asset != null && symbol != null && inputDto.getStatTime() != null) {
                String dataRedisKey = null;
                String eventRedisKey = null;
                //String dataRedisKey = "kline:bb:thirdData:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                //String eventRedisKey = "kline:bb:thirdDataUpdateEvent:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                if (inputDto.getKlineType() == 0) {
                    dataRedisKey = "kline:bb:thirdData:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                    eventRedisKey = "kline:bb:thirdDataUpdateEvent:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                    table_Name = "bb_candle_";
                } else if (inputDto.getKlineType() == 1) {
                    dataRedisKey = "kline:pc:thirdData:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                    eventRedisKey = "kline:pc:thirdDataUpdateEvent:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                    table_Name = "pc_candle_";
                }
                if (dataRedisKey != null && eventRedisKey != null && table_Name != null) {
                    //获取开始和结束时间
                    statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                    endTime = bbCandleHelper.getIntervalPointTime(endTime, endTime, interval);   // 1575951360000 2019/12/10 12:16:0
                    List<BbCandlePo> klineDataPos = null;
                    DateTime dataTime = new DateTime(statTime);
                    while (dataTime.getMillis() < endTime) {
                        if (dataTime.plusMonths(1).getMillis() < endTime) {
                            klineDataPos = bbCandleDao.selectPosByInterval(table_Name + String.valueOf(new DateTime(dataTime).getYear()), asset, symbol, interval, dataTime.getMillis(), dataTime.plusMonths(1).getMillis());
                            writeRedis(dataRedisKey, eventRedisKey, klineDataPos);
                           /* if (klineDataPos == null) {
                                System.out.println(dataTime + "---" + dataTime.getMillis() + "---" + dataTime.plusMonths(1).getMillis() + "---" + table_Name + String.valueOf(new DateTime(dataTime).getYear()));
                            } else {
                                System.out.println(dataTime + "---" + dataTime.getMillis() + "---" + dataTime.plusMonths(1).getMillis() + "---" + table_Name + String.valueOf(new DateTime(dataTime).getYear()) + "---" + klineDataPos.size());
                            }*/
                            dataTime = dataTime.plusMonths(1);
                        } else {
                            //查询历史数据
                            klineDataPos = bbCandleDao.selectPosByInterval(table_Name + String.valueOf(new DateTime(dataTime).getYear()), asset, symbol, interval, dataTime.getMillis(), endTime);
                            writeRedis(dataRedisKey, eventRedisKey, klineDataPos);
                           /* if (klineDataPos == null) {
                                System.out.println(dataTime + "*****" + dataTime.getMillis() + "*****" + table_Name + String.valueOf(new DateTime(dataTime).getYear()));
                            } else {
                                System.out.println(dataTime + "*****" + dataTime.getMillis() + "*****" + table_Name + String.valueOf(new DateTime(dataTime).getYear()) + "***" + klineDataPos.size());
                            }*/
                            dataTime = new DateTime(endTime);
                            ;
                        }
                    }
                    outputDto.setBn(true);
                }
            }
        } catch (Exception e) {
            logger.error("getThirdDataKline Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    public void writeRedis(String dataRedisKey, String eventRedisKey, List<BbCandlePo> klineDataPos) {
        if (klineDataPos != null && klineDataPos.size() > 0) {
            klineDataPos.sort(Comparator.comparing(BbCandlePo::getOpenTime));
            List<List<BbCandlePo>> partition = Lists.partition(klineDataPos, 1440);
            for (List<BbCandlePo> dataPos : partition) {
                Map<String, Double> dataMap = new HashMap<>();
                Map<String, Double> dataEventMap = new HashMap<>();
                Long statScore = null;
                Long endScore = null;
                for (BbCandlePo po : dataPos) {
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
                    dataEventMap.put("" + score, score);
                    endScore = po.getOpenTime();
                }
                RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(dataRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                RedisUtil.redisClientFactory(candleRedisConfig).zAdd(dataRedisKey, dataMap);
                RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(eventRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                RedisUtil.redisClientFactory(candleRedisConfig).zAdd(eventRedisKey, dataEventMap);
                // outputDto.setBn(true);
            }
        }
    }

    /**
     * 按分钟级别的修补，第三方历史K线数据
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    public KlineConfigOutputDto getThirdDataIntervalKline(KlineConfigInputDto inputDto) throws BizException {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        /*  outputDto.setBn(false);
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval =String.valueOf("1");
            Long statTime = inputDto.getStatTime();
            Long endTime = inputDto.getEndTime();
            if (asset!=null && symbol!=null && statTime!=null && endTime!=null){
                String dataRedisKey = "kline:bb:thirdData:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                String eventRedisKey = "kline:bb:thirdDataUpdateEvent:" + asset + ":" + symbol + ":" + interval;//kline:bb:thirdData:%{asset}:%{symbol}:%{freq}
                statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);   // 1575951360000 2019/12/10 12:16:0
                endTime = bbCandleHelper.getIntervalPointTime(endTime, endTime, interval);   // 1575951360000 2019/12/10 12:16:0
                List<BbCandlePo> klineDataPos = bbCandleDao.selectPosByInterval(tableName + String.valueOf(new DateTime(statTime).getYear()), asset, symbol, interval, statTime, endTime);
                if (klineDataPos != null && klineDataPos.size()> 0) {
                    klineDataPos.sort(Comparator.comparing(BbCandlePo::getOpenTime));
                    Map<String, Double> dataMap = new HashMap<>();
                    Map<String, Double> dataEventMap = new HashMap<>();
                    Long statScore = null;
                    Long endScore = null;
                    for (BbCandlePo po : klineDataPos) {
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
                        dataEventMap.put("" + score, score);
                        endScore = po.getOpenTime();
                    }
                    if(dataMap!=null && dataMap.size()>0){
                        RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(dataRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                        RedisUtil.redisClientFactory(candleRedisConfig).zAdd(dataRedisKey, dataMap);
                        RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(eventRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                        RedisUtil.redisClientFactory(candleRedisConfig).zAdd(eventRedisKey, dataEventMap);
                        outputDto.setBn(true);
                    }
                    logger.info("{} {} {}—>{}：处理完毕", tableName+String.valueOf(new DateTime(statTime).getYear()), symbol, statScore, endScore);
                }
            }
        } catch (Exception e) {
            logger.error("getThirdDataKline Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));*/
        return outputDto;
    }

    /**
     * 同步线上K线到DB
     *
     * @param inputDto
     * @return
     */
    private KlineConfigOutputDto getRealtTime(KlineConfigInputDto inputDto) {
        KlineConfigOutputDto outputDto = new KlineConfigOutputDto();
        outputDto.setBn(false);
        try {
            String asset = inputDto.getAsset();//资产
            String symbol = inputDto.getSymbol();//交易对
            String interval = String.valueOf("1");
            Long statTime = inputDto.getStatTime();
            Long endTime = inputDto.getEndTime();
            String candleRedisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:USDT:EOS_USDT:1
            DateTime dataTime = new DateTime(statTime);
            while (dataTime.getMillis() < endTime) {
                if (dataTime.plusMonths(1).getMillis() < endTime) {
                    //按月查询
                    Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(candleRedisKey, String.valueOf(dataTime.getMillis()), String.valueOf(dataTime.plusMonths(1).getMillis()));
                    String fullTableName = CANDLE_TABLE_NAME + String.valueOf(new DateTime(dataTime).getYear());//表名
                    writeDb(lists, fullTableName, asset, symbol, interval);
                    dataTime = dataTime.plusMonths(1);
                } else {
                    Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(candleRedisKey, String.valueOf(dataTime.getMillis()), String.valueOf(endTime));
                    String fullTableName = CANDLE_TABLE_NAME + String.valueOf(new DateTime(dataTime).getYear());//表名
                    writeDb(lists, fullTableName, asset, symbol, interval);
                    dataTime = new DateTime(endTime);
                    ;
                }
            }
        } catch (Exception e) {
            logger.error("getRealtTime Exception" + e.getMessage(), e);
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 写入数据库
     *
     * @param lists
     * @param fullTableName
     * @param asset
     * @param symbol
     * @param interval
     */
    public void writeDb(Set<String> lists, String fullTableName, String asset, String symbol, String interval) {
        if (lists != null && lists.size() > 0) {
            List<BbCandlePo> candleInsertList = new ArrayList<>();
            List<BbCandlePo> candleUpdateList = new ArrayList<>();
            for (String str : lists) {
                String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume 开、高、低、收为当前交易数据的交易金额
                BbCandlePo bbCandlePo = null;
                Long openTime = Long.parseLong(array[0].substring(1));
                String tableName = CANDLE_TABLE_NAME + String.valueOf(new DateTime(openTime).getYear());
                bbCandlePo = iBbCandleDao.selectPoByOpenTime(tableName, asset, symbol, interval, bbCandlePo.getOpenTime());
                if (bbCandlePo == null) {
                    bbCandlePo = new BbCandlePo();
                    bbCandlePo.setOpenTime(openTime);
                    bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                    bbCandlePo.setHigh(new BigDecimal(array[2]));
                    bbCandlePo.setLow(new BigDecimal(array[3]));
                    bbCandlePo.setClose(new BigDecimal(array[4]));
                    bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                    bbCandlePo.setAsset(asset);
                    bbCandlePo.setSymbol(symbol);
                    bbCandlePo.setTableName(tableName);
                    candleInsertList.add(bbCandlePo);
                } else {
                    bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                    bbCandlePo.setHigh(new BigDecimal(array[2]));
                    bbCandlePo.setLow(new BigDecimal(array[3]));
                    bbCandlePo.setClose(new BigDecimal(array[4]));
                    bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                    candleUpdateList.add(bbCandlePo);
                }
            }
            //批量插入数据
            candleInsertList.sort(Comparator.comparing(BbCandlePo::getOpenTime));
            List<List<BbCandlePo>> partitionInsert = Lists.partition(candleInsertList, 100);
            for (List<BbCandlePo> dataInsertPos : partitionInsert) {
                iBbCandleDao.insertBatch(fullTableName, dataInsertPos);
            }
            candleInsertList.clear();
            //批量修改数据
            candleUpdateList.sort(Comparator.comparing(BbCandlePo::getOpenTime));
            List<List<BbCandlePo>> partitionUpdate = Lists.partition(candleUpdateList, 100);
            for (List<BbCandlePo> dataInsertPos : partitionUpdate) {
                iBbCandleDao.updateBatch(fullTableName, dataInsertPos);
            }
            candleUpdateList.clear();
        }
    }


    /**
     * 每天同步一天线上K线到DB
     */
    private void getTasks() {
        //timer.scheduleAtFixedRate(() -> {
        try {
            List<ExpKlineRequestConfigPo> klineRequestConfigPos = iExpKlineRequestConfigDao.selectActiveKlineConfigByType(2, 1);
            for (ExpKlineRequestConfigPo expKlineRequestConfigPo : klineRequestConfigPos) {
                //设置时间段  时间周期30天。每天运行一次
                DateTime nowDay = DateTime.now();//.plusDays(1);
                String maxTime = nowDay.toString("yyyy-MM-dd " + "00:00:00");
                long endTime = DateTime.parse(maxTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
                DateTime preDay = nowDay.plusDays(-1);
                String minTime = preDay.toString("yyyy-MM-dd " + "00:00:00");
                long statTime = DateTime.parse(minTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();

                String asset = expKlineRequestConfigPo.getAsset();//资产
                String symbol = expKlineRequestConfigPo.getSymbol();//交易对
                String interval = String.valueOf("1");
                String candleRedisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:USDT:EOS_USDT:1
                Set<String> lists = RedisUtil.redisClientFactory(candleRedisConfig).zRevRangeByScore(candleRedisKey, String.valueOf(statTime), String.valueOf(endTime));
                if (lists != null && lists.size() > 0) {
                    List<BbCandlePo> candleInsertList = new ArrayList<>();
                    for (String str : lists) {
                        String[] array = str.split(",");//[1577774220000,null,null,null,null,0] time,open,high,low,close.volume 开、高、低、收为当前交易数据的交易金额
                        BbCandlePo bbCandlePo = new BbCandlePo();
                        bbCandlePo.setOpenTime(Long.parseLong(array[0].substring(1)));
                        bbCandlePo.setOpen(new BigDecimal(array[1]));//没有就用自己的交易价格当开盘价   有历史交易记录的话会更新这条数据的收盘价
                        bbCandlePo.setHigh(new BigDecimal(array[2]));
                        bbCandlePo.setLow(new BigDecimal(array[3]));
                        bbCandlePo.setClose(new BigDecimal(array[4]));
                        bbCandlePo.setVolume(new BigDecimal(array[5].substring(0, array[5].length() - 1)));
                        candleInsertList.add(bbCandlePo);
                    }
                    String fullTableName = CANDLE_TABLE_NAME + String.valueOf(new DateTime(statTime).getYear());//表名
                    candleInsertList.sort(Comparator.comparing(BbCandlePo::getOpenTime));
                    List<List<BbCandlePo>> partitionInsert = Lists.partition(candleInsertList, 100);
                    for (List<BbCandlePo> dataInsertPos : partitionInsert) {
                        iBbCandleDao.insertBatch(fullTableName, dataInsertPos);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("getTasks Exception" + e.getMessage(), e);
        }
        //}, 0, 1, TimeUnit.SECONDS);
    }


    public void SupCandle() {
        //timer.scheduleAtFixedRate(() -> {
        try {
            Map<String, BigDecimal> upCloseMap = new HashMap<>();
            //1、获取补充K线 配置
            List<ExpKlineRequestConfigPo> klineRequestConfigPos = iExpKlineRequestConfigDao.selectActiveKlineConfigByType(1, 1);
            for (ExpKlineRequestConfigPo expKlineRequestConfigPo : klineRequestConfigPos) {
                if (expKlineRequestConfigPo.getStatTime() == null)
                    continue;
                String asset = expKlineRequestConfigPo.getAsset();//资产
                String symbol = expKlineRequestConfigPo.getSymbol();//交易对
                String[] intervalArr = expKlineRequestConfigPo.getKlineInterval().split(",");//时间区间
                long statTime = expKlineRequestConfigPo.getStatTime();
                long endTime = expKlineRequestConfigPo.getEndTime() != null ? expKlineRequestConfigPo.getEndTime() : System.currentTimeMillis();//结束时间

                List<BbCandlePo> klineDataPos = null; //bbCandleDao.selectPosByInterval(tableName + String.valueOf(new DateTime(statTime).getYear()), asset, symbol, String.valueOf("1"), statTime, endTime);
                for (String interval : intervalArr) {
                    String candleRedisKey = "candle:bb:" + asset + ":" + symbol + ":" + interval;//candle:bb:USDT:EOS_USDT:1
                    if (klineDataPos != null && klineDataPos.size() > 0) {
                        klineDataPos.sort(Comparator.comparing(BbCandlePo::getOpenTime));
                        if (interval.equals(PcCandleIntervalDic.interval_min_1)) {
                            Map<String, Double> dataMap = new HashMap<>();
                            Long statScore = null;
                            Long endScore = null;
                            for (BbCandlePo po : klineDataPos) {
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
                            if (dataMap != null && dataMap.size() > 0) {
                                RedisUtil.redisClientFactory(candleRedisConfig).zRemRangeByScore(candleRedisKey, String.valueOf(statScore), String.valueOf(endScore));
                                RedisUtil.redisClientFactory(candleRedisConfig).zAdd(candleRedisKey, dataMap);
                            }
                        } else {
                            while (statTime < endTime) {
                                //4.1 根据开始时间和结束时间查询，查询交易表中的数据
                                statTime = bbCandleHelper.getIntervalPointTime(statTime, statTime, interval);
                                long nextTime = statTime + bbCandleHelper.getIntervalMills(interval, statTime);// 1575951420000 2019/12/10 12:17:0
                                //所有一分钟的K线数据
                                klineDataPos.sort(Comparator.comparing(BbCandlePo::getOpenTime));//按时间正序排列
                                BigDecimal close = klineDataPos.get(klineDataPos.size() - 1).getClose();//收
                                klineDataPos.sort(Comparator.comparing(BbCandlePo::getHigh).reversed());//按最高价倒序排列
                                BigDecimal high = klineDataPos.get(0).getHigh();//高
                                klineDataPos.sort(Comparator.comparing(BbCandlePo::getLow));//按最低价正序排列
                                BigDecimal low = klineDataPos.get(0).getLow();//低

                                BbCandlePo candlePo = new BbCandlePo();
                                //candlePo.setTableName(tableName);
                                candlePo.setAsset(asset);
                                candlePo.setSymbol(symbol);
                                candlePo.setInterval(interval);
                                candlePo.setOpenTime(statTime);
                                candlePo.setCloseTime(nextTime - 1);
                                candlePo.setOpen(new BigDecimal("0"));
                                candlePo.setHigh(high);
                                candlePo.setLow(low);
                                candlePo.setClose(close);
                                candlePo.setCtime(System.currentTimeMillis());
                                candlePo.setMtime(System.currentTimeMillis());
                                candlePo.setVolume(new BigDecimal("0"));
                                klineDataPos.forEach(po -> {
                                    candlePo.setVolume(candlePo.getVolume().add(po.getVolume()));
                                });
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("pc SupCandle--修复出现异常" + e.getMessage());
        }
        //}, 0, 10, TimeUnit.SECONDS);
    }


}
