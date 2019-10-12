package com.hupa.exp.bizother.service.user.def;

import com.hupa.exp.bizother.entity.user.ExpUserRoleBizBo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserRolePo;

import java.util.List;

public interface IUserRoleService {
    List<ExpUserRoleBizBo> queryPosByUserId(long roleId);
}
