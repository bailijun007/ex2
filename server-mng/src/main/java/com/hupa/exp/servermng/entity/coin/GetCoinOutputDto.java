package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class GetCoinOutputDto extends BaseOutputDto {
    private String id;
    private String chainCoinId;
    private String coinName;
    private String displayName;
    private String chainName;
    private String precision;
    private String privilege;
    private String status;
    private String sort;
    private String minWithdrawVolume;
    private String  withdrawFee;
    private String chainTransactionUrl;
    private String ctime;
    private String mtime;

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

    public String getChainCoinId() {
        return chainCoinId;
    }

    public void setChainCoinId(String chainCoinId) {
        this.chainCoinId = chainCoinId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
