package com.hupa.exp.servermng.entity.information;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetInformationInfoInputDto extends BaseInputDto {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
