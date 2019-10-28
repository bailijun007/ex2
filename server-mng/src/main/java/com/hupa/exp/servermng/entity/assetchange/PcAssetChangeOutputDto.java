package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class PcAssetChangeOutputDto extends BaseOutputDto {

    private String id;
    private String accountId;
    private String asset;
    private String symbol;
    private String bidFlag;
    private String closeFlag;
    private String tradePrice;
    private String tradeAmt;
    private String changeType;
    private String objectId;
    private String ratio;
    private String fee;
    private String orderType;
    private String orderPrice;
    private String orderAmt;
    private String unfilledAmt;

    private String posId;
    private String orderId;
    /**
     * 利润或亏损
     */
    private String pnl;

    private String orderMarginRls;
    private String orderMargin;

    private String posMarginRls;
    private String posMargin;

    private String accOrderMarginPre;
    private String accOrderMargin;
    private String accPosMarginPre;
    private String accPosMargin;

    private String symbolOrderMarginPre;
    private String symbolOrderMargin;
    private String symbolPosMarginPre;
    private String symbolPosMargin;

    private String accTotalPre;
    private String accTotal;
    private String accAvailPre;
    private String accAvail;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
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

    public String getOrderMarginRls() {
        return orderMarginRls;
    }

    public void setOrderMarginRls(String orderMarginRls) {
        this.orderMarginRls = orderMarginRls;
    }

    public String getOrderMargin() {
        return orderMargin;
    }

    public void setOrderMargin(String orderMargin) {
        this.orderMargin = orderMargin;
    }

    public String getPosMarginRls() {
        return posMarginRls;
    }

    public void setPosMarginRls(String posMarginRls) {
        this.posMarginRls = posMarginRls;
    }

    public String getPosMargin() {
        return posMargin;
    }

    public void setPosMargin(String posMargin) {
        this.posMargin = posMargin;
    }

    public String getAccOrderMarginPre() {
        return accOrderMarginPre;
    }

    public void setAccOrderMarginPre(String accOrderMarginPre) {
        this.accOrderMarginPre = accOrderMarginPre;
    }

    public String getAccOrderMargin() {
        return accOrderMargin;
    }

    public void setAccOrderMargin(String accOrderMargin) {
        this.accOrderMargin = accOrderMargin;
    }

    public String getAccPosMarginPre() {
        return accPosMarginPre;
    }

    public void setAccPosMarginPre(String accPosMarginPre) {
        this.accPosMarginPre = accPosMarginPre;
    }

    public String getAccPosMargin() {
        return accPosMargin;
    }

    public void setAccPosMargin(String accPosMargin) {
        this.accPosMargin = accPosMargin;
    }

    public String getSymbolOrderMarginPre() {
        return symbolOrderMarginPre;
    }

    public void setSymbolOrderMarginPre(String symbolOrderMarginPre) {
        this.symbolOrderMarginPre = symbolOrderMarginPre;
    }

    public String getSymbolOrderMargin() {
        return symbolOrderMargin;
    }

    public void setSymbolOrderMargin(String symbolOrderMargin) {
        this.symbolOrderMargin = symbolOrderMargin;
    }

    public String getSymbolPosMarginPre() {
        return symbolPosMarginPre;
    }

    public void setSymbolPosMarginPre(String symbolPosMarginPre) {
        this.symbolPosMarginPre = symbolPosMarginPre;
    }

    public String getSymbolPosMargin() {
        return symbolPosMargin;
    }

    public void setSymbolPosMargin(String symbolPosMargin) {
        this.symbolPosMargin = symbolPosMargin;
    }

    public String getAccTotalPre() {
        return accTotalPre;
    }

    public void setAccTotalPre(String accTotalPre) {
        this.accTotalPre = accTotalPre;
    }

    public String getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(String accTotal) {
        this.accTotal = accTotal;
    }

    public String getAccAvailPre() {
        return accAvailPre;
    }

    public void setAccAvailPre(String accAvailPre) {
        this.accAvailPre = accAvailPre;
    }

    public String getAccAvail() {
        return accAvail;
    }

    public void setAccAvail(String accAvail) {
        this.accAvail = accAvail;
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
