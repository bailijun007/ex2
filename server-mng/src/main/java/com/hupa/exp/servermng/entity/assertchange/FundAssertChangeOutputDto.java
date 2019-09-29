package com.hupa.exp.servermng.entity.assertchange;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class FundAssertChangeOutputDto extends BaseOutputDto {
    private String id;
    private String accountId;
    private String symbol;
    private String tradeVolume;
    private String tradeType;
    private String objectId;
    private String fee;
    //private String orderType;

    private String accLockPre;
    private String accLock;
    private String accTotalPre;
    private String accTotal;
    private String accAvailPre;
    private String accAvail;

    private String remark;
    private String tradeTime;
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

    public String getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(String tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

//    public String getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(String orderType) {
//        this.orderType = orderType;
//    }

    public String getAccLockPre() {
        return accLockPre;
    }

    public void setAccLockPre(String accLockPre) {
        this.accLockPre = accLockPre;
    }

    public String getAccLock() {
        return accLock;
    }

    public void setAccLock(String accLock) {
        this.accLock = accLock;
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

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
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
