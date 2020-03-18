package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

/**
 * 提现历史记录
 */
public class FundWithdrawOutputDto extends BaseOutputDto{
    /**
     * 主键id
     */
    private String id;
    /**
     * 币种
     */
    private String asset;
    /**
     * 用户id
     */
    private String accountId;
    /**
     * 提币地址
     */
    private String targetAddr;
    /**
     * 数量、金额
     */
    private String volume; //amount;

    /**
     *  提现HASH
     */
    private String txHash;

    /**
     *  支付id，流水号
     */
    private String transactionId;

    /**
     * 提币手续费
     */
    private String fee;
    /**
     * 提币时间
     */
    private String withdrawTime;
    /**
     * 状态：0：已创建，1：成功，2：失败，3-同步余额,4：审核中，5-审核通过,6：审核不通过
     */
    private String status;

    /**
     * 支付状态、提现状态
     */
    private String payStatus;

    /**
     * 支付状态描述
     */
    private String payStatusDesc;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 修改时间
     */
    private String mtime;


    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

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

    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusDesc() {
        return payStatusDesc;
    }

    public void setPayStatusDesc(String payStatusDesc) {
        this.payStatusDesc = payStatusDesc;
    }
}
