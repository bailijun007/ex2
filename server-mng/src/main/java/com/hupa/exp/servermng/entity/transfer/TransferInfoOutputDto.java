package com.hupa.exp.servermng.entity.transfer;

import com.hupa.exp.base.entity.bo.BaseAccountTransferBo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易输出对象
 */
public class TransferInfoOutputDto {

    private String id;//等于返回transferId
    /**
     * 货币
     */
    private String asset;
    /**
     * 金额
     */
    private String amount;

    /**
     * 创建时间
     */
    private String created;
    /**
     * 状态：1-成功，2-失败
     */
    private String status;
    /**
     * 转出账户
     */
    private String fromAccount;
    /**
     * 转入账户
     */
    private String toAccount;

    /**
     * 转账时间
     */
    private String ctime;

/*
    private String srcAccountId;
    private String tarAccountId;
    private String srcAccType;
    private String tarAccType;
    private String volume;
    private String receiveFlag;
    private String receiveTime;
    private String remark;
    private String operator;
   */
    /**
     * 修改时间
     */
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

   /* public String getSrcAccountId() {
        return srcAccountId;
    }

    public void setSrcAccountId(String srcAccountId) {
        this.srcAccountId = srcAccountId;
    }

    public String getTarAccountId() {
        return tarAccountId;
    }

    public void setTarAccountId(String tarAccountId) {
        this.tarAccountId = tarAccountId;
    }

    public String getSrcAccType() {
        return srcAccType;
    }

    public void setSrcAccType(String srcAccType) {
        this.srcAccType = srcAccType;
    }

    public String getTarAccType() {
        return tarAccType;
    }

    public void setTarAccType(String tarAccType) {
        this.tarAccType = tarAccType;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getReceiveFlag() {
        return receiveFlag;
    }

    public void setReceiveFlag(String receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
*/
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }
}
