package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetChangBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.servermng.entity.assetchange.*;
import com.hupa.exp.servermng.service.def.IApiAssetChangeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApiAssetChangeControllerServiceImpl implements IApiAssetChangeControllerService {
    @Autowired
    private IAssetChangBiz iAssetChangBiz;
    @Override
    public FundAssetChangeOutputDto getFundAssetChange(FundAssetChangeInputDto inputDto) {
        FundAssetChangeMongoBizBo bo= iAssetChangBiz.queryFundAssetChangePoById(
                inputDto.getId(), inputDto.getSymbol());
        FundAssetChangeOutputDto outputDto=new FundAssetChangeOutputDto();
        if(bo!=null)
        {
           outputDto.setId(String.valueOf(bo.getId()));
           outputDto.setAccountId(String.valueOf(bo.getAccountId()));
           outputDto.setAsset(String.valueOf(bo.getAsset()));
           outputDto.setTradeVolume(DecimalUtil.toTrimLiteral(bo.getChangeVolume()));
           outputDto.setChangeType(String.valueOf(bo.getChangeType()));
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
           outputDto.setChangeTime(String.valueOf(bo.getChangeTime()));
           outputDto.setCtime(String.valueOf(bo.getCtime()));
           outputDto.setMtime(String.valueOf(bo.getMtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public FundAssetChangeListOutputDto getFundAssetChangeList(FundAssetChangeListInputDto inputDto) {
        FundAssetChangeMongoPageBizBo pageBizBo= iAssetChangBiz.queryFundAssetPageData(
                inputDto.getSymbol(),inputDto.getId(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<FundAssetChangeOutputDto> list=new ArrayList<>();
        for(FundAssetChangeMongoBizBo bo:pageBizBo.getRows())
        {
            FundAssetChangeOutputDto out=new FundAssetChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setAccountId(String.valueOf(bo.getAccountId()));
            out.setAsset(String.valueOf(bo.getAsset()));
            out.setTradeVolume(DecimalUtil.toTrimLiteral(bo.getChangeVolume()));
            out.setChangeType(String.valueOf(bo.getChangeType()));
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
            out.setChangeTime(String.valueOf(bo.getChangeTime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            out.setMtime(String.valueOf(bo.getMtime()));
            list.add(out);
        }
        FundAssetChangeListOutputDto outputDto=new FundAssetChangeListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return  outputDto;
    }

    @Override
    public PcAssetChangeOutputDto getPcAssetChange(PcAssetChangeInputDto inputDto) throws BizException {
        PcAssetChangeMongoBizBo bo= iAssetChangBiz.selectPcAssetChangePoById(
                inputDto.getId(), inputDto.getSymbol());
        PcAssetChangeOutputDto outputDto=new PcAssetChangeOutputDto();
        if(bo!=null)
        {
            outputDto.setId(String.valueOf(bo.getId()));
            outputDto.setAccountId(String.valueOf(bo.getAccountId()));
            outputDto.setAsset(String.valueOf(bo.getAsset()));
            outputDto.setSymbol(String.valueOf(bo.getSymbol()));
            outputDto.setBidFlag(String.valueOf(bo.getBidFlag()));
            outputDto.setCloseFlag(String.valueOf(bo.getCloseFlag()));
            outputDto.setTradePrice(DecimalUtil.toTrimLiteral(bo.getTradePrice()));
            outputDto.setTradeAmt(DecimalUtil.toTrimLiteral(bo.getTradeAmt()));
            outputDto.setChangeType(String.valueOf(bo.getChangeType()));
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
            outputDto.setSymbolOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMarginPre()));
            outputDto.setSymbolOrderMargin(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMargin()));
            outputDto.setSymbolPosMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolPosMarginPre()));
            outputDto.setSymbolPosMargin(DecimalUtil.toTrimLiteral(bo.getSymbolPosMargin()));
            outputDto.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            outputDto.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
            outputDto.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
            outputDto.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
            outputDto.setRemark(String.valueOf(bo.getRemark()));
            outputDto.setChangeTime(String.valueOf(bo.getChangeTime()));
            outputDto.setCtime(String.valueOf(bo.getCtime()));
            outputDto.setCtime(String.valueOf(bo.getCtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PcAssetChangeListOutputDto getPcAssetChangeList(PcAssetChangeListInputDto inputDto) throws BizException {
        PcAssetChangeMongoPageBizBo pageBizBo= iAssetChangBiz.queryPcAssetPageData(
                inputDto.getSymbol(),inputDto.getId(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<PcAssetChangeOutputDto> list=new ArrayList<>();
        for(PcAssetChangeMongoBizBo bo:pageBizBo.getRows())
        {
            PcAssetChangeOutputDto out=new PcAssetChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setAccountId(String.valueOf(bo.getAccountId()));
            out.setAsset(String.valueOf(bo.getAsset()));
            out.setSymbol(String.valueOf(bo.getSymbol()));
            out.setBidFlag(String.valueOf(bo.getBidFlag()));
            out.setCloseFlag(String.valueOf(bo.getCloseFlag()));
            out.setTradePrice(DecimalUtil.toTrimLiteral(bo.getTradePrice()));
            out.setTradeAmt(DecimalUtil.toTrimLiteral(bo.getTradeAmt()));
            out.setChangeType(String.valueOf(bo.getChangeType()));
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
            out.setSymbolOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMarginPre()));
            out.setSymbolOrderMargin(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMargin()));
            out.setSymbolPosMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolPosMarginPre()));
            out.setSymbolPosMargin(DecimalUtil.toTrimLiteral(bo.getSymbolPosMargin()));
            out.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            out.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
            out.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
            out.setAccAvail(DecimalUtil.toTrimLiteral(bo.getAccAvail()));
            out.setRemark(String.valueOf(bo.getRemark()));
            out.setChangeTime(String.valueOf(bo.getChangeTime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            list.add(out);
        }
        PcAssetChangeListOutputDto outputDto=new PcAssetChangeListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return  outputDto;
    }
}
