package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolListOutputPage {

    private String id;
    private String symbol;
    private String asset;
    private String currency;
    private String precision;
    @JsonProperty("number_precision")
    private String numberPrecision;
    @JsonProperty("bb_group_id")
    private Integer bbGroupId;//分组
    @JsonProperty("bbSymbol_name")
    private String bbSymbolName;
    @JsonProperty("bbSymbol_name_split")
    private String bbSymbolNameSplit;//合约名称分隔符
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_name_split")
    private String displayNameSplit;
    private String step;
    private String sort;
    private String status;

    private String enableCreate;//开启下单

    private String enableCancel;//开启撤单

    private String ctime;
    private String mtime;


    /*@JsonProperty("default_price")
    private String defaultPrice;
    @JsonProperty("last_price")
    private String lastPrice;
    @JsonProperty("face_value")
    private String faceValue;
    @JsonProperty("quote_currency")
    private String quoteCurrency;
    @JsonProperty("face_currency")
    private String faceCurrency;
    @JsonProperty("base_currency")
    private String baseCurrency;
    @JsonProperty("settle_currency")
    private String settleCurrency;
    @JsonProperty("bbSymbol_type")
    private Integer bbSymbolType;//类型
    @JsonProperty("settle_price")
    private BigDecimal settlePrice;// 结算金额
    @JsonProperty("symbol_type")
    private String symbolType;
    private String privilege;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
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

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getNumberPrecision() {
        return numberPrecision;
    }

    public void setNumberPrecision(String numberPrecision) {
        this.numberPrecision = numberPrecision;
    }

    public String getEnableCreate() {
        return enableCreate;
    }

    public void setEnableCreate(String enableCreate) {
        this.enableCreate = enableCreate;
    }

    public String getEnableCancel() {
        return enableCancel;
    }

    public void setEnableCancel(String enableCancel) {
        this.enableCancel = enableCancel;
    }
}
