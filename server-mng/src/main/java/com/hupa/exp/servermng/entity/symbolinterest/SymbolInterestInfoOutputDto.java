package com.hupa.exp.servermng.entity.symbolinterest;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

public class SymbolInterestInfoOutputDto extends BaseOutputDto {
    private Long id;
    private String symbol;
    private BigDecimal symbolInterest;
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

    public BigDecimal getSymbolInterest() {
        return symbolInterest;
    }

    public void setSymbolInterest(BigDecimal symbolInterest) {
        this.symbolInterest = symbolInterest;
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
