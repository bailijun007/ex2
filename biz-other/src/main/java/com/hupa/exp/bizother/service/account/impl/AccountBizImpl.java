package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.account.def.fund.FundAccount4ServerDef;
import com.hupa.exp.account.service.account.def.FundAccountDef;
import com.hupa.exp.account.util.token.fund.FundAccount4ServerTokenUtil;
import com.hupa.exp.account.util.token.pc.PcAccount4ServerTokenUtil;
import com.hupa.exp.base.dic.expv2.AccountTypeDic;
import com.hupa.exp.base.entity.bo.FundAccountBo;
import com.hupa.exp.base.entity.bo.pc.PcAccountBo;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.account.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
import com.hupa.exp.pccore.def.account.PcAccount4ServerDef;
import com.hupa.exp.pccore.service.pcaccount.def.PcAccountDef;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AccountBizImpl implements IAccountBiz {


//    @Autowired
//    private AccountBizSettingConfig accountBizSettingConfig;
//
//    @Autowired
//    private FundAccountRedisConfig fundAccountRedisConfig;
//
//    @Autowired
//    private PcAccountRedisConfig pcAccountRedisConfig;


    @Autowired
    private FundAccountDef fundAccountDef;


    @Autowired
    private PcAccountDef pcAccountDef;


    @Autowired
    private ILastPriceBiz iLastPriceBiz;

    @Reference
    private FundAccount4ServerDef fundAccount4ServerDef;

    @Reference
    private PcAccount4ServerDef pcAccount4ServerDef;

    @Override
    public boolean existAccount(long userId, String symbol,  int accountType) {

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

            FundAccountBo fundAccount = null;
            try {
                 fundAccount = fundAccountDef.getFundAccount(userId, symbol, true);
            } catch (FundAccountException e) {

            }

            if(fundAccount!=null)
                return true;
            else
                return false;

        }
        //判断pc
        else  if (accountType== AccountTypeDic.ACCOUNT_TYPE_PC){

            PcAccountBo pcAccountBo = null;
            try {
                 pcAccountBo = pcAccountDef.getPcAccount(userId, symbol, true, false, false);
            } catch (PcAccountException e) {

            }

            if(pcAccountBo !=null)
                return true;
            else
                return false;


        }else{

            return false;
        }
    }

    @Override
    public void createPcAccount(long userId, String symbol) throws PcAccountException {
        boolean bl = existAccount(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_PC);
        if(bl)
            return;

        pcAccount4ServerDef.createPcAccount(userId,symbol,
                PcAccount4ServerTokenUtil.genToken4CreatePcAccount(userId,symbol));
    }

    @Override
    public void createFundAccount(long userId, String symbol) throws FundAccountException {

        boolean bl = existAccount(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_FUND);
        if(bl)
            return;

        fundAccount4ServerDef.createFundAccount(userId,symbol,
                FundAccount4ServerTokenUtil.genToken4CreateFundAccount(userId, symbol));
    }


    @Override
    public PcAccountBizBo getPcAccount(long userId, String symbol) {

//
//        String accountKey = getAccountKey(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_PC);
//        String val = RedisUtil.redisClientFactory(pcAccountRedisConfig).get(accountKey);
//
//        if(StringUtils.isEmpty(val))
//            return null;

        PcAccountBo pcAccountBo =null;
        try {

            pcAccountBo = pcAccountDef.getPcAccount(userId, symbol, true, false, false);
        } catch (PcAccountException e) {

        }


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
        bizBo.setSymbol(symbol);
        bizBo.setTotal(total);
        bizBo.setAvailable(available);
        bizBo.setOrderMargin(orderMargin);
        bizBo.setPosMargin(posMargin);


//        bizBo.setTotal(accountJson.getBigDecimal("total"));
//        bizBo.setAvailable(accountJson.getBigDecimal("available"));
//        bizBo.setOrderMargin(accountJson.getBigDecimal("orderMargin"));
//        bizBo.setPosMargin(accountJson.getBigDecimal("posMargin"));

        return bizBo;
    }

    @Override
    public FundAccountBizBo getFundAccount(long userId, String symbol) {

//        String accountKey = getAccountKey(userId, symbol, AccountTypeDic.ACCOUNT_TYPE_FUND);
//        String val = RedisUtil.redisClientFactory(fundAccountRedisConfig).get(accountKey);
//
//
//        if(StringUtils.isEmpty(val)) {
//            return null;
//        }

        FundAccountBo fundAccount=null;
        try {
            fundAccount  = fundAccountDef.getFundAccount(userId, symbol, true);

        } catch (FundAccountException e) {

        }

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
        bizBo.setSymbol(symbol);
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
