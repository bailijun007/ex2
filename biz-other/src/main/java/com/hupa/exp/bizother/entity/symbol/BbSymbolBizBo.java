package com.hupa.exp.bizother.entity.symbol;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolBizBo {

    private Long id;
    private String symbol;
    private String asset;
    private Integer precision;
    private Integer numberPrecision;
     /**
     * 分组
     */
    private Integer bbGroupId;
    /**
     * 合约名称
     */
    private String bbSymbolName;
    /**
     * 合约名称分隔符
     */
    private String bbSymbolNameSplit;
    private String displayName;
    private String displayNameSplit;
    private BigDecimal step;
    private Integer sort;
    private Integer status;
    private BigDecimal minTradeNumber;
    private String symbolChinese;

    /**
     * 开启下单
     */
    private Integer enableCreate;

    /**
     * 开启撤单
     */
    private Integer enableCancel;

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

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getBbGroupId() {
        return bbGroupId;
    }

    public void setBbGroupId(Integer bbGroupId) {
        this.bbGroupId = bbGroupId;
    }

    public String getBbSymbolName() {
        return bbSymbolName;
    }

    public void setBbSymbolName(String bbSymbolName) {
        this.bbSymbolName = bbSymbolName;
    }

    public String getBbSymbolNameSplit() {
        return bbSymbolNameSplit;
    }

    public void setBbSymbolNameSplit(String bbSymbolNameSplit) {
        this.bbSymbolNameSplit = bbSymbolNameSplit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayNameSplit() {
        return displayNameSplit;
    }

    public void setDisplayNameSplit(String displayNameSplit) {
        this.displayNameSplit = displayNameSplit;
    }

    public BigDecimal getStep() {
        return step;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getNumberPrecision() {
        return numberPrecision;
    }

    public void setNumberPrecision(Integer numberPrecision) {
        this.numberPrecision = numberPrecision;
    }

    public Integer getEnableCreate() {
        return enableCreate;
    }

    public void setEnableCreate(Integer enableCreate) {
        this.enableCreate = enableCreate;
    }

    public Integer getEnableCancel() {
        return enableCancel;
    }

    public void setEnableCancel(Integer enableCancel) {
        this.enableCancel = enableCancel;
    }

    public String getSymbolChinese() {
        return symbolChinese;
    }

    public void setSymbolChinese(String symbolChinese) {
        this.symbolChinese = symbolChinese;
    }

    public BigDecimal getMinTradeNumber() {
        return minTradeNumber;
    }

    public void setMinTradeNumber(BigDecimal minTradeNumber) {
        this.minTradeNumber = minTradeNumber;
    }
}
