package com.hupa.exp.servermng.entity.locale;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class CheckHasLocaleInputDto extends BaseInputDto {

    private Integer errorCode;


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
