package com.hupa.exp.bizother.service.role.def;

import com.hupa.exp.daoex2.entity.po.expv2.ExpRoleMenuPo;

import java.util.List;

public interface IRoleMenuService {
    long createRoleMenu(ExpRoleMenuPo roleMenuPo);

    List<ExpRoleMenuPo> queryPosByRoleId(long roleId);

    Integer deleteRoleMenuByRoleId(long roleId);
}
