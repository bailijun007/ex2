package com.hupa.exp.bizother.entity.login;

import java.math.BigDecimal;

public class UserLoginBizBo {
    private long id;
    private String userName;
    private Integer userLevel;
    private Integer pwdLevel;
    private Integer feeLevel;
    private String lastLoginTime;
    private String loginIP;
    private String loginAddress;
    private long loginTime;
    private Integer isBindPhone;
    private Integer isBindEmail;
    private Integer isBindGoogle;
    private Integer isInstalledFundPwd;
    private Integer isRealNameAuth;
    private String realName;
    private String phone;
    private String areaCode;
    private String email;
    private Integer enablePhoneVerify;
    private Integer enableEmailVerify;
    private Integer enableGoogleVerify;
    private String referrerCode;
    private BigDecimal makerPcFee;
    private BigDecimal takerPcFee;
    private Integer bbFeeLevel;

    public BigDecimal getMakerPcFee() {
        return makerPcFee;
    }

    public void setMakerPcFee(BigDecimal makerPcFee) {
        this.makerPcFee = makerPcFee;
    }

    public BigDecimal getTakerPcFee() {
        return takerPcFee;
    }

    public void setTakerPcFee(BigDecimal takerPcFee) {
        this.takerPcFee = takerPcFee;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setReferrerCode(String referrerCode) {
        this.referrerCode = referrerCode;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public Integer getIsRealNameAuth() {
        return isRealNameAuth;
    }

    public void setIsRealNameAuth(Integer isRealNameAuth) {
        this.isRealNameAuth = isRealNameAuth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getPwdLevel() {
        return pwdLevel;
    }

    public void setPwdLevel(Integer pwdLevel) {
        this.pwdLevel = pwdLevel;
    }

    public Integer getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(Integer feeLevel) {
        this.feeLevel = feeLevel;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIsBindPhone() {
        return isBindPhone;
    }

    public void setIsBindPhone(Integer isBindPhone) {
        this.isBindPhone = isBindPhone;
    }

    public Integer getIsBindEmail() {
        return isBindEmail;
    }

    public void setIsBindEmail(Integer isBindEmail) {
        this.isBindEmail = isBindEmail;
    }

    public Integer getIsBindGoogle() {
        return isBindGoogle;
    }

    public void setIsBindGoogle(Integer isBindGoogle) {
        this.isBindGoogle = isBindGoogle;
    }

    public Integer getIsInstalledFundPwd() {
        return isInstalledFundPwd;
    }

    public void setIsInstalledFundPwd(Integer isInstalledFundPwd) {
        this.isInstalledFundPwd = isInstalledFundPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnablePhoneVerify() {
        return enablePhoneVerify;
    }

    public void setEnablePhoneVerify(Integer enablePhoneVerify) {
        this.enablePhoneVerify = enablePhoneVerify;
    }

    public Integer getEnableEmailVerify() {
        return enableEmailVerify;
    }

    public void setEnableEmailVerify(Integer enableEmailVerify) {
        this.enableEmailVerify = enableEmailVerify;
    }

    public Integer getEnableGoogleVerify() {
        return enableGoogleVerify;
    }

    public void setEnableGoogleVerify(Integer enableGoogleVerify) {
        this.enableGoogleVerify = enableGoogleVerify;
    }

    public Integer getBbFeeLevel() {
        return bbFeeLevel;
    }

    public void setBbFeeLevel(Integer bbFeeLevel) {
        this.bbFeeLevel = bbFeeLevel;
    }
}
