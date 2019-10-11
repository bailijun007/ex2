package com.hupa.exp.bizother.entity.account.MongoBo;

import java.math.BigDecimal;


public class FundWithdrawMongoBizBo  {
    private long id;
    private String symbol;
    private long accountId;
    private String targetAddr;
    private BigDecimal volume;
    private BigDecimal fee;
    private BigDecimal withdrawTime;
    private Integer status;
    private Long ctime;
    private Long mtime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(BigDecimal withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }
}
