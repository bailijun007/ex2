package com.hupa.exp.servermng.entity.c2c;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/1/14.
 */
public class AuditC2CInputDto extends BaseInputDto{
    /**
     * 主键id
     */
    private Long id;

    /**
     * 审批状态
     */
    private Integer approvalStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
