package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssertChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssertChangBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.servermng.entity.assertchange.*;
import com.hupa.exp.servermng.service.def.IApiAssertChangeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApiAssertChangeControllerServiceImpl implements IApiAssertChangeControllerService {
    @Autowired
    private IAssertChangBiz iAssertChangBiz;
    @Override
    public FundAssertChangeOutputDto getFundAssertChange(FundAssertChangeInputDto inputDto) {
        FundAssertChangeMongoBizBo bo= iAssertChangBiz.queryFundAssetChangePoById(
                inputDto.getId(), inputDto.getSymbol());
        FundAssertChangeOutputDto outputDto=new FundAssertChangeOutputDto();
        if(bo!=null)
        {
           outputDto.setId(String.valueOf(bo.getId()));
           outputDto.setAccountId(String.valueOf(bo.getAccountId()));
           outputDto.setSymbol(String.valueOf(bo.getSymbol()));
           outputDto.setTradeVolume(DecimalUtil.toTrimLiteral(bo.getTradeVolume()));
           outputDto.setTradeType(String.valueOf(bo.getTradeType()));
           outputDto.setObjectId(String.valueOf(bo.getObjectId()));
           outputDto.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
           //outputDto.setOrderType(String.valueOf(bo.getOrderType()));
           outputDto.setAccLockPre(DecimalUtil.toTrimLiteral(bo.getAccLockPre()));
           outputDto.setAccLock(DecimalUtil.toTrimLiteral(bo.getAccLock()));
           outputDto.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
           outputDto.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
           outputDto.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
           outputDto.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
           outputDto.setRemark(String.valueOf(bo.getRemark()));
           outputDto.setTradeTime(String.valueOf(bo.getTradeTime()));
           outputDto.setCtime(String.valueOf(bo.getCtime()));
           outputDto.setMtime(String.valueOf(bo.getMtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public FundAssertChangeListOutputDto getFundAssertChangeList(FundAssertChangeListInputDto inputDto) {
        FundAssertChangeMongoPageBizBo pageBizBo= iAssertChangBiz.queryFundAssetPageData(
                inputDto.getSymbol(),inputDto.getId(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<FundAssertChangeOutputDto> list=new ArrayList<>();
        for(FundAssertChangeMongoBizBo bo:pageBizBo.getRows())
        {
            FundAssertChangeOutputDto out=new FundAssertChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setAccountId(String.valueOf(bo.getAccountId()));
            out.setSymbol(String.valueOf(bo.getSymbol()));
            out.setTradeVolume(DecimalUtil.toTrimLiteral(bo.getTradeVolume()));
            out.setTradeType(String.valueOf(bo.getTradeType()));
            out.setObjectId(String.valueOf(bo.getObjectId()));
            out.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
            //out.setOrderType(String.valueOf(bo.getOrderType()));
            out.setAccLockPre(DecimalUtil.toTrimLiteral(bo.getAccLockPre()));
            out.setAccLock(DecimalUtil.toTrimLiteral(bo.getAccLock()));
            out.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            out.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
            out.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
            out.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
            out.setRemark(String.valueOf(bo.getRemark()));
            out.setTradeTime(String.valueOf(bo.getTradeTime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            out.setMtime(String.valueOf(bo.getMtime()));
            list.add(out);
        }
        FundAssertChangeListOutputDto outputDto=new FundAssertChangeListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return  outputDto;
    }

    @Override
    public PcAssertChangeOutputDto getPcAssertChange(PcAssertChangeInputDto inputDto) throws BizException {
        PcAssertChangeMongoBizBo bo= iAssertChangBiz.selectPcAssertChangePoById(
                inputDto.getId(), inputDto.getSymbol());
        PcAssertChangeOutputDto outputDto=new PcAssertChangeOutputDto();
        if(bo!=null)
        {
            outputDto.setId(String.valueOf(bo.getId()));
            outputDto.setAccountId(String.valueOf(bo.getAccountId()));
            outputDto.setSymbol(String.valueOf(bo.getSymbol()));
            outputDto.setPair(String.valueOf(bo.getPair()));
            outputDto.setBidFlag(String.valueOf(bo.getBidFlag()));
            outputDto.setCloseFlag(String.valueOf(bo.getCloseFlag()));
            outputDto.setTradePrice(DecimalUtil.toTrimLiteral(bo.getTradePrice()));
            outputDto.setTradeAmt(DecimalUtil.toTrimLiteral(bo.getTradeAmt()));
            outputDto.setTradeType(String.valueOf(bo.getTradeType()));
            outputDto.setObjectId(String.valueOf(bo.getObjectId()));
            outputDto.setRatio(DecimalUtil.toTrimLiteral(bo.getRatio()));
            outputDto.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
            outputDto.setOrderType(String.valueOf(bo.getOrderType()));
            outputDto.setOrderPrice(DecimalUtil.toTrimLiteral(bo.getOrderPrice()));
            outputDto.setOrderAmt(DecimalUtil.toTrimLiteral(bo.getOrderAmt()));
            outputDto.setUnfilledAmt(DecimalUtil.toTrimLiteral(bo.getUnfilledAmt()));
            outputDto.setPosId(String.valueOf(bo.getPosId()));
            outputDto.setOrderId(String.valueOf(bo.getOrderId()));
            outputDto.setPnl(DecimalUtil.toTrimLiteral(bo.getPnl()));
            outputDto.setOrderMarginRls(DecimalUtil.toTrimLiteral(bo.getOrderMarginRls()));
            outputDto.setOrderMargin(DecimalUtil.toTrimLiteral(bo.getOrderMargin()));
            outputDto.setPosMarginRls(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
            outputDto.setPosMargin(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
            outputDto.setAccOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getAccOrderMarginPre()));
            outputDto.setAccOrderMargin(DecimalUtil.toTrimLiteral(bo.getAccOrderMargin()));
            outputDto.setAccPosMarginPre(DecimalUtil.toTrimLiteral(bo.getAccPosMarginPre()));
            outputDto.setAccPosMargin(DecimalUtil.toTrimLiteral(bo.getAccPosMargin()));
            outputDto.setPairOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getPairOrderMarginPre()));
            outputDto.setPairOrderMargin(DecimalUtil.toTrimLiteral(bo.getPairOrderMargin()));
            outputDto.setPairPosMarginPre(DecimalUtil.toTrimLiteral(bo.getPairPosMarginPre()));
            outputDto.setPairPosMargin(DecimalUtil.toTrimLiteral(bo.getPairPosMargin()));
            outputDto.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            outputDto.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
            outputDto.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
            outputDto.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
            outputDto.setRemark(String.valueOf(bo.getRemark()));
            outputDto.setTradeTime(String.valueOf(bo.getTradeTime()));
            outputDto.setCtime(String.valueOf(bo.getCtime()));
            outputDto.setCtime(String.valueOf(bo.getCtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PcAssertChangeListOutputDto getPcAssertChangeList(PcAssertChangeListInputDto inputDto) throws BizException {
        PcAssertChangeMongoPageBizBo pageBizBo= iAssertChangBiz.queryPcAssertPageData(
                inputDto.getSymbol(),inputDto.getId(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<PcAssertChangeOutputDto> list=new ArrayList<>();
        for(PcAssertChangeMongoBizBo bo:pageBizBo.getRows())
        {
            PcAssertChangeOutputDto out=new PcAssertChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setAccountId(String.valueOf(bo.getAccountId()));
            out.setSymbol(String.valueOf(bo.getSymbol()));
            out.setPair(String.valueOf(bo.getPair()));
            out.setBidFlag(String.valueOf(bo.getBidFlag()));
            out.setCloseFlag(String.valueOf(bo.getCloseFlag()));
            out.setTradePrice(DecimalUtil.toTrimLiteral(bo.getTradePrice()));
            out.setTradeAmt(DecimalUtil.toTrimLiteral(bo.getTradeAmt()));
            out.setTradeType(String.valueOf(bo.getTradeType()));
            out.setObjectId(String.valueOf(bo.getObjectId()));
            out.setRatio(DecimalUtil.toTrimLiteral(bo.getRatio()));
            out.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
            out.setOrderType(String.valueOf(bo.getOrderType()));
            out.setOrderPrice(DecimalUtil.toTrimLiteral(bo.getOrderPrice()));
            out.setOrderAmt(DecimalUtil.toTrimLiteral(bo.getOrderAmt()));
            out.setUnfilledAmt(DecimalUtil.toTrimLiteral(bo.getUnfilledAmt()));
            out.setPosId(String.valueOf(bo.getPosId()));
            out.setOrderId(String.valueOf(bo.getOrderId()));
            out.setPnl(DecimalUtil.toTrimLiteral(bo.getPnl()));
            out.setOrderMarginRls(DecimalUtil.toTrimLiteral(bo.getOrderMarginRls()));
            out.setOrderMargin(DecimalUtil.toTrimLiteral(bo.getOrderMargin()));
            out.setPosMarginRls(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
            out.setPosMargin(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
            out.setAccOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getAccOrderMarginPre()));
            out.setAccOrderMargin(DecimalUtil.toTrimLiteral(bo.getAccOrderMargin()));
            out.setAccPosMarginPre(DecimalUtil.toTrimLiteral(bo.getAccPosMarginPre()));
            out.setAccPosMargin(DecimalUtil.toTrimLiteral(bo.getAccPosMargin()));
            out.setPairOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getPairOrderMarginPre()));
            out.setPairOrderMargin(DecimalUtil.toTrimLiteral(bo.getPairOrderMargin()));
            out.setPairPosMarginPre(DecimalUtil.toTrimLiteral(bo.getPairPosMarginPre()));
            out.setPairPosMargin(DecimalUtil.toTrimLiteral(bo.getPairPosMargin()));
            out.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            out.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
            out.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
            out.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
            out.setRemark(String.valueOf(bo.getRemark()));
            out.setTradeTime(String.valueOf(bo.getTradeTime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            list.add(out);
        }
        PcAssertChangeListOutputDto outputDto=new PcAssertChangeListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return  outputDto;
    }
}
