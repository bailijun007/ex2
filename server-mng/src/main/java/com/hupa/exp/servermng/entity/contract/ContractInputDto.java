package com.hupa.exp.servermng.entity.contract;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.math.BigDecimal;

public class ContractInputDto extends BaseInputDto {

    private Long id;

    private String symbol;

    private Integer symbolType;

    private String asset;

    private Integer precision;
    /**
     *  合约名称
     */
    private String contractName;
    /**
     * 合约名称分隔符
     */
    private String contractNameSplit;
    /**
     * 合约类型：正向、反向 contract_type
     */
    private Integer contractType;
    /**
     * 合约分组 contract_group
     */
    private Integer contractGroup;
    /**
     * 结算金额 settle_price
     */
    private BigDecimal settlePrice;

    /**
     * 合约显示名
     */
    private String displayName;

    private String displayNameSplit;

    private BigDecimal defaultPrice;

    private BigDecimal lastPrice;

    /**
     * 步长
     */
    private BigDecimal step;

    private String quoteCurrency;

    private Integer faceValue;

    private String faceCurrency;

    private String baseCurrency;

    private String settleCurrency;
    /**
     * 权限
     */
    private Integer privilege;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开启下单
     */
    private Integer enableCreate;

    /**
     * 开启撤单
     */
    private Integer enableCancel;

    /**
     * 创建时间(时间戳)
     */
    private Long ctime;
    /**
     *  修改时间(时间戳)
     */
    private Long mtime;

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

    public Integer getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(Integer symbolType) {
        this.symbolType = symbolType;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getDisplayNameSplit() {
        return displayNameSplit;
    }

    public void setDisplayNameSplit(String displayNameSplit) {
        this.displayNameSplit = displayNameSplit;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

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

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
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
}
