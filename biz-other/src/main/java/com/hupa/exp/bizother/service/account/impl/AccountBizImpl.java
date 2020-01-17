package com.hupa.exp.bizother.service.account.impl;

import com.hp.sh.expv3.fund.extension.api.FundAccountExtApi;
import com.hp.sh.expv3.fund.extension.vo.CapitalAccountVo;
import com.hp.sh.expv3.fund.wallet.api.FundAccountCoreApi;
import com.hp.sh.expv3.fund.wallet.vo.request.FundAddRequest;
import com.hp.sh.expv3.fund.wallet.vo.request.FundCutRequest;
import com.hp.sh.expv3.pc.api.PcAccountCoreApi;
import com.hp.sh.expv3.pc.extension.api.PcAccountExtendApi;
import com.hp.sh.expv3.pc.extension.vo.PcAccountExtVo;
import com.hp.sh.expv3.pc.vo.request.PcAddRequest;
import com.hp.sh.expv3.pc.vo.request.PcCutRequest;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.pc.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountBizImpl implements IAccountBiz {

    private static Logger logger = LoggerFactory.getLogger(AccountBizImpl.class);

    //资金账户接口
    @Autowired
    private FundAccountCoreApi fundAccountCoreApi;

    //合约账户接口
    @Autowired
    private PcAccountCoreApi pcAccountCoreApi;

    //资金账户接口扩展
    @Autowired
    private FundAccountExtApi fundAccountExtApi;

    //合约账户接口扩展
    @Autowired
    private PcAccountExtendApi pcAccountExtendApi;

    /**
     *  创建资金账户
     * @param userId
     * @param asset
     * @throws FundAccountException
     */
    @Override
    public void createFundAccount(long userId, String asset) throws FundAccountException {
        try{
            //判断资金账户是否已经创建，true：已创建，否则创建
            Boolean bn = fundAccountCoreApi.accountExist(userId, asset);
            int num = 0;
            if(!bn){
                num = fundAccountCoreApi.createAccount(userId,asset);
            }
            logger.info("admin AccountBizImpl createFundAccount userId:"+ userId + ",asset:"+ asset + ",createAccount-Start:" + num +",accountExist:"+ bn);
        }catch(Exception e){
            logger.info("admin AccountBizImpl createFundAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
    }

    /**
     *  获取资金账户
     * @param userId
     * @param asset
     * @return
     */
    @Override
    public FundAccountBizBo getFundAccount(long userId, String asset) {
        FundAccountBizBo fundAccountBizBo = null;
        CapitalAccountVo capitalAccountVo = fundAccountExtApi.getCapitalAccount(userId, asset);
        if(capitalAccountVo!=null){
            fundAccountBizBo = new FundAccountBizBo();
            fundAccountBizBo.setAccountId(capitalAccountVo.getAccountId());
            fundAccountBizBo.setAsset(capitalAccountVo.getAsset());
            fundAccountBizBo.setAvailable(capitalAccountVo.getAvailable());
            fundAccountBizBo.setLock(capitalAccountVo.getLock());
            fundAccountBizBo.setTotal(capitalAccountVo.getTotalAssets());
        }
        return fundAccountBizBo;
    }

    /**
     * 给资金账户加钱
     */
    @Override
    public void addFundAccount(FundAddRequest fundAddRequest, long userId, String asset) {
        try {
            //判断账户是否存在
            Boolean bn = fundAccountCoreApi.accountExist(userId, asset);
            if (bn)
                fundAccountCoreApi.add(fundAddRequest);
        }catch(Exception e){
            logger.info("admin AccountBizImpl addFundAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
    }

    /**
     * 给资金账户扣钱
     */
    @Override
    public void cutFundAccount(FundCutRequest fundCutRequest, long userId, String asset) {
        try {
            //判断账户是否存在
            Boolean bn = fundAccountCoreApi.accountExist(userId, asset);
            if(bn){
                BigDecimal balanceFundAccount = fundAccountCoreApi.getBalance(userId, asset);
                if(balanceFundAccount!=null){
                    fundAccountCoreApi.cut(fundCutRequest);
                }
            }
        }catch(Exception e){
            logger.info("admin AccountBizImpl cutFundAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
    }

    /**
     * 创建合约账户
     * @param userId
     * @param asset
     * @throws PcAccountException
     */
    @Override
    public void createPcAccount(long userId, String asset) throws PcAccountException {
        try{
            //判断合约账户是否已经创建，true：已创建，否则创建
            Boolean bn = pcAccountCoreApi.accountExist(userId, asset);
            int num = 0;
            if(!bn){
               num = pcAccountCoreApi.createAccount(userId,asset);
            }
            logger.info("AccountBizImpl createPcAccount userId:"+ userId + ",asset:"+ asset + ",createAccount-Start:" + num +",accountExist:"+ bn);
        }catch(Exception e){
            logger.info("AccountBizImpl createPcAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
    }

    /**
     * 获取合约账户
     * @param userId
     * @param asset
     * @return
     */
    @Override
    public PcAccountBizBo getPcAccount(long userId, String asset){
        PcAccountBizBo pcAccountBizBo = null;
        try{
            List<PcAccountExtVo> pcAccountExtVoList = pcAccountExtendApi.findContractAccount(String.valueOf(userId),asset);
            if(pcAccountExtVoList!=null &&  pcAccountExtVoList.size()>0){
                PcAccountExtVo pcAccountExtVo  = pcAccountExtVoList.get(0);
                if(pcAccountExtVo!=null){
                    pcAccountBizBo = new PcAccountBizBo();
                    pcAccountBizBo.setAccountId(pcAccountExtVo.getAccountId());
                    pcAccountBizBo.setAsset(pcAccountExtVo.getAsset());
                    pcAccountBizBo.setAvailable(pcAccountExtVo.getAvailable());
                    pcAccountBizBo.setOrderMargin(pcAccountExtVo.getOrderMargin());//委托保证金
                    pcAccountBizBo.setPosMargin(pcAccountBizBo.getPosMargin());//仓位保证金
                    pcAccountBizBo.setTotal(pcAccountBizBo.getTotal());
                }
            }
        }catch(Exception e){
            logger.info("admin AccountBizImpl getPcAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
        return pcAccountBizBo;
    }

    /**
     * 合约账户加钱
     * @param pcAddRequest
     * @param userId
     * @param asset
     * @return
     */
    @Override
    public Integer addPcAccount(PcAddRequest pcAddRequest,long userId, String asset) {
        try{
            boolean bn = pcAccountCoreApi.accountExist(userId,asset);
            if(bn)
                return pcAccountCoreApi.add(pcAddRequest);
        }catch(Exception e){
            logger.info("admin AccountBizImpl addPcAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * 合约账户扣钱
     * @param pcCutRequest
     * @param userId
     * @param asset
     * @return
     */
    @Override
    public Integer cutPcAccount(PcCutRequest pcCutRequest,long userId, String asset) {
        try{
            boolean bn = pcAccountCoreApi.accountExist(userId,asset);
            if(bn){
                BigDecimal balancePcAccount = pcAccountCoreApi.getBalance(userId,asset);
                if(balancePcAccount!=null){
                    return  pcAccountCoreApi.cut(pcCutRequest);
                }
            }
        }catch(Exception e){
            logger.info("admin AccountBizImpl cutPcAccount userId:"+ userId + ",asset:"+ asset + ",exception: " + e.getMessage());
        }
        return null;
    }
}
