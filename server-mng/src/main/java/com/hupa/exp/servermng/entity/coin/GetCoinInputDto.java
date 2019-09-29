package com.hupa.exp.servermng.entity.coin;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetCoinInputDto extends BaseInputDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
