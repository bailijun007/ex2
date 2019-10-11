package com.hupa.exp.bizother.entity.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QueryDepositBizBo {

    //总记录数
    private long totalCount;

    //页行数
    private int pageSize;

    private long currentPage;


    private List<QueryDepositEntry> entries = new ArrayList<>();


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

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

    public List<QueryDepositEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<QueryDepositEntry> entries) {
        this.entries = entries;
    }

    public static class  QueryDepositEntry{


        private long id;
        private String chainServerOrderId;
        private long accountId;
        private String symbol;



        private String chainTransactionUrl;
        private String txHash;


        private BigDecimal volume;
        private long depositTime;
        private String address;
        private long lastConfirmTime;
        private int status;
        private long ctime;
        private long mtime;



        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getChainServerOrderId() {
            return chainServerOrderId;
        }

        public void setChainServerOrderId(String chainServerOrderId) {
            this.chainServerOrderId = chainServerOrderId;
        }

        public long getAccountId() {
            return accountId;
        }

        public void setAccountId(long accountId) {
            this.accountId = accountId;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTxHash() {
            return txHash;
        }

        public void setTxHash(String txHash) {
            this.txHash = txHash;
        }

        public BigDecimal getVolume() {
            return volume;
        }

        public void setVolume(BigDecimal volume) {
            this.volume = volume;
        }

        public long getDepositTime() {
            return depositTime;
        }

        public void setDepositTime(long depositTime) {
            this.depositTime = depositTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getLastConfirmTime() {
            return lastConfirmTime;
        }

        public void setLastConfirmTime(long lastConfirmTime) {
            this.lastConfirmTime = lastConfirmTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public long getMtime() {
            return mtime;
        }

        public void setMtime(long mtime) {
            this.mtime = mtime;
        }

        public String getChainTransactionUrl() {
            return chainTransactionUrl;
        }

        public void setChainTransactionUrl(String chainTransactionUrl) {
            this.chainTransactionUrl = chainTransactionUrl;
        }
    }
}
