package com.hupa.exp.bizother.entity.fundaccount;

import com.hupa.exp.bizother.entity.assets.AssetsBizBo;

import java.util.List;

public class FundAccountMngBizBo {
    private long id;
    private String userName;
    private String realName;
    private List<AssetsBizBo> assets;

    public List<AssetsBizBo> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetsBizBo> assets) {
        this.assets = assets;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
