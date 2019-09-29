package com.hupa.exp.servermng.entity.fundwithdraw;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class AuditFundWithdrawOutputDto extends BaseOutputDto {
    private boolean auditStatus;

    public boolean isAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(boolean auditStatus) {
        this.auditStatus = auditStatus;
    }
}
