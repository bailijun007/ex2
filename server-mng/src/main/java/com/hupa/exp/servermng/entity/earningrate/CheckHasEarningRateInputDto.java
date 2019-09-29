package com.hupa.exp.servermng.entity.earningrate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckHasEarningRateInputDto extends BaseInputDto
{
    private String account;
    private Long rateTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getRateTime() {
        return rateTime;
    }

    public void setRateTime(Long rateTime) {
        this.rateTime = rateTime;
    }
}
