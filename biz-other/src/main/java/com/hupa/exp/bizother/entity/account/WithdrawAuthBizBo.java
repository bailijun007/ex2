package com.hupa.exp.bizother.entity.account;

public class WithdrawAuthBizBo {

    //身份认证
    private int identityAuth;
    //短信认证
    private int shortMsgAuth;

    //谷歌认证
    private int googleAuth;

    //邮箱认证
    private int mailAuth;

    //资金密码认证
    private int fundPwdAuth;

    public int getIdentityAuth() {
        return identityAuth;
    }

    public void setIdentityAuth(int identityAuth) {
        this.identityAuth = identityAuth;
    }

    public int getShortMsgAuth() {
        return shortMsgAuth;
    }

    public void setShortMsgAuth(int shortMsgAuth) {
        this.shortMsgAuth = shortMsgAuth;
    }

    public int getGoogleAuth() {
        return googleAuth;
    }

    public void setGoogleAuth(int googleAuth) {
        this.googleAuth = googleAuth;
    }

    public int getMailAuth() {
        return mailAuth;
    }

    public void setMailAuth(int mailAuth) {
        this.mailAuth = mailAuth;
    }

    public int getFundPwdAuth() {
        return fundPwdAuth;
    }

    public void setFundPwdAuth(int fundPwdAuth) {
        this.fundPwdAuth = fundPwdAuth;
    }


}
