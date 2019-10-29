package com.hupa.exp.servermng.entity.asset;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class AssetInputDto extends BaseInputDto {
    private Long id;
    private Integer chainAppointId;
    private String realName;
    private String displayName;
    private String chainName;
    private BigDecimal precision;
    private Integer privilege;
    private Integer status;
    private Integer sort;
    private BigDecimal minDepositVolume;
    private BigDecimal minWithdrawVolume;
    private BigDecimal  withdrawFee;
    private String chainTransactionUrl;
    private Integer dwType;
    private Long ctime;
    private Long mtime;

    public Integer getDwType() {
        return dwType;
    }

    public void setDwType(Integer dwType) {
        this.dwType = dwType;
    }

    public BigDecimal getMinDepositVolume() {
        return minDepositVolume;
    }

    public void setMinDepositVolume(BigDecimal minDepositVolume) {
        this.minDepositVolume = minDepositVolume;
    }

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

    public Integer getChainAppointId() {
        return chainAppointId;
    }

    public void setChainAppointId(Integer chainAppointId) {
        this.chainAppointId = chainAppointId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getSymbol() {
//        return symbol;
//    }
//
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDisplaynName() {
        return displayName;
    }

    public void setDisplaynName(String displaynName) {
        this.displayName = displaynName;
    }

    public String getChainNname() {
        return chainName;
    }

    public void setChainNname(String chainNname) {
        this.chainName = chainNname;
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
