package com.hupa.exp.bizother.entity.account.MongoBo;

import com.hupa.exp.base.entity.bo.pc.PcAssetChangeBo;

public class PcAssetChangeMongoBizBo extends PcAssetChangeBo {
//    private long id;
//    private long accountId;
//    private String symbol;
//    private String pair;
//    private Integer bidFlag;
//    private Integer closeFlag;
//    private BigDecimal tradePrice;
//    private BigDecimal tradeAmt;
//    private int tradeType;
//    private long objectId;
//    private BigDecimal ratio;
//    private BigDecimal fee;
//    private Integer orderType;
//    private BigDecimal orderPrice;
//    private BigDecimal orderAmt;
//    private BigDecimal unfilledAmt;
//
//    private long posId;
//    private long orderId;
//    /**
//     * 利润或亏损
//     */
//    private BigDecimal pnl;
//
//    private BigDecimal orderMarginRls;
//    private BigDecimal orderMargin;
//
//    private BigDecimal posMarginRls;
//    private BigDecimal posMargin;
//
//    private BigDecimal accOrderMarginPre;
//    private BigDecimal accOrderMargin;
//    private BigDecimal accPosMarginPre;
//    private BigDecimal accPosMargin;
//
//    private BigDecimal pairOrderMarginPre;
//    private BigDecimal pairOrderMargin;
//    private BigDecimal pairPosMarginPre;
//    private BigDecimal pairPosMargin;
//
//    private BigDecimal accTotalPre;
//    private BigDecimal accTotal;
//    private BigDecimal accAvailPre;
//    private BigDecimal accAvail;
//
//    private String remark;
//    private long tradeTime;
//    private long ctime;
//    private long mtime;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(long accountId) {
//        this.accountId = accountId;
//    }
//
//    public String getSymbol() {
//        return symbol;
//    }
//
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }
//
//    public String getPair() {
//        return pair;
//    }
//
//    public void setPair(String pair) {
//        this.pair = pair;
//    }
//
//    public Integer getBidFlag() {
//        return bidFlag;
//    }
//
//    public void setBidFlag(Integer bidFlag) {
//        this.bidFlag = bidFlag;
//    }
//
//    public Integer getCloseFlag() {
//        return closeFlag;
//    }
//
//    public void setCloseFlag(Integer closeFlag) {
//        this.closeFlag = closeFlag;
//    }
//
//    public BigDecimal getTradePrice() {
//        return tradePrice;
//    }
//
//    public void setTradePrice(BigDecimal tradePrice) {
//        this.tradePrice = tradePrice;
//    }
//
//    public BigDecimal getTradeAmt() {
//        return tradeAmt;
//    }
//
//    public void setTradeAmt(BigDecimal tradeAmt) {
//        this.tradeAmt = tradeAmt;
//    }
//
//    public int getTradeType() {
//        return tradeType;
//    }
//
//    public void setTradeType(int tradeType) {
//        this.tradeType = tradeType;
//    }
//
//    public long getObjectId() {
//        return objectId;
//    }
//
//    public void setObjectId(long objectId) {
//        this.objectId = objectId;
//    }
//
//    public BigDecimal getRatio() {
//        return ratio;
//    }
//
//    public void setRatio(BigDecimal ratio) {
//        this.ratio = ratio;
//    }
//
//    public BigDecimal getFee() {
//        return fee;
//    }
//
//    public void setFee(BigDecimal fee) {
//        this.fee = fee;
//    }
//
//    public Integer getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(Integer orderType) {
//        this.orderType = orderType;
//    }
//
//    public BigDecimal getOrderPrice() {
//        return orderPrice;
//    }
//
//    public void setOrderPrice(BigDecimal orderPrice) {
//        this.orderPrice = orderPrice;
//    }
//
//    public BigDecimal getOrderAmt() {
//        return orderAmt;
//    }
//
//    public void setOrderAmt(BigDecimal orderAmt) {
//        this.orderAmt = orderAmt;
//    }
//
//    public BigDecimal getUnfilledAmt() {
//        return unfilledAmt;
//    }
//
//    public void setUnfilledAmt(BigDecimal unfilledAmt) {
//        this.unfilledAmt = unfilledAmt;
//    }
//
//    public long getPosId() {
//        return posId;
//    }
//
//    public void setPosId(long posId) {
//        this.posId = posId;
//    }
//
//    public long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }
//
//    public BigDecimal getPnl() {
//        return pnl;
//    }
//
//    public void setPnl(BigDecimal pnl) {
//        this.pnl = pnl;
//    }
//
//    public BigDecimal getOrderMarginRls() {
//        return orderMarginRls;
//    }
//
//    public void setOrderMarginRls(BigDecimal orderMarginRls) {
//        this.orderMarginRls = orderMarginRls;
//    }
//
//    public BigDecimal getOrderMargin() {
//        return orderMargin;
//    }
//
//    public void setOrderMargin(BigDecimal orderMargin) {
//        this.orderMargin = orderMargin;
//    }
//
//    public BigDecimal getPosMarginRls() {
//        return posMarginRls;
//    }
//
//    public void setPosMarginRls(BigDecimal posMarginRls) {
//        this.posMarginRls = posMarginRls;
//    }
//
//    public BigDecimal getPosMargin() {
//        return posMargin;
//    }
//
//    public void setPosMargin(BigDecimal posMargin) {
//        this.posMargin = posMargin;
//    }
//
//    public BigDecimal getAccOrderMarginPre() {
//        return accOrderMarginPre;
//    }
//
//    public void setAccOrderMarginPre(BigDecimal accOrderMarginPre) {
//        this.accOrderMarginPre = accOrderMarginPre;
//    }
//
//    public BigDecimal getAccOrderMargin() {
//        return accOrderMargin;
//    }
//
//    public void setAccOrderMargin(BigDecimal accOrderMargin) {
//        this.accOrderMargin = accOrderMargin;
//    }
//
//    public BigDecimal getAccPosMarginPre() {
//        return accPosMarginPre;
//    }
//
//    public void setAccPosMarginPre(BigDecimal accPosMarginPre) {
//        this.accPosMarginPre = accPosMarginPre;
//    }
//
//    public BigDecimal getAccPosMargin() {
//        return accPosMargin;
//    }
//
//    public void setAccPosMargin(BigDecimal accPosMargin) {
//        this.accPosMargin = accPosMargin;
//    }
//
//    public BigDecimal getPairOrderMarginPre() {
//        return pairOrderMarginPre;
//    }
//
//    public void setPairOrderMarginPre(BigDecimal pairOrderMarginPre) {
//        this.pairOrderMarginPre = pairOrderMarginPre;
//    }
//
//    public BigDecimal getPairOrderMargin() {
//        return pairOrderMargin;
//    }
//
//    public void setPairOrderMargin(BigDecimal pairOrderMargin) {
//        this.pairOrderMargin = pairOrderMargin;
//    }
//
//    public BigDecimal getPairPosMarginPre() {
//        return pairPosMarginPre;
//    }
//
//    public void setPairPosMarginPre(BigDecimal pairPosMarginPre) {
//        this.pairPosMarginPre = pairPosMarginPre;
//    }
//
//    public BigDecimal getPairPosMargin() {
//        return pairPosMargin;
//    }
//
//    public void setPairPosMargin(BigDecimal pairPosMargin) {
//        this.pairPosMargin = pairPosMargin;
//    }
//
//    public BigDecimal getAccTotalPre() {
//        return accTotalPre;
//    }
//
//    public void setAccTotalPre(BigDecimal accTotalPre) {
//        this.accTotalPre = accTotalPre;
//    }
//
//    public BigDecimal getAccTotal() {
//        return accTotal;
//    }
//
//    public void setAccTotal(BigDecimal accTotal) {
//        this.accTotal = accTotal;
//    }
//
//    public BigDecimal getAccAvailPre() {
//        return accAvailPre;
//    }
//
//    public void setAccAvailPre(BigDecimal accAvailPre) {
//        this.accAvailPre = accAvailPre;
//    }
//
//    public BigDecimal getAccAvail() {
//        return accAvail;
//    }
//
//    public void setAccAvail(BigDecimal accAvail) {
//        this.accAvail = accAvail;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    public long getTradeTime() {
//        return tradeTime;
//    }
//
//    public void setTradeTime(long tradeTime) {
//        this.tradeTime = tradeTime;
//    }
//
//    public long getCtime() {
//        return ctime;
//    }
//
//    public void setCtime(long ctime) {
//        this.ctime = ctime;
//    }
//
//    public long getMtime() {
//        return mtime;
//    }
//
//    public void setMtime(long mtime) {
//        this.mtime = mtime;
//    }
}
