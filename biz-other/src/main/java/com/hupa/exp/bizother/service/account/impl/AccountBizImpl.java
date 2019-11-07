package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.account.def.fund.FundAccount4ServerDef;
import com.hupa.exp.account.service.def.fund.FundAccountService;
import com.hupa.exp.account.util.token.fund.FundAccount4ServerTokenUtil;
import com.hupa.exp.base.dic.expv2.AccountTypeDic;
import com.hupa.exp.base.entity.bo.FundAccountBo;
import com.hupa.exp.base.entity.bo.pc.PcAccountBo;
import com.hupa.exp.base.exception.account.AccountTransferException;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.pc.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAccountAssetMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAccountAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundAccountAssetMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcAccountAssetMongoPo;
import com.hupa.exp.pc.margin.def.account.PcAccount4ServerDef;
import com.hupa.exp.pc.margin.util.token.PcAccount4ServerTokenUtil;
import com.hupa.exp.pc.service.def.PcAccountService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AccountBizImpl implements IAccountBiz {
    @Autowired
    private FundAccountService fundAccountDef;


    @Autowired
    private PcAccountService pcAccountDef;

//    @Autowired
//    private PcAmtService amtDef;



    @Reference
    private FundAccount4ServerDef fundAccount4ServerDef;

    @Reference
    private PcAccount4ServerDef pcAccount4ServerDef;


    @Autowired
    private IFundAccountAssetMongoDao iFundAccountAssetMongoDao;

    @Autowired
    private IPcAccountAssetMongoDao iPcAccountAssetMongoDao;

    @Override
    public boolean existAccount(long userId, String asset, int accountType) {

//        String redisKey = getAccountKey(userId,symbol,accountType);
//        if(StringUtils.isEmpty(redisKey))
//            return false;
//
//        String val;
//        //判断资金账户
//        if(accountType== AccountTypeDic.ACCOUNT_TYPE_FUND){
//
//            val = RedisUtil.redisClientFactory(fundAccountRedisConfig).get(redisKey);
//
//        }
//        //判断pc
//        else  if (accountType== AccountTypeDic.ACCOUNT_TYPE_PC){
//
//            val = RedisUtil.redisClientFactory(pcAccountRedisConfig).get(redisKey);
//        }else{
//
//            val =StringUtils.EMPTY;
//        }
//
//
//        if(StringUtils.isEmpty(val))
//            return false;
//        else
//            return true;


        if(accountType== AccountTypeDic.ACCOUNT_TYPE_FUND){

            FundAccountAssetMongoPo fundAccount =iFundAccountAssetMongoDao.selectPoById(userId, asset);

            if(fundAccount!=null)
                return true;
            else
                return false;

        }
        //判断pc
        else  if (accountType== AccountTypeDic.ACCOUNT_TYPE_PC){

            PcAccountAssetMongoPo pcAccountBo = iPcAccountAssetMongoDao.selectPoById(userId, asset);




            if(pcAccountBo !=null)
                return true;
            else
                return false;


        }else{

            return false;
        }
    }

    @Override
    public void createPcAccount(long userId, String asset) throws PcAccountException {
        boolean bl = existAccount(userId, asset, AccountTypeDic.ACCOUNT_TYPE_PC);
        if(bl)
            return;

        pcAccount4ServerDef.createPcAccount(String.valueOf(userId), userId, asset,
                PcAccount4ServerTokenUtil.genToken4CreatePcAccount(String.valueOf(userId), userId, asset));
    }

    @Override
    public void createFundAccount(long userId, String asset) throws FundAccountException {

        boolean bl = existAccount(userId, asset, AccountTypeDic.ACCOUNT_TYPE_FUND);
        if(bl)
            return;

        fundAccount4ServerDef.createFundAccount(String.valueOf(userId), userId, asset,
                FundAccount4ServerTokenUtil.genToken4CreateFundAccount(String.valueOf(userId),userId, asset));
    }


    @Override
    public PcAccountBizBo getPcAccount(long userId,  String asset) {

//
//        String accountKey = getAccountKey(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_PC);
//        String val = RedisUtil.redisClientFactory(pcAccountRedisConfig).get(accountKey);
//
//        if(StringUtils.isEmpty(val))
//            return null;


        PcAccountAssetMongoPo pcAccountBo = iPcAccountAssetMongoDao.selectPoById(userId, asset);


        BigDecimal total;
        if(pcAccountBo == null||pcAccountBo.getTotal()==null)
            total = BigDecimal.ZERO;
        else
            total = pcAccountBo.getTotal();

        BigDecimal available;
        if(pcAccountBo == null||pcAccountBo.getAvailable()==null)
            available=BigDecimal.ZERO;
        else
            available = pcAccountBo.getAvailable();


        BigDecimal orderMargin;
        if(pcAccountBo == null||pcAccountBo.getOrderMargin()==null)
            orderMargin = BigDecimal.ZERO;
        else
            orderMargin = pcAccountBo.getOrderMargin();


        BigDecimal posMargin;
        if(pcAccountBo == null||pcAccountBo.getPosMargin()==null)
            posMargin = BigDecimal.ZERO;
        else
            posMargin = pcAccountBo.getPosMargin();


        PcAccountBizBo bizBo = new PcAccountBizBo();
        bizBo.setSymbol(asset);
        bizBo.setTotal(total);
        bizBo.setAvailable(available);
        bizBo.setOrderMargin(orderMargin);
        bizBo.setPosMargin(posMargin);


        return bizBo;
    }

    @Override
    public FundAccountBizBo getFundAccount(long userId, String asset) {

//        String accountKey = getAccountKey(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_FUND);
//        String val = RedisUtil.redisClientFactory(fundAccountRedisConfig).get(accountKey);
//
//
//        if(StringUtils.isEmpty(val)) {
//            return null;
//        }

        FundAccountAssetMongoPo fundAccount = iFundAccountAssetMongoDao.selectPoById(userId, asset);


        BigDecimal total;
        if(fundAccount==null||fundAccount.getTotal()==null)
            total = BigDecimal.ZERO;
        else
            total = fundAccount.getTotal();

        BigDecimal available;
        if(fundAccount==null||fundAccount.getAvailable()==null)
            available = BigDecimal.ZERO;
        else
            available = fundAccount.getAvailable();

        BigDecimal lock;
        if(fundAccount==null||fundAccount.getLock()==null)
            lock = BigDecimal.ZERO;
        else
            lock = fundAccount.getLock();

        FundAccountBizBo bizBo = new FundAccountBizBo();
        bizBo.setAsset(asset);
        bizBo.setTotal(total);
        bizBo.setAvailable(available);
        bizBo.setLock(lock);

//        JSONObject accountJson = JSON.parseObject(val);
//        FundAccountBizBo bizBo = new FundAccountBizBo();
//
//
//        bizBo.setSymbol(symbol);
//        bizBo.setTotal(Optional.ofNullable(accountJson.getBigDecimal("total")).orElse(BigDecimal.ZERO));
//        bizBo.setAvailable(Optional.ofNullable(accountJson.getBigDecimal("available")).orElse(BigDecimal.ZERO));
//        bizBo.setLock(Optional.ofNullable(accountJson.getBigDecimal("lock")).orElse(BigDecimal.ZERO));

        return bizBo;
    }


//    private String getAccountKey(long userId,String symbol, int accountType){
//
//        String redisKey;
//
//        if(accountType==AccountTypeDic.ACCOUNT_TYPE_FUND){
//
//            redisKey = accountBizSettingConfig.getFundAccountRedisKey();
//            redisKey = MessageFormat.format(redisKey,symbol,String.valueOf(userId));
//            return redisKey;
//        }else  if(accountType==AccountTypeDic.ACCOUNT_TYPE_PC){
//            redisKey = accountBizSettingConfig.getPcAccountRedisKey();
//            redisKey = MessageFormat.format(redisKey,symbol,String.valueOf(userId));
//            return redisKey;
//        }else{
//
//            return StringUtils.EMPTY;
//        }
//    }
}
