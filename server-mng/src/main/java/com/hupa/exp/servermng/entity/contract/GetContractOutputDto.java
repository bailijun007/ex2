package com.hupa.exp.servermng.entity.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

public class GetContractOutputDto extends BaseOutputDto {
    private String id;
    private String symbol;
    @JsonProperty("symbol_type")
    private String symbolType;
    private String asset;
//    private String currency;
    private String precision;

    /**
     * 合约类型
     */
    @JsonProperty("contract_type")
    private Integer contractType;
    /**
     * 合约分组
     */
    @JsonProperty("contract_group")
    private Integer contractGroup;
    /**
     * 合约名称
     */
    @JsonProperty("contract_name")
    private String contractName;
    /**
     * 合约名称分隔符
     */
    @JsonProperty("contract_name_split")
    private String contractNameSplit;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_name_split")
    private String displayNameSplit;
    @JsonProperty("default_price")
    private String defaultPrice;
    @JsonProperty("last_price")
    private String lastPrice;
    private String step;
    @JsonProperty("face_value")
    private String faceValue;
    @JsonProperty("face_currency")
    private String faceCurrency;
    @JsonProperty("base_currency")
    private String baseCurrency;
    @JsonProperty("settle_currency")
    private String settleCurrency;
    /**
     * 结算金额
     */
    @JsonProperty("settle_price")
    private BigDecimal settlePrice;
    private String sort;
    private String status;
    private String enableCreate;//开启下单
    private String enableCancel;//开启撤单
    private String privilege;
    @JsonProperty("quote_currency")
    private String quoteCurrency;

    private String ctime;
    private String mtime;


    public String getFaceCurrency() {
        return faceCurrency;
    }

    public void setFaceCurrency(String faceCurrency) {
        this.faceCurrency = faceCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getDisplayNameSplit() {
        return displayNameSplit;
    }

    public void setDisplayNameSplit(String displayNameSplit) {
        this.displayNameSplit = displayNameSplit;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

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

    public String getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(String symbolType) {
        this.symbolType = symbolType;
    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
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

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
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

    public String getContractNameSplit() {
        return contractNameSplit;
    }

    public void setContractNameSplit(String contractNameSplit) {
        this.contractNameSplit = contractNameSplit;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Integer getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(Integer contractGroup) {
        this.contractGroup = contractGroup;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
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
