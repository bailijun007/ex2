package com.hupa.exp.servermng.entity.login;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class ExistLoginInputDto extends BaseInputDto {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
