package com.hupa.exp.bizother.entity;

public class ExpModuleLimitBizBo {
    private Long id;
    private String module;
    private String moduleUrl;
    private Long triesLimit;
    private Integer enable;
    private Long ctime;
    private Long mtime;

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Long getTriesLimit() {
        return triesLimit;
    }

    public void setTriesLimit(Long triesLimit) {
        this.triesLimit = triesLimit;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
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
