package com.hupa.exp.servermng.entity.user;

import com.hupa.exp.bizother.entity.AssetsBizBo;

import java.util.List;

public class FundAccountListOutputPage {
    private String id;
    private String userName;
    private String realName;
    private List<AssetsBizBo> assets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<AssetsBizBo> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetsBizBo> assets) {
        this.assets = assets;
    }
}
