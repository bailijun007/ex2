package com.hupa.exp.bizother.service.user.def;

import com.hupa.exp.bizother.entity.ExpUserRoleBizBo;
import com.hupa.exp.daoex2.entity.po.expv2.ExpUserRolePo;

import java.util.List;

public interface IUserRoleService {
    long createUserRole(ExpUserRolePo expUserRolePo);

    int deleteById(long id);

    List<ExpUserRoleBizBo> queryPosByUserId(long roleId);

    Integer deleteUserRoleByUserId(long roleId);
}
