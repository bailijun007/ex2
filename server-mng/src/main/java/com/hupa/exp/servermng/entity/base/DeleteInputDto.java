package com.hupa.exp.servermng.entity.base;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DeleteInputDto extends BaseInputDto {
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
