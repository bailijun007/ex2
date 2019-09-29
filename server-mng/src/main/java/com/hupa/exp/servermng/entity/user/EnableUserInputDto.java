package com.hupa.exp.servermng.entity.user;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class EnableUserInputDto extends BaseInputDto {
    private String ids;
    private Integer status;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
