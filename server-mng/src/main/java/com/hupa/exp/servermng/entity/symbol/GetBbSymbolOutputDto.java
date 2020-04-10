package com.hupa.exp.servermng.entity.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/9.
 */
public class GetBbSymbolOutputDto extends BaseOutputDto {

    private String id;
    private String symbol;

    private String asset;
    private String precision;
    private String numberPrecision;
    /**
     * 分组
     */
    @JsonProperty("bb_group_id")
    private Integer bbGroupId;
    /**
     * 名称
     */
    @JsonProperty("bbSymbol_name")
    private String bbSymbolName;
    //名称分隔符
    @JsonProperty("bbSymbol_name_split")
    private String bbSymbolNameSplit;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("display_name_split")
    private String displayNameSplit;
    private String step;
    private String sort;

    private String status;
    private String ctime;
    private String mtime;
    /**
     * 开启下单
     */
    private String enableCreate;

    /**
     * 开启撤单
     */
    private String enableCancel;


    @JsonProperty("symbol_chinese")
    private String symbolChinese;

    private String minTradeNumber;

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

    public String getSymbolChinese() {
        return symbolChinese;
    }

    public void setSymbolChinese(String symbolChinese) {
        this.symbolChinese = symbolChinese;
    }

    public String getMinTradeNumber() {
        return minTradeNumber;
    }

    public void setMinTradeNumber(String minTradeNumber) {
        this.minTradeNumber = minTradeNumber;
    }
}
