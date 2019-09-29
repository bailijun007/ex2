package com.hupa.exp.servermng.entity.menu;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetMenuInputDto extends BaseInputDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
