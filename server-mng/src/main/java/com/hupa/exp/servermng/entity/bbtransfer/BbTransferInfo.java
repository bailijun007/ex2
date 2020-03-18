package com.hupa.exp.servermng.entity.bbtransfer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/2/15.
 */
public class BbTransferInfo implements Serializable {
    /**
     * 自增主键
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 资产类型
     */
    private String asset;
    /**
     * 流水号
     */
    private String sn;
    /**
     * 类型：1 收入,-1 支出
     */
    private Integer type;
    /**
     * 本笔金额
     */
    private BigDecimal amount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 本笔余额
     */
    private BigDecimal balance;
    /**
     * 调用方支付单号
     */
    private String tradeNo;
    /**
     * 交易类型：1-资金转入，2-资金转出，3-下订单，4-撤单，4-追加保证金，5-平仓收益
     */
    private Integer tradeType;
    /**
     * 序号
     */
    private Long serialNo;
    /**
     * 关联对象的ID
     */
    private Long associatedId;
    /**
     * 事务ID
     */
    private Long txId;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * 修改时间
     */
    private Long modified;
    /**
     * 创建时间
     */
    private Long created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Long associatedId) {
        this.associatedId = associatedId;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}
