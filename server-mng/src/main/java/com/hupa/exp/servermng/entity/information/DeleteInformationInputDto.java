package com.hupa.exp.servermng.entity.information;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class DeleteInformationInputDto extends BaseInputDto {
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
