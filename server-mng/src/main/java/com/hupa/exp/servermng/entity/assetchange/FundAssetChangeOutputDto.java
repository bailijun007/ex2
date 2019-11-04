package com.hupa.exp.servermng.entity.assetchange;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

public class FundAssetChangeOutputDto extends BaseOutputDto {
    private String id;
    private String srcAccountId;
    private String accountId;
    private String asset;
    private String changeVolume;
    private String changeType;
    private String objectId;
    private String objectType;
    private String fee;
    private String accLockPre;
    private String accLock;
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

    public String getChangeVolume() {
        return changeVolume;
    }

    public void setChangeVolume(String changeVolume) {
        this.changeVolume = changeVolume;
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

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

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
