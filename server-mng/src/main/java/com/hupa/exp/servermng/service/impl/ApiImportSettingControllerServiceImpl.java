package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.*;
import com.hupa.exp.daomysql.entity.po.expv2.*;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingInputDto;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingOutputDto;
import com.hupa.exp.servermng.service.def.IApiImportSettingControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiImportSettingControllerServiceImpl implements IApiImportSettingControllerService {
    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private IPcContractDao iPcContractDao;

    @Autowired
    private IPcPosLevelDao iPcStoringLevelDao;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private IPcFeeDao pcFeeDao;

    @Autowired
    private IExpDicDao iExpDicDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public ImportSettingOutputDto importSetting(ImportSettingInputDto inputDto) throws BizException {

        //1、初始化币种
        List<AssetPo> assetPoList=iAssetDao.selectActiveList();
        ExpDicPo assetRedisKey= iExpDicDao.selectDicByKey("AssetRedisKey");

        List<AssetBizBo> assetBizBos=new ArrayList<>();
        assetPoList.forEach(assetPo -> {
            AssetBizBo bo= ConventObjectUtil.conventObject(assetPo,AssetBizBo.class);
            bo.setChainSymbolId(assetPo.getChainAppointId());
            bo.setSymbol(assetPo.getRealName());
            assetBizBos.add(bo);
        });
        Map<String, AssetBizBo> assetMap = assetBizBos.stream().collect(Collectors.toMap(AssetBizBo::getRealName, a -> a,(k1, k2)->k1));
        //初始化币种
        if(assetRedisKey!=null)
            redisUtilDb0.hmset(assetRedisKey.getValue(),assetMap);
        else
            redisUtilDb0.hmset("asset",assetMap);


        //2、初始化交易对
        List<PcContractPo> pcContractPos= iPcContractDao.selectPos();
        Map<String, PcContractPo> symbolMap = new HashMap<>();
        //list 转 map
        pcContractPos.forEach(contractPo->{
            symbolMap.put(contractPo.getAsset()+"__"+contractPo.getSymbol(),contractPo);
        });
        ExpDicPo contractRedisKey= iExpDicDao.selectDicByKey("ContractRedisKey");
        if(contractRedisKey!=null)
        {
            redisUtilDb0.del(contractRedisKey.getValue());
            redisUtilDb0.hmset(contractRedisKey.getValue(),symbolMap);
        }


        //3、初始化展示名
        Map<String, PcContractPo> displayMap = new HashMap<>();//pcContractPos.stream().collect(Collectors.toMap(PcContractPo::getDisplayName, a -> a,(k1, k2)->k1));
        //list 转 map
        pcContractPos.forEach(contractPo->{
            displayMap.put(contractPo.getAsset()+"__"+contractPo.getSymbol(),contractPo);
        });
        ExpDicPo displayNameRedisKey= iExpDicDao.selectDicByKey("DisplayNameRedisKey");
        if(displayNameRedisKey!=null)
        {
            redisUtilDb0.del(displayNameRedisKey.getValue());
            redisUtilDb0.hmset(displayNameRedisKey.getValue(),displayMap);
        }

        //4、初始化 吃单和挂单的手续费
        List<ExpUserPo> expUserPos =  iExpUserDao.selectListByUserType(1);//用户类型 0.admin 1.交易所注册用户 2.后台管理系统注册的业务员
        for(ExpUserPo expUserPo : expUserPos){
            PcFeePo pcFeePo= pcFeeDao.selectPcFeeByLevel(expUserPo.getFeeLevel());
            if(pcFeePo!=null)
            {
                BigDecimal mPcFee=pcFeePo.getMakerFee().divide(new BigDecimal("100"));
                BigDecimal tPcFee=pcFeePo.getTakerFee().divide(new BigDecimal("100"));
              /*  BigDecimal mPcFee=expUserPo.getMakerFee().divide(new BigDecimal("100"));
                BigDecimal tPcFee=expUserPo.getTakerFee().divide(new BigDecimal("100"));*/
                ExpDicPo dicPo= iExpDicDao.selectDicByKey("PcFeeRedisKey");
                //给redis的不用%所以除100
                if(dicPo!=null) { //dicPo.getValue() "pc_fee"
                    redisUtilDb0.hdel(dicPo.getValue(),"m_"+expUserPo.getId());
                    redisUtilDb0.hset(dicPo.getValue(),"m_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(mPcFee));
                    redisUtilDb0.hdel(dicPo.getValue(),"t_"+expUserPo.getId());
                    redisUtilDb0.hset(dicPo.getValue(),"t_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(tPcFee));
                }
            }
        }

        //5、初始化仓位保证金等级
        ExpDicPo posLevel= iExpDicDao.selectDicByKey("PosLevel");
        for(PcContractPo contractPo:pcContractPos)
        {
            List<PcPosLevelPo> posLevelPoList=iPcStoringLevelDao.selectAllPosLevelList(contractPo.getAsset(),contractPo.getSymbol());
//            Map<String, PcStoringLevelPo> storingLevelMap = storingLevelPoList.stream().collect(Collectors.toMap(PcPosLevelPo::getAsset, a -> a,(k1, k2)->k1));
            if(posLevel!=null && posLevelPoList.size()>0)
            {
                redisUtilDb0.hdel(posLevel.getValue(),contractPo.getAsset()+"__"+contractPo.getSymbol());
                redisUtilDb0.hset(posLevel.getValue(),contractPo.getAsset()+"__"+contractPo.getSymbol(), JsonUtil.toJsonString(posLevelPoList));
            }
        }


        //6、初始化redis
        ImportSettingOutputDto outputDto=new ImportSettingOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


}
