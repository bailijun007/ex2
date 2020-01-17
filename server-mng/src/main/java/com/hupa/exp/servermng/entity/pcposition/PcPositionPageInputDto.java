package com.hupa.exp.servermng.entity.pcposition;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcPositionPageInputDto extends BaseInputDto{
   private String asset;
   private String symbol;
   private Long posId;
   private Long accountId;
   private Integer liqStatus;
   private Integer currentPage;
   private Integer pageSize;
   //private MongoSortEnum sortEnum;
   private String account;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getLiqStatus() {
        return liqStatus;
    }

    public void setLiqStatus(Integer liqStatus) {
        this.liqStatus = liqStatus;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
