package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.*;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcContractPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcStoringLevelPo;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingInputDto;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingOutputDto;
import com.hupa.exp.servermng.service.def.IApiImportSettingControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
    private IPcStoringLevelDao iPcStoringLevelDao;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private IExpDicDao iExpDicDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public ImportSettingOutputDto importSetting(ImportSettingInputDto inputDto) throws BizException {

        List<AssetPo> assetPoList=iAssetDao.selectActiveList();
        ExpDicPo assetRedisKey= iExpDicDao.selectDicByKey("AssetRedisKey");
        Map<String, AssetPo> assetMap = assetPoList.stream().collect(Collectors.toMap(AssetPo::getRealName, a -> a,(k1, k2)->k1));
        //初始化币种
        if(assetRedisKey!=null)
            redisUtilDb0.hmset(assetRedisKey.getValue(),assetMap);
        else
            redisUtilDb0.hmset("asset",assetMap);

        List<PcContractPo> pcContractPos= iPcContractDao.selectPos();
        //list 转 map
        Map<String, PcContractPo> pairMap = new HashMap<>();
        pcContractPos.forEach(contractPo->{
            pairMap.put(contractPo.getAsset()+"__"+contractPo.getSymbol(),contractPo);
        });
        Map<String, PcContractPo> displayMap = pcContractPos.stream().collect(Collectors.toMap(PcContractPo::getDisplayName, a -> a,(k1, k2)->k1));
        //初始化交易对
        ExpDicPo contractRedisKey= iExpDicDao.selectDicByKey("ContractRedisKey");
        if(iExpDicDao!=null)
        {
            redisUtilDb0.del(contractRedisKey.getValue());
            redisUtilDb0.hmset(contractRedisKey.getValue(),pairMap);
        }
        //初始化展示名
        ExpDicPo displayNameRedisKey= iExpDicDao.selectDicByKey("DisplayNameRedisKey");
        if(displayNameRedisKey!=null)
        {
            redisUtilDb0.del(displayNameRedisKey.getValue());
            redisUtilDb0.hmset(displayNameRedisKey.getValue(),displayMap);
        }
        //初始化仓位保证金等级
        ExpDicPo posLevel= iExpDicDao.selectDicByKey("PosLevel");
        for(PcContractPo contractPo:pcContractPos)
        {
            List<PcStoringLevelPo> storingLevelPoList=iPcStoringLevelDao.selectAllStoringLevelList(contractPo.getSymbol());
//            Map<String, PcStoringLevelPo> storingLevelMap = storingLevelPoList.stream().collect(Collectors.toMap(PcStoringLevelPo::getPair, a -> a,(k1, k2)->k1));
            if(posLevel!=null)
            {
                redisUtilDb0.hdel(posLevel.getValue(),contractPo.getSymbol());
                redisUtilDb0.hset(posLevel.getValue(),contractPo.getSymbol(), JsonUtil.toJsonString(storingLevelPoList));
            }
        }
        ImportSettingOutputDto outputDto=new ImportSettingOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
