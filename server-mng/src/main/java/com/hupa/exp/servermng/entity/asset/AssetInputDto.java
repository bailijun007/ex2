package com.hupa.exp.servermng.entity.asset;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import java.math.BigDecimal;

/**
 * yan
 */
public class AssetInputDto extends BaseInputDto {

    private Long id;
    /**
     * 图标
     */
    private String icon;
    /**
     * 图标图片路径
     */
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
     * 链上服务的Id
     */
    private Integer chainAppointId;
    /**
     * 币所属的链
     */
    private String chainName;
    /**
     * 精度
     */
    private BigDecimal precision;
    /**
     * 权限
     */
    private Integer privilege;
    /**
     * 手续费费率
     */
    private BigDecimal  withdrawFee;
    /**
     * c2c手续费费率
     */
    private BigDecimal c2cFee;
    private BigDecimal minDepositVolume;
    private BigDecimal minWithdrawVolume;
    private String chainTransactionUrl;
    /**
     * 充提类型
     */
    private Integer dwType;
    /**
     * 永续合约账户是否启用 1是 0否
     */
    private Integer enableFlagPcAccount;
    /**
     * bb交易账户是否启用 1是 0否
     */
    private Integer enableFlagBbAccount;
    /**
     * 永续合约市场是否启用
     */
    private Integer enableFlagPcMarket;
    /**
     * 币币市场是否启用
     */
    private Integer enableFlagBbMarket;
    /**
     * 是否作废状态
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
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

    public Integer getDwType() {
        return dwType;
    }

    public void setDwType(Integer dwType) {
        this.dwType = dwType;
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

    public Integer getChainAppointId() {
        return chainAppointId;
    }

    public void setChainAppointId(Integer chainAppointId) {
        this.chainAppointId = chainAppointId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDisplaynName() {
        return displayName;
    }

    public void setDisplaynName(String displaynName) {
        this.displayName = displaynName;
    }

    public String getChainNname() {
        return chainName;
    }

    public void setChainNname(String chainNname) {
        this.chainName = chainNname;
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
