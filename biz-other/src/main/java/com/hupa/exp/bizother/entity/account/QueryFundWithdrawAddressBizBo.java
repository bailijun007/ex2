package com.hupa.exp.bizother.entity.account;

import java.util.ArrayList;
import java.util.List;

public class QueryFundWithdrawAddressBizBo {

    private int pageSize;

    private long currentPage;
    private long total;
    private List<QueryFundWithdrawAddressEntry> entryList = new ArrayList<>();




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

    public List<QueryFundWithdrawAddressEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<QueryFundWithdrawAddressEntry> entryList) {
        this.entryList = entryList;
    }




    public static class QueryFundWithdrawAddressEntry{
        private Long id;

        private Long accountId;

        private String symbol;


        private String address;

        private String remark;

        private Integer  enableFlag;

        private Long ctime;

        private Long mtime;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Integer getEnableFlag() {
            return enableFlag;
        }

        public void setEnableFlag(Integer enableFlag) {
            this.enableFlag = enableFlag;
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


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
