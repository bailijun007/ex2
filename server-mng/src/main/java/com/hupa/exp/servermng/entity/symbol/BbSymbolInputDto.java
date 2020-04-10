package com.hupa.exp.servermng.entity.symbol;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/9.
 */
public class BbSymbolInputDto extends BaseInputDto {

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
     *  名称
     */
    private String bbSymbolName;
    /**
     * 名称分隔符
     */
    private String bbSymbolNameSplit;

    /**
     * 合约显示名
     */
    private String displayName;

    private String displayNameSplit;
    /**
     * 步长
     */
    private BigDecimal step;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 开启下单
     */
    private Integer enableCreate;

    /**
     * 开启撤单
     */
    private Integer enableCancel;

    private BigDecimal minTradeNumber;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间(时间戳)
     */
    private Long ctime;
    /**
     *  修改时间(时间戳)
     */
    private Long mtime;


    private String symbolChinese;


/*    private Integer bbSymbolType;//类型：正向、反向
    private Integer symbolType;
    private BigDecimal settlePrice;// 结算金额
    private BigDecimal defaultPrice;
    private BigDecimal lastPrice;
    private String quoteCurrency;
    private Integer faceValue;
    private String faceCurrency;
    private String baseCurrency;
    private String settleCurrency;
    private Integer privilege;//权限*/

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
