package com.hupa.exp.servermng.entity.user;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class EditFundAccountInputDto extends BaseInputDto {

    private long id;
    /**
     * 资金（增加资产）
     */
    private String funds;

    /**
     *  0：扣钱
     *  1：加钱
     */
    private Integer type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
