package com.hupa.exp.bizother.entity.account;

import com.baomidou.mybatisplus.annotation.TableField;

import java.math.BigDecimal;

public class AssetBizBo {

    private Long id;

    private String symbol;

    private Integer chainSymbolId;

    /**
     * 币的符号
     */
    private String icon;

    private String iconImg;
    /**
     * 币的名称
     */
    private String realName;

    /**
     * 币的展示名
     */
    private String displayName;
    /**
     * 币所属的链
     */
    private String chainName;
    /**
     * 链上服务的约定的id
     */
    private Integer chainAppointId;
    /**
     * 精度
     */
    private BigDecimal precision;
    /**
     * 币的权限
     */
    private Integer privilege;
    /**
     * 是否启用
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 充币最小值
     */
    private BigDecimal minDepositVolume;

    /**
     * 提币最小值
     */
    private BigDecimal minWithdrawVolume;

    /**
     * 提币手续费
     */
    private BigDecimal  withdrawFee;

    /**
     * C2C手续费
     */
    private BigDecimal  c2cFee;

    /**
     * 查询地址
     */
    private String chainTransactionUrl;

    /**
     * 冲提类型
     */
    private Integer dwType;

    //永续合约账户启用 1是 0否
    private Integer enableFlagPcAccount;
    //bb交易账户启用 1是 0否
    private Integer enableFlagBbAccount;
    //永续合约市场启用
    private Integer enableFlagPcMarket;
    //币币交易启用
    private Integer enableFlagBbMarket;

    /**
     * 创建时间
     */
    private Long ctime;
    /**
     * 修改时间
     */
    private Long mtime;


    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getChainSymbolId() {
        return chainSymbolId;
    }

    public void setChainSymbolId(Integer chainSymbolId) {
        this.chainSymbolId = chainSymbolId;
    }

    public Integer getDwType() {
        return dwType;
    }

    public void setDwType(Integer dwType) {
        this.dwType = dwType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getChainAppointId() {
        return chainAppointId;
    }

    public void setChainAppointId(Integer chainAppointId) {
        this.chainAppointId = chainAppointId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public BigDecimal getMinDepositVolume() {
        return minDepositVolume;
    }

    public void setMinDepositVolume(BigDecimal minDepositVolume) {
        this.minDepositVolume = minDepositVolume;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getChainTransactionUrl() {
        return chainTransactionUrl;
    }

    public void setChainTransactionUrl(String chainTransactionUrl) {
        this.chainTransactionUrl = chainTransactionUrl;
    }

    public BigDecimal getMinWithdrawVolume() {
        return minWithdrawVolume;
    }

    public void setMinWithdrawVolume(BigDecimal minWithdrawVolume) {
        this.minWithdrawVolume = minWithdrawVolume;
    }

    public BigDecimal getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(BigDecimal withdrawFee) {
        this.withdrawFee = withdrawFee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public BigDecimal getPrecision() {
        return precision;
    }

    public void setPrecision(BigDecimal precision) {
        this.precision = precision;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
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

    public BigDecimal getC2cFee() {
        return c2cFee;
    }

    public void setC2cFee(BigDecimal c2cFee) {
        this.c2cFee = c2cFee;
    }

    public Integer getEnableFlagPcAccount() {
        return enableFlagPcAccount;
    }

    public void setEnableFlagPcAccount(Integer enableFlagPcAccount) {
        this.enableFlagPcAccount = enableFlagPcAccount;
    }

    public Integer getEnableFlagBbAccount() {
        return enableFlagBbAccount;
    }

    public void setEnableFlagBbAccount(Integer enableFlagBbAccount) {
        this.enableFlagBbAccount = enableFlagBbAccount;
    }

    public Integer getEnableFlagPcMarket() {
        return enableFlagPcMarket;
    }

    public void setEnableFlagPcMarket(Integer enableFlagPcMarket) {
        this.enableFlagPcMarket = enableFlagPcMarket;
    }

    public Integer getEnableFlagBbMarket() {
        return enableFlagBbMarket;
    }

    public void setEnableFlagBbMarket(Integer enableFlagBbMarket) {
        this.enableFlagBbMarket = enableFlagBbMarket;
    }
}
