package com.hupa.exp.bizother.entity.account;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QueryFundWithdrawHistoryBizBo {

    private int pageSize;
    private long currentPage;
    private long total;
    private List<QueryFundWithdrawHistoryBizEntry> entryList = new ArrayList<>();

    public List<QueryFundWithdrawHistoryBizEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<QueryFundWithdrawHistoryBizEntry> entryList) {
        this.entryList = entryList;
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }




    public static class QueryFundWithdrawHistoryBizEntry{

        @Id
        private long id;
        private long accountId;

        private String symbol;
        private String targetAddr;
        private BigDecimal volume;
        private BigDecimal fee;

        private String chainTransactionUrl;
        private String txHash;
        private long withdrawTime;
        private int status;

        private int conventStatus;
        private long ctime;
        private long mtime;
        private String failReason;

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

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
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

        public String getTxHash() {
            return txHash;
        }

        public void setTxHash(String txHash) {
            this.txHash = txHash;
        }

        public long getWithdrawTime() {
            return withdrawTime;
        }

        public void setWithdrawTime(long withdrawTime) {
            this.withdrawTime = withdrawTime;
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

        public String getFailReason() {
            return failReason;
        }

        public void setFailReason(String failReason) {
            this.failReason = failReason;
        }


        public int getConventStatus() {
            return conventStatus;
        }

        public void setConventStatus(int conventStatus) {
            this.conventStatus = conventStatus;
        }


        public String getChainTransactionUrl() {
            return chainTransactionUrl;
        }

        public void setChainTransactionUrl(String chainTransactionUrl) {
            this.chainTransactionUrl = chainTransactionUrl;
        }
    }
}
