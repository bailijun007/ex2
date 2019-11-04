package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

public class PcAssetChangeOutputDto extends BaseOutputDto {

    private String id;
    private String srcAccountId;
    private String accountId;
    private String asset;
    private String symbol;
    private String bidFlag;
    private String closeFlag;
    private String longFlag;
    private String makerFlag;
    private String tradePrice;
    private String tradeAmt;
    private String changeType;
    private String changeVolume;
    private String transVolume;
    private String tradeVolume;
    private String marginVolume;
    private String objectType;
    private String objectId;
    private String ratio;
    private String fee;
    private String unfilledAmt;
    private String posId;
    private String orderId;
    private String pnl;
    private String orderFeeCost;
    private String orderOrderMargin;
    private String orderOpenFee;
    private String orderCloseFee;
    private String orderFundingFee;
    private String orderTotalMargin;
    private String posFeeCost;
    private String posPosMargin;
    private String posEntryPrice;
    private String posLiqPrice;
    private String posBankruptPrice;
    private String posRealisedPnl;
    private String posLeverage;
    private String posHoldMarginRatio;
    private String accOrderMargin;
    private String accPosMargin;
    private String accTotal;
    private String accAvail;
    private String accLock;
    private String symbolOrderMargin;
    private String symbolPosMargin;
    private String remark;
    private String changeTime;
    private String ctime;
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrcAccountId() {
        return srcAccountId;
    }

    public void setSrcAccountId(String srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBidFlag() {
        return bidFlag;
    }

    public void setBidFlag(String bidFlag) {
        this.bidFlag = bidFlag;
    }

    public String getCloseFlag() {
        return closeFlag;
    }

    public void setCloseFlag(String closeFlag) {
        this.closeFlag = closeFlag;
    }

    public String getLongFlag() {
        return longFlag;
    }

    public void setLongFlag(String longFlag) {
        this.longFlag = longFlag;
    }

    public String getMakerFlag() {
        return makerFlag;
    }

    public void setMakerFlag(String makerFlag) {
        this.makerFlag = makerFlag;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(String tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeVolume() {
        return changeVolume;
    }

    public void setChangeVolume(String changeVolume) {
        this.changeVolume = changeVolume;
    }

    public String getTransVolume() {
        return transVolume;
    }

    public void setTransVolume(String transVolume) {
        this.transVolume = transVolume;
    }

    public String getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(String tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public String getMarginVolume() {
        return marginVolume;
    }

    public void setMarginVolume(String marginVolume) {
        this.marginVolume = marginVolume;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getUnfilledAmt() {
        return unfilledAmt;
    }

    public void setUnfilledAmt(String unfilledAmt) {
        this.unfilledAmt = unfilledAmt;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPnl() {
        return pnl;
    }

    public void setPnl(String pnl) {
        this.pnl = pnl;
    }

    public String getOrderFeeCost() {
        return orderFeeCost;
    }

    public void setOrderFeeCost(String orderFeeCost) {
        this.orderFeeCost = orderFeeCost;
    }

    public String getOrderOrderMargin() {
        return orderOrderMargin;
    }

    public void setOrderOrderMargin(String orderOrderMargin) {
        this.orderOrderMargin = orderOrderMargin;
    }

    public String getOrderOpenFee() {
        return orderOpenFee;
    }

    public void setOrderOpenFee(String orderOpenFee) {
        this.orderOpenFee = orderOpenFee;
    }

    public String getOrderCloseFee() {
        return orderCloseFee;
    }

    public void setOrderCloseFee(String orderCloseFee) {
        this.orderCloseFee = orderCloseFee;
    }

    public String getOrderFundingFee() {
        return orderFundingFee;
    }

    public void setOrderFundingFee(String orderFundingFee) {
        this.orderFundingFee = orderFundingFee;
    }

    public String getOrderTotalMargin() {
        return orderTotalMargin;
    }

    public void setOrderTotalMargin(String orderTotalMargin) {
        this.orderTotalMargin = orderTotalMargin;
    }

    public String getPosFeeCost() {
        return posFeeCost;
    }

    public void setPosFeeCost(String posFeeCost) {
        this.posFeeCost = posFeeCost;
    }

    public String getPosPosMargin() {
        return posPosMargin;
    }

    public void setPosPosMargin(String posPosMargin) {
        this.posPosMargin = posPosMargin;
    }

    public String getPosEntryPrice() {
        return posEntryPrice;
    }

    public void setPosEntryPrice(String posEntryPrice) {
        this.posEntryPrice = posEntryPrice;
    }

    public String getPosLiqPrice() {
        return posLiqPrice;
    }

    public void setPosLiqPrice(String posLiqPrice) {
        this.posLiqPrice = posLiqPrice;
    }

    public String getPosBankruptPrice() {
        return posBankruptPrice;
    }

    public void setPosBankruptPrice(String posBankruptPrice) {
        this.posBankruptPrice = posBankruptPrice;
    }

    public String getPosRealisedPnl() {
        return posRealisedPnl;
    }

    public void setPosRealisedPnl(String posRealisedPnl) {
        this.posRealisedPnl = posRealisedPnl;
    }

    public String getPosLeverage() {
        return posLeverage;
    }

    public void setPosLeverage(String posLeverage) {
        this.posLeverage = posLeverage;
    }

    public String getPosHoldMarginRatio() {
        return posHoldMarginRatio;
    }

    public void setPosHoldMarginRatio(String posHoldMarginRatio) {
        this.posHoldMarginRatio = posHoldMarginRatio;
    }

    public String getAccOrderMargin() {
        return accOrderMargin;
    }

    public void setAccOrderMargin(String accOrderMargin) {
        this.accOrderMargin = accOrderMargin;
    }

    public String getAccPosMargin() {
        return accPosMargin;
    }

    public void setAccPosMargin(String accPosMargin) {
        this.accPosMargin = accPosMargin;
    }

    public String getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(String accTotal) {
        this.accTotal = accTotal;
    }

    public String getAccAvail() {
        return accAvail;
    }

    public void setAccAvail(String accAvail) {
        this.accAvail = accAvail;
    }

    public String getAccLock() {
        return accLock;
    }

    public void setAccLock(String accLock) {
        this.accLock = accLock;
    }

    public String getSymbolOrderMargin() {
        return symbolOrderMargin;
    }

    public void setSymbolOrderMargin(String symbolOrderMargin) {
        this.symbolOrderMargin = symbolOrderMargin;
    }

    public String getSymbolPosMargin() {
        return symbolPosMargin;
    }

    public void setSymbolPosMargin(String symbolPosMargin) {
        this.symbolPosMargin = symbolPosMargin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}
