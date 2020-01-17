package com.hupa.exp.servermng.entity.c2c;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

/**
 * Created by Administrator on 2020/1/14.
 */
public class AuditC2COutputDto extends BaseOutputDto {

    private Boolean approvalStatus;

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
