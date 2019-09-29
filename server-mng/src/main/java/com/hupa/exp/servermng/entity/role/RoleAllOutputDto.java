package com.hupa.exp.servermng.entity.role;

import com.hupa.exp.bizother.entity.ExpRoleBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class RoleAllOutputDto extends BaseOutputDto{

    public List<ExpRoleBizBo> getUserRoleBoList() {
        return userRoleBoList;
    }

    public void setUserRoleBoList(List<ExpRoleBizBo> userRoleBoList) {
        this.userRoleBoList = userRoleBoList;
    }

    private List<ExpRoleBizBo> userRoleBoList;
}
