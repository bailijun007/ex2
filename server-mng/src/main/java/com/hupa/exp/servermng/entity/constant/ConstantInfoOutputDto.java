package com.hupa.exp.servermng.entity.constant;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class ConstantInfoOutputDto extends BaseOutputDto {

    private String id;
    private String key;
    private String value;
    private String splitSymbol;
    private String ctime;
    private String mtime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSplitSymbol() {
        return splitSymbol;
    }

    public void setSplitSymbol(String splitSymbol) {
        this.splitSymbol = splitSymbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
