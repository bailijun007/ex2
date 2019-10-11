package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QueryAccountTransferBizBo {


    private int pageSize;
    private long currentPage;


    private long total;


    private List<QueryAccountTransferEntry> accountTransferEntries = new ArrayList<>();

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }


    public List<QueryAccountTransferEntry> getAccountTransferEntries() {
        return accountTransferEntries;
    }

    public void setAccountTransferEntries(List<QueryAccountTransferEntry> accountTransferEntries) {
        this.accountTransferEntries = accountTransferEntries;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public static class  QueryAccountTransferEntry{

        private Long id;

        private String symbol;

        private Long accountId;

        private Integer srcAccType;

        private Integer tarAccType;

        private BigDecimal volume;

        private Integer status;

        private Long ctime;

        private Long mtime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public Integer getSrcAccType() {
            return srcAccType;
        }

        public void setSrcAccType(Integer srcAccType) {
            this.srcAccType = srcAccType;
        }

        public Integer getTarAccType() {
            return tarAccType;
        }

        public void setTarAccType(Integer tarAccType) {
            this.tarAccType = tarAccType;
        }

        public BigDecimal getVolume() {
            return volume;
        }

        public void setVolume(BigDecimal volume) {
            this.volume = volume;
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
}
