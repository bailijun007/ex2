package com.hupa.exp.servermng.entity.pcposition;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.daomysql.enums.SortEnum;

public class PcPositionPageInputDto extends BaseInputDto{
   private String pair;
   private Long posId;
   private Long accountId;
   private Integer liqStatus;
   private Integer currentPage;
   private Integer pageSize;
   private SortEnum sortEnum;
   private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
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

    public SortEnum getSortEnum() {
        return sortEnum;
    }

    public void setSortEnum(SortEnum sortEnum) {
        this.sortEnum = sortEnum;
    }
}
