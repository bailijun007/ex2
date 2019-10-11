package com.hupa.exp.bizother.entity.locale;

public class ExpLocaleBizBo {
    private Long id;
    private Integer type;
    private Integer code;
    private String localeLanguage;
    private String localeConstant;
    private String localeContent;
    private String remark;
    private String module;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleConstant() {
        return localeConstant;
    }

    public void setLocaleConstant(String localeConstant) {
        this.localeConstant = localeConstant;
    }

    public String getLocaleContent() {
        return localeContent;
    }

    public void setLocaleContent(String localeContent) {
        this.localeContent = localeContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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
