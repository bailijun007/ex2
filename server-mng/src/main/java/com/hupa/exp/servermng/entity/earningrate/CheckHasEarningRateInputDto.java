package com.hupa.exp.servermng.entity.earningrate;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckHasEarningRateInputDto extends BaseInputDto
{
    private String userName;
    private Long rateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRateTime() {
        return rateTime;
    }

    public void setRateTime(Long rateTime) {
        this.rateTime = rateTime;
    }
}
