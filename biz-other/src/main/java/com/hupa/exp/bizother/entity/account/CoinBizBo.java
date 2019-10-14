package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;

public class CoinBizBo {
    private Long id;

    private Integer chainCoinId;
    private String coinName;
    private String displayName;
    private String chainName;
    private BigDecimal precision;
    private Integer privilege;
    private Integer status;
    private Integer sort;
    private BigDecimal minWithdrawVolume;
    private BigDecimal  withdrawFee;
    private String chainTransactionUrl;
    private Long ctime;
    private Long mtime;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getChainTransactionUrl() {
        return chainTransactionUrl;
    }

    public void setChainTransactionUrl(String chainTransactionUrl) {
        this.chainTransactionUrl = chainTransactionUrl;
    }

    public BigDecimal getMinWithdrawVolume() {
        return minWithdrawVolume;
    }

    public void setMinWithdrawVolume(BigDecimal minWithdrawVolume) {
        this.minWithdrawVolume = minWithdrawVolume;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public Integer getChainCoinId() {
        return chainCoinId;
    }

    public void setChainCoinId(Integer chainCoinId) {
        this.chainCoinId = chainCoinId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
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

    public BigDecimal getPrecision() {
        return precision;
    }

    public void setPrecision(BigDecimal precision) {
        this.precision = precision;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
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
