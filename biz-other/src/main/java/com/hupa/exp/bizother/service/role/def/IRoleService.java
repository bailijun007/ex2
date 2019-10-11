package com.hupa.exp.bizother.service.role.def;

import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.role.ExpRoleBizBo;
import com.hupa.exp.bizother.entity.role.ExpRoleListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.util.List;

public interface IRoleService {
    long createRole(ExpRoleBizBo role) throws ValidateException;
    long editRole(ExpRoleBizBo role) throws ValidateException;
    List<ExpRoleBizBo> queryRoleLsit();
   ExpRoleListBizBo queryRolePageLsit(long currentPage, long pageSize)throws BizException;
    ExpRoleBizBo queryRoleById(long id);
}
