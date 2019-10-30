package com.hupa.exp.bizother.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.bizother.component.BizAccountComponent;
import com.hupa.exp.bizother.entity.account.PcFeeBizBo;
import com.hupa.exp.bizother.entity.account.PcFeeListBizBo;
import com.hupa.exp.bizother.entity.account.UserPcFee;
import com.hupa.exp.bizother.service.account.def.IPcFeeBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomongo.dao.expv2.def.IFundWithdrawAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundWithdrawAssetMongoPo;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcFeeDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcTradeVolumeDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcFeePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PcFeeBizImpl implements IPcFeeBiz {
    @Autowired
    private IPcFeeDao iPcFeeDao;
    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Autowired
    private IPcTradeVolumeDao iPcTradeVolumeDao;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private IFundWithdrawAssetMongoDao iFundWithdrawSymbolDao;

    @Autowired
    private BizAccountComponent bizAccountComponent;

    @Override
    public PcFeeBizBo getPcFeeById(long id) {
        PcFeePo po=iPcFeeDao.selectPoById(id);
        PcFeeBizBo bo= ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
        return bo;
    }

    @Override
    public PcFeeListBizBo getPcFeePageData(long currentPage, long pageSize) {
        IPage<PcFeePo> pageData=iPcFeeDao.selectPcFeePageData(currentPage,pageSize);
        PcFeeListBizBo pageBizbo=new PcFeeListBizBo();
        pageBizbo.setTotal(pageData.getTotal());
        List<PcFeeBizBo> pcFeeBizBos=new ArrayList<>();
        for(PcFeePo po:pageData.getRecords())
        {
            PcFeeBizBo bo=ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
            pcFeeBizBos.add(bo);
        }
        pageBizbo.setRows(pcFeeBizBos);
        return pageBizbo;
    }

    @Override
    public long createPcFee(PcFeeBizBo bo) {
        PcFeePo po=ConventObjectUtil.conventObject(bo,PcFeePo.class);
        long id=iPcFeeDao.insert(po);
        if(id>0)
            setPcFeeToRedis();
        return  id;
    }

    @Override
    public long editPcFee(PcFeeBizBo bo) {
        PcFeePo po=ConventObjectUtil.conventObject(bo,PcFeePo.class);
        int count=iPcFeeDao.updateById(po);
        if(count>0)
            setPcFeeToRedis();
        return  count;
    }

    @Override
    public List<PcFeeBizBo> getAllPcFee() {
        List<PcFeePo> pageData=iPcFeeDao.selectAllPcFeeData();
        List<PcFeeBizBo> pcFeeBizBos=new ArrayList<>();
        for(PcFeePo po:pageData)
        {
            PcFeeBizBo bo=ConventObjectUtil.conventObject(po,PcFeeBizBo.class);
            pcFeeBizBos.add(bo);
        }
        return pcFeeBizBos;

    }

    @Override
    public UserPcFee getUserPcFee(long id) {
       ExpUserPo user= iExpUserDao.selectPoById(id);
        DateTime endDateTime=new DateTime(System.currentTimeMillis());
//            long endTime=endDateTime.millisOfDay().withMinimumValue().getMillis();
        long statTime=endDateTime.plusDays(-30).millisOfDay().withMinimumValue().getMillis();
            BigDecimal sumVolume= iPcTradeVolumeDao.selectSumVolume(id,statTime);
        UserPcFee userPcFee=new UserPcFee();
        userPcFee.setVolume(sumVolume);
        userPcFee.setMakerFee(user.getMakerFee());
        userPcFee.setTakerFee(user.getTakerFee());
        String minTime=DateTime.now().toString("yyyy-MM-dd "+"00:00:00");
        long nowDay=DateTime.parse(minTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis();
        List<AssetPo> assetPos= iAssetDao.selectList();
        BigDecimal sumWithdrawVolume=new BigDecimal("0");
        for(AssetPo assetPo:assetPos)
        {
           List<FundWithdrawAssetMongoPo> fundWithdrawList=  iFundWithdrawSymbolDao.selectFundWithdrawPoByTime(id,assetPo.getRealName(),nowDay);
            for(FundWithdrawAssetMongoPo fundWithdrawPo:fundWithdrawList)
            {
                BigDecimal volume=new BigDecimal("0");
                if(assetPo.getRealName().equals("BTC"))
                {
                    volume=fundWithdrawPo.getVolume();
                }
                else {
                    volume=  bizAccountComponent.calcAssetValuationBtc("BTC",
                            assetPo.getRealName(), fundWithdrawPo.getVolume());
                }
                sumWithdrawVolume.add(volume);
            }
        }
        userPcFee.setWithdrawLimit(sumWithdrawVolume);

        return userPcFee;
    }

    private void setPcFeeToRedis()
    {
        List<PcFeePo> pcFeePos=iPcFeeDao.selectAllPcFeeData();
        redisUtilDb0.hset("pc_fee","pc", JsonUtil.toJsonString(pcFeePos));
    }
}
