package com.hupa.exp.bizother.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;

import java.math.BigDecimal;
import java.util.List;

public class ExpUserBizBo {
    private Long id;
    private String userName;
    private String userpwd;
    private Integer userType;
    private Integer userLevel;
    private Integer pwdLevel;
    private Integer smsVerify;
    private Integer emailVerify;
    private Integer googleVerify;
    private String areaCode;
    private Integer status;
    private Long ctime;
    private Long mtime;
    private String email;
    private String phone;
    private String referrerId;
    private String referrerCode;
    private String nationality;
    private String realName;
    private String idNum;
    private String fundPwd;
    private String secretKey;
    private Integer feeLevel;
    private Integer idType;
    private String surname;
    private String name;
    private List<Integer> roleList;
    private Long loginTime;
    private String loginIp;
    private BigDecimal makerFee;
    private BigDecimal takerFee;

    private Integer bbFeeLevel;
    private BigDecimal bbMakerFee;
    private BigDecimal bbTakerFee;

    public BigDecimal getMakerFee() {
        return makerFee;
    }

    public void setMakerFee(BigDecimal makerFee) {
        this.makerFee = makerFee;
    }

    public BigDecimal getTakerFee() {
        return takerFee;
    }

    public void setTakerFee(BigDecimal takerFee) {
        this.takerFee = takerFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Integer getSmsVerify() {
        return smsVerify;
    }

    public void setSmsVerify(Integer smsVerify) {
        this.smsVerify = smsVerify;
    }

    public Integer getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(Integer emailVerify) {
        this.emailVerify = emailVerify;
    }

    public Integer getGoogleVerify() {
        return googleVerify;
    }

    public void setGoogleVerify(Integer googleVerify) {
        this.googleVerify = googleVerify;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setReferrerCode(String referrerCode) {
        this.referrerCode = referrerCode;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getFundPwd() {
        return fundPwd;
    }

    public void setFundPwd(String fundPwd) {
        this.fundPwd = fundPwd;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(Integer feeLevel) {
        this.feeLevel = feeLevel;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public BigDecimal getBbMakerFee() {
        return bbMakerFee;
    }

    public void setBbMakerFee(BigDecimal bbMakerFee) {
        this.bbMakerFee = bbMakerFee;
    }

    public BigDecimal getBbTakerFee() {
        return bbTakerFee;
    }

    public void setBbTakerFee(BigDecimal bbTakerFee) {
        this.bbTakerFee = bbTakerFee;
    }

    public Integer getBbFeeLevel() {
        return bbFeeLevel;
    }

    public void setBbFeeLevel(Integer bbFeeLevel) {
        this.bbFeeLevel = bbFeeLevel;
    }
}
