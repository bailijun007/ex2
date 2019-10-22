package com.hupa.exp.servermng.entity.funddeposit;

import java.math.BigDecimal;

public class FundDepositInfoOutputDto {
    private String id;
    private String chainServerOrderId;
    private String accountId;
    //private String symbol;
    private String asset;
    private String chainTransactionUrl;
    private String txHash;
    private String volume;
    private String depositTime;
    private String address;
    private String lastConfirmTime;
    private String status;
    private String ctime;
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChainServerOrderId() {
        return chainServerOrderId;
    }

    public void setChainServerOrderId(String chainServerOrderId) {
        this.chainServerOrderId = chainServerOrderId;
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

    public String getChainTransactionUrl() {
        return chainTransactionUrl;
    }

    public void setChainTransactionUrl(String chainTransactionUrl) {
        this.chainTransactionUrl = chainTransactionUrl;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastConfirmTime() {
        return lastConfirmTime;
    }

    public void setLastConfirmTime(String lastConfirmTime) {
        this.lastConfirmTime = lastConfirmTime;
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
