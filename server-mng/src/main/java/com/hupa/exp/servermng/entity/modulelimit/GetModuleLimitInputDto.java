package com.hupa.exp.servermng.entity.modulelimit;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetModuleLimitInputDto extends BaseInputDto {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
