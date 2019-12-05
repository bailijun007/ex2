package com.hupa.exp.servermng.entity.constant;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ConstantAllListInputDto extends BaseInputDto {
    private long parentId;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
