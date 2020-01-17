package com.hupa.exp.servermng.entity.funddeposit;

import java.math.BigDecimal;
import java.util.Date;

public class FundDepositInfoOutputDto {

     private String id;
    /**
     * 用户编号
     */
    private String accountId;
    /**
     * 币种
     */
    private String asset;
    /**
     * 充值地址
     */
    private String address;
    /**
     *  数量、金额
     */
    private String volume;
    /**
     * 充值时间
     */
    private String depositTime;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 充值状态(0:已创建，3：已到账)
     */
    private String status;
    /**
     * 修改时间
     */
    private String modified;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 更新时间
     */
    private String mtime;
    /**
     * 充值hash
     */
    private String txHash;


    private String chainTransactionUrl;
    private String chainServerOrderId;
    //最后确认时间
    private String lastConfirmTime;

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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
