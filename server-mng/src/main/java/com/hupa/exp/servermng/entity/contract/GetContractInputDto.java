package com.hupa.exp.servermng.entity.contract;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class GetContractInputDto extends BaseInputDto {
    private long id;

    private int contractGroup;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(int contractGroup) {
        this.contractGroup = contractGroup;
    }
}
