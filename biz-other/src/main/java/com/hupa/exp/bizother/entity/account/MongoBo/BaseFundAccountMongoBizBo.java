package com.hupa.exp.bizother.entity.account.MongoBo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseFundAccountMongoBizBo implements Serializable {
        private String symbol;
        private BigDecimal total;
        private BigDecimal lock;
        private BigDecimal available;
        private int privilege;
        private int mngPrivilege;
        private long ctime;
        private long mtime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getLock() {
        return lock;
    }

    public void setLock(BigDecimal lock) {
        this.lock = lock;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public int getMngPrivilege() {
        return mngPrivilege;
    }

    public void setMngPrivilege(int mngPrivilege) {
        this.mngPrivilege = mngPrivilege;
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
}
