package com.hupa.exp.servermng.entity.c2c;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

/**
 * Created by Administrator on 2020/1/20.
 */
public class BankCardInputDto extends BaseInputDto {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
