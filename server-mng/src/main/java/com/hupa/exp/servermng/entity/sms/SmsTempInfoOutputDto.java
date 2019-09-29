package com.hupa.exp.servermng.entity.sms;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.beans.FeatureDescriptor;

public class SmsTempInfoOutputDto extends BaseOutputDto {
    @JSONField(serialzeFeatures = SerializerFeature.WriteNonStringValueAsString )
    private Long id;
    private String content;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNonStringValueAsString )
    private Integer type;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNonStringValueAsString )
    private Long ctime;
    @JSONField(serialzeFeatures = SerializerFeature.WriteNonStringValueAsString )
    private Long mtime;
    private String remark;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
