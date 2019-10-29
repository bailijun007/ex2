package com.hupa.exp.servermng.entity.asset;

import java.math.BigDecimal;

public class AssetListOutputPage {
    private String id;
    private String chainAppointId;
    private String realName;
    private String displayName;
    private String chainName;
    private String precision;
    private String privilege;
    private String status;
    private String sort;
    private String minDepositVolume;
    private String minWithdrawVolume;
    private String  withdrawFee;
    private String chainTransactionUrl;
    private String dwType;
    private String ctime;
    private String mtime;

    public String getDwType() {
        return dwType;
    }

    public void setDwType(String dwType) {
        this.dwType = dwType;
    }

    public String getMinDepositVolume() {
        return minDepositVolume;
    }

    public void setMinDepositVolume(String minDepositVolume) {
        this.minDepositVolume = minDepositVolume;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getChainTransactionUrl() {
        return chainTransactionUrl;
    }

    public void setChainTransactionUrl(String chainTransactionUrl) {
        this.chainTransactionUrl = chainTransactionUrl;
    }

    public String getMinWithdrawVolume() {
        return minWithdrawVolume;
    }

    public void setMinWithdrawVolume(String minWithdrawVolume) {
        this.minWithdrawVolume = minWithdrawVolume;
    }

    public String getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(String withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public String getChainAppointId() {
        return chainAppointId;
    }

    public void setChainAppointId(String chainAppointId) {
        this.chainAppointId = chainAppointId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
