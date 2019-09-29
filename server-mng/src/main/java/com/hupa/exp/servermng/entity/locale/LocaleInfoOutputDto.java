package com.hupa.exp.servermng.entity.locale;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class LocaleInfoOutputDto extends BaseOutputDto {
    private String id;
    private String type;
    private String code;
    @JsonProperty("locale_language")
    private String localeLanguage;
    @JsonProperty("locale_constant")
    private String localeConstant;
    @JsonProperty("locale_content")
    private String localeContent;
    private String remark;
    private String module;
    private String ctime;
    private String mtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
}
