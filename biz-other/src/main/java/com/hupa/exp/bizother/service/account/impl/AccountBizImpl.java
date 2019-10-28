package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.account.def.Account4ServerDef;
import com.hupa.exp.account.def.fund.FundAccount4ServerDef;
import com.hupa.exp.account.def.pc.PcAccount4ServerDef;
import com.hupa.exp.base.dic.expv2.AccountTypeDic;
import com.hupa.exp.base.entity.bo.FundAccountBo;
import com.hupa.exp.base.entity.bo.pc.PcAccountBo;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.exception.account.PcAccountException;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import com.hupa.exp.persist.def.fund.FundAccountReadService;
import com.hupa.exp.persist.def.pc.PcAccountReadService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class AccountBizImpl implements IAccountBiz {

    @Autowired
    private FundAccountReadService fundAccountReadService;
    @Autowired
    private PcAccountReadService pcAccountReadService;

    @Reference
    private Account4ServerDef account4ServerDef;

    @Reference
    private FundAccount4ServerDef fundAccount4ServerDef;

    @Reference
    private PcAccount4ServerDef pcAccount4ServerDef;

    @Override
    public boolean existAccount(long userId, String symbol, int accountType) {


        if (accountType == AccountTypeDic.ACCOUNT_TYPE_FUND) {

            FundAccountBo fundAccount = null;
//            try {
//                fundAccount = fundAccountReadService.getFundAccount(userId, symbol, true);
//            } catch (FundAccountException e) {
//
//            }

            if (fundAccount != null)
                return true;
            else
                return false;

        }
        //判断pc
        else if (accountType == AccountTypeDic.ACCOUNT_TYPE_PC) {

            PcAccountBo pcAccountBo = null;
//            try {
//                pcAccountBo = pcAccountDef.getPcAccount(userId, symbol, true, false, false);
//            } catch (PcAccountException e) {
//
//            }

            if (pcAccountBo != null)
                return true;
            else
                return false;


        } else {

            return false;
        }
    }


    @Override
    public PcAccountBizBo getPcAccount(long userId, String symbol) {

        PcAccountBo pcAccountBo = null;
//        try {
//
//            pcAccountBo = pcAccountDef.getPcAccount(userId, symbol, true, false, false);
//        } catch (PcAccountException e) {
//
//        }


        BigDecimal total;
        if (pcAccountBo == null || pcAccountBo.getTotal() == null)
            total = BigDecimal.ZERO;
        else
            total = pcAccountBo.getTotal();

        BigDecimal available;
        if (pcAccountBo == null || pcAccountBo.getAvailable() == null)
            available = BigDecimal.ZERO;
        else
            available = pcAccountBo.getAvailable();


        BigDecimal orderMargin;
        if (pcAccountBo == null || pcAccountBo.getOrderMargin() == null)
            orderMargin = BigDecimal.ZERO;
        else
            orderMargin = pcAccountBo.getOrderMargin();


        BigDecimal posMargin;
        if (pcAccountBo == null || pcAccountBo.getPosMargin() == null)
            posMargin = BigDecimal.ZERO;
        else
            posMargin = pcAccountBo.getPosMargin();


        PcAccountBizBo bizBo = new PcAccountBizBo();
        bizBo.setSymbol(symbol);
        bizBo.setTotal(total);
        bizBo.setAvailable(available);
        bizBo.setOrderMargin(orderMargin);
        bizBo.setPosMargin(posMargin);

        return bizBo;
    }

    @Override
    public FundAccountBizBo getFundAccount(long userId, String symbol) {

        FundAccountBo fundAccount = null;
//        try {
//            fundAccount = fundAccountDef.getFundAccount(userId, symbol, true);
//
//        } catch (FundAccountException e) {
//
//        }

        BigDecimal total;
        if (fundAccount == null || fundAccount.getTotal() == null)
            total = BigDecimal.ZERO;
        else
            total = fundAccount.getTotal();

        BigDecimal available;
        if (fundAccount == null || fundAccount.getAvailable() == null)
            available = BigDecimal.ZERO;
        else
            available = fundAccount.getAvailable();

        BigDecimal lock;
        if (fundAccount == null || fundAccount.getLock() == null)
            lock = BigDecimal.ZERO;
        else
            lock = fundAccount.getLock();

        FundAccountBizBo bizBo = new FundAccountBizBo();
        bizBo.setSymbol(symbol);
        bizBo.setTotal(total);
        bizBo.setAvailable(available);
        bizBo.setLock(lock);

        return bizBo;
    }

}
