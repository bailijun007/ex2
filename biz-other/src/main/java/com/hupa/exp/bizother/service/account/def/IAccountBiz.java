package com.hupa.exp.bizother.service.account.def;

import com.hp.sh.expv3.fund.wallet.vo.request.FundAddRequest;
import com.hp.sh.expv3.fund.wallet.vo.request.FundCutRequest;
import com.hp.sh.expv3.pc.vo.request.PcAddRequest;
import com.hp.sh.expv3.pc.vo.request.PcCutRequest;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.pc.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAccountBiz {

    //boolean existAccount(long userId, String symbol, int accountType);

    /**
     * 获取资产账户
     * @param userId
     * @param symbol
     * @return
     */
    FundAccountBizBo getFundAccount(long userId, String symbol);

    /**
     *  获取合约账户
     * @param userId
     * @param symbol
     * @return
     */
    PcAccountBizBo getPcAccount(long userId, String symbol);

    /**
     * 创建合约账户
     * @param userId
     * @param asset
     * @throws PcAccountException
     * @throws PcAccountException
     */
    void createPcAccount(long userId, String asset) throws PcAccountException, PcAccountException;

    /**
     * 创建资金账户
     * @param userId
     * @param asset
     * @throws FundAccountException
     */
    void createFundAccount(long userId, String asset) throws FundAccountException;

    /**
     * 给资金账户加钱
     */
    void addFundAccount(FundAddRequest fundAddRequest, long userId, String asset);

    /**
     * 给资金账户扣钱
     */
    void cutFundAccount(FundCutRequest fundCutRequest, long userId, String asset);

    /**
     * 合约账户加钱
     * @param pcAddRequest
     * @return
     */
    Integer addPcAccount(PcAddRequest pcAddRequest,long userId, String asset);

    /**
     * 合约账户扣钱
     * @param pcCutRequest
     * @return
     */
    Integer cutPcAccount(PcCutRequest pcCutRequest,long userId, String asset);


}
