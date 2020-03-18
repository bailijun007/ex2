package com.hupa.exp.servermng.entity.c2c;

/**
 * Created by Administrator on 2020/1/20.
 */
public class BankCardOutputDto {

    private String id;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 支持的交易币种
     */
    private String asset;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 持卡人姓名,不一定和user_id一样
     */
    private String cardUserName;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 开户支行
     */
    private String openBranch;
    /**
     * 状态
     */
    private Integer enableFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Long ctime;
    /**
     * 修改时间
     */
    private Long mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardUserName() {
        return cardUserName;
    }

    public void setCardUserName(String cardUserName) {
        this.cardUserName = cardUserName;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenBranch() {
        return openBranch;
    }

    public void setOpenBranch(String openBranch) {
        this.openBranch = openBranch;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
