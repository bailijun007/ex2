package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAssetChangeMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetChangBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAccountAssetMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IFundAssetChangeSymbolMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAccountAssetMongoDao;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAssetChangeAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.*;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.servermng.entity.assetchange.*;
import com.hupa.exp.servermng.service.def.IApiAssetChangeControllerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiAssetChangeControllerServiceImpl implements IApiAssetChangeControllerService {
    @Autowired
    private IPcAssetChangeAssetMongoDao iPcAssetChangeAssetMongoDao;

    @Autowired
    private IFundAssetChangeSymbolMongoDao iFundAssetChangeSymbolMongoDao;
    @Autowired
    private IAssetDao iAssetDao;

    @Override
    public FundAssetChangeOutputDto getFundAssetChange(FundAssetChangeInputDto inputDto) {
        FundAssetChangeSymbolMongoPo bo= iFundAssetChangeSymbolMongoDao.selectPoById(
                inputDto.getId(), inputDto.getSymbol());
        FundAssetChangeOutputDto outputDto=new FundAssetChangeOutputDto();
        if(bo!=null)
        {
           outputDto.setId(String.valueOf(bo.getId()));
           outputDto.setAccountId(String.valueOf(bo.getAccountId()));
           outputDto.setAsset(String.valueOf(bo.getAsset()));
           outputDto.setChangeVolume(DecimalUtil.toTrimLiteral(bo.getChangeVolume()));
           outputDto.setChangeType(String.valueOf(bo.getChangeType()));
           outputDto.setObjectId(String.valueOf(bo.getObjectId()));
           outputDto.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
           outputDto.setChangeType(String.valueOf(bo.getChangeType()));
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
        List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<FundAssetChangeSymbolMongoPo> withdrawSymbolMongoPoList = new ArrayList<>();
        int counts = 0;
        List<FundAssetChangeSymbolMongoPo> newList = new ArrayList<>();

        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {

            inputDto.setId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<FundAssetChangeSymbolMongoPo> pageBizBo= iFundAssetChangeSymbolMongoDao.pagePosByParamMng(
                    assetPo.getRealName(),inputDto.getId(),
                    inputDto.getAccountId(),inputDto.getPageStatus(),
                    inputDto.getCurrentPage(),inputDto.getPageSize());
            withdrawSymbolMongoPoList.addAll(pageBizBo.getRows());
            counts += pageBizBo.getTotalCount();
        }
        newList = withdrawSymbolMongoPoList.stream().sorted(Comparator.comparing(FundAssetChangeSymbolMongoPo::getId).reversed())
                //Comparator.comparing(FundWithdrawSymbolMongoPo::getWithdrawTime
                .collect(Collectors.toList());
        if (newList.size() > 10) {
            if (inputDto.getPageStatus() == -1&&inputDto.getCurrentPage()!=1)
                newList = newList.subList(newList.size() - 10, newList.size());
            else
                newList = newList.subList(0, 10);

        } else {
            newList = newList.subList(0, newList.size());
        }

        List<FundAssetChangeOutputDto> list=new ArrayList<>();
        for(FundAssetChangeSymbolMongoPo bo:newList)
        {
            FundAssetChangeOutputDto out=new FundAssetChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setAccountId(String.valueOf(bo.getAccountId()));
            out.setAsset(String.valueOf(bo.getAsset()));
            out.setChangeVolume(DecimalUtil.toTrimLiteral(bo.getChangeVolume()));
            out.setChangeType(String.valueOf(bo.getChangeType()));
            out.setObjectId(String.valueOf(bo.getObjectId()));
            out.setFee(DecimalUtil.toTrimLiteral(bo.getFee()));
            out.setObjectType(String.valueOf(bo.getObjectType()));
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
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setRows(list);
        return  outputDto;
    }

    @Override
    public PcAssetChangeOutputDto getPcAssetChange(PcAssetChangeInputDto inputDto) throws BizException {
        PcAssetChangeAssetMongoPo bo= iPcAssetChangeAssetMongoDao.selectPoById(
                inputDto.getId(), inputDto.getAsset());
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
//            outputDto.setOrderType(String.valueOf(bo.getChangeType()));
//            outputDto.setOrderPrice(DecimalUtil.toTrimLiteral(bo.getTradePrice()));
//            outputDto.setOrderAmt(DecimalUtil.toTrimLiteral(bo.getTradeAmt()));
            outputDto.setUnfilledAmt(DecimalUtil.toTrimLiteral(bo.getUnfilledAmt()));
            outputDto.setPosId(String.valueOf(bo.getPosId()));
            outputDto.setOrderId(String.valueOf(bo.getOrderId()));
            outputDto.setPnl(DecimalUtil.toTrimLiteral(bo.getPnl()));
//            outputDto.setOrderMarginRls(DecimalUtil.toTrimLiteral(bo.getOrderMarginRls()));
//            outputDto.setOrderMargin(DecimalUtil.toTrimLiteral(bo.getOrderMargin()));
//            outputDto.setPosMarginRls(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
//            outputDto.setPosMargin(DecimalUtil.toTrimLiteral(bo.getPosMarginRls()));
//            outputDto.setAccOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getAccOrderMarginPre()));
            outputDto.setAccOrderMargin(DecimalUtil.toTrimLiteral(bo.getAccOrderMargin()));
//            outputDto.setAccPosMarginPre(DecimalUtil.toTrimLiteral(bo.getAccPosMarginPre()));
            outputDto.setAccPosMargin(DecimalUtil.toTrimLiteral(bo.getAccPosMargin()));
//            outputDto.setSymbolOrderMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMarginPre()));
            outputDto.setSymbolOrderMargin(DecimalUtil.toTrimLiteral(bo.getSymbolOrderMargin()));
//            outputDto.setSymbolPosMarginPre(DecimalUtil.toTrimLiteral(bo.getSymbolPosMarginPre()));
            outputDto.setSymbolPosMargin(DecimalUtil.toTrimLiteral(bo.getSymbolPosMargin()));
//            outputDto.setAccTotalPre(DecimalUtil.toTrimLiteral(bo.getAccTotalPre()));
            outputDto.setAccTotal(DecimalUtil.toTrimLiteral(bo.getAccTotal()));
//            outputDto.setAccAvailPre(DecimalUtil.toTrimLiteral(bo.getAccAvailPre()));
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
        List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<PcAssetChangeAssetMongoPo> withdrawSymbolMongoPoList = new ArrayList<>();
        int counts = 0;
        MongoSortEnum sort = MongoSortEnum.desc;
        List<PcAssetChangeAssetMongoPo> newList = new ArrayList<>();
        if (inputDto.getPageStatus() == -1)
            sort = MongoSortEnum.asc;
        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {
            sort = MongoSortEnum.desc;
            inputDto.setId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<PcAssetChangeAssetMongoPo> pageBizBo= iPcAssetChangeAssetMongoDao.pagePosByParamMng(
                    assetPo.getRealName(),inputDto.getId(),
                    inputDto.getAccountId(),inputDto.getPageStatus(),
                    inputDto.getCurrentPage(),inputDto.getPageSize());
            withdrawSymbolMongoPoList.addAll(pageBizBo.getRows());
            counts += pageBizBo.getTotalCount();
        }
        newList = withdrawSymbolMongoPoList.stream().sorted(Comparator.comparing(PcAssetChangeAssetMongoPo::getId).reversed())
                //Comparator.comparing(FundWithdrawSymbolMongoPo::getWithdrawTime
                .collect(Collectors.toList());
        if (newList.size() > 10) {
            if (inputDto.getPageStatus() == -1&&inputDto.getCurrentPage()!=1)
                newList = newList.subList(newList.size() - 10, newList.size());
            else
                newList = newList.subList(0, 10);

        } else {
            newList = newList.subList(0, newList.size());
        }


        List<PcAssetChangeOutputDto> list=new ArrayList<>();
        for(PcAssetChangeAssetMongoPo bo:newList)
        {
            PcAssetChangeOutputDto out=new PcAssetChangeOutputDto();
            out.setId(String.valueOf(bo.getId()));
            out.setSrcAccountId(String.valueOf(bo.getSrcAccountId()));
            out.setSrcAccountId(String.valueOf(bo.getSrcAccountId()));
            out.setAsset(String.valueOf(bo.getAsset()));
            out.setSymbol(String.valueOf(bo.getSymbol()));
            out.setBidFlag(String.valueOf(bo.getBidFlag()));
            out.setCloseFlag(String.valueOf(bo.getCloseFlag()));
            out.setLongFlag(String.valueOf(bo.getLongFlag()));
            out.setMakerFlag(String.valueOf(bo.getMakerFlag()));
            out.setTradePrice(String.valueOf(bo.getTradePrice()));
            out.setTradeAmt(String.valueOf(bo.getTradeAmt()));
            out.setChangeType(String.valueOf(bo.getChangeType()));
            out.setChangeVolume(String.valueOf(bo.getChangeVolume()));
            out.setTransVolume(String.valueOf(bo.getTransVolume()));
            out.setTradeVolume(String.valueOf(bo.getTradeVolume()));
            out.setMarginVolume(String.valueOf(bo.getMarginVolume()));
            out.setObjectType(String.valueOf(bo.getObjectType()));
            out.setObjectId(String.valueOf(bo.getObjectId()));
            out.setRatio(String.valueOf(bo.getRatio()));
            out.setFee(String.valueOf(bo.getFee()));
            out.setUnfilledAmt(String.valueOf(bo.getUnfilledAmt()));
            out.setPosId(String.valueOf(bo.getPosId()));
            out.setOrderId(String.valueOf(bo.getOrderId()));
            out.setPnl(String.valueOf(bo.getPnl()));
            out.setOrderFeeCost(String.valueOf(bo.getOrderFeeCost()));
            out.setOrderOrderMargin(String.valueOf(bo.getOrderOrderMargin()));
            out.setOrderOpenFee(String.valueOf(bo.getOrderOpenFee()));
            out.setOrderCloseFee(String.valueOf(bo.getOrderCloseFee()));
            out.setOrderFundingFee(String.valueOf(bo.getOrderFundingFee()));
            out.setOrderTotalMargin(String.valueOf(bo.getOrderTotalMargin()));
            out.setPosFeeCost(String.valueOf(bo.getPosFeeCost()));
            out.setPosPosMargin(String.valueOf(bo.getPosPosMargin()));
            out.setPosEntryPrice(String.valueOf(bo.getPosEntryPrice()));
            out.setPosLiqPrice(String.valueOf(bo.getPosLiqPrice()));
            out.setPosBankruptPrice(String.valueOf(bo.getPosBankruptPrice()));
            out.setPosRealisedPnl(String.valueOf(bo.getPosRealisedPnl()));
            out.setPosLeverage(String.valueOf(bo.getPosLeverage()));
            out.setPosHoldMarginRatio(String.valueOf(bo.getPosHoldMarginRatio()));
            out.setAccOrderMargin(String.valueOf(bo.getAccOrderMargin()));
            out.setAccPosMargin(String.valueOf(bo.getAccPosMargin()));
            out.setAccTotal(String.valueOf(bo.getAccTotal()));
            out.setAccAvail(String.valueOf(bo.getAccAvail()));
            out.setAccLock(String.valueOf(bo.getAccLock()));
            out.setSymbolOrderMargin(String.valueOf(bo.getSymbolOrderMargin()));
            out.setSymbolPosMargin(String.valueOf(bo.getSymbolPosMargin()));
            out.setRemark(String.valueOf(bo.getRemark()));
            out.setChangeTime(String.valueOf(bo.getChangeTime()));
            out.setCtime(String.valueOf(bo.getCtime()));
            out.setMtime(String.valueOf(bo.getMtime()));
            list.add(out);
        }
        PcAssetChangeListOutputDto outputDto=new PcAssetChangeListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setRows(list);
        return  outputDto;
    }
}
