package com.hupa.exp.servermng.entity.locale;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class LocaleDeleteInputDto extends BaseInputDto
{
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
