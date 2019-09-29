package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.role.*;
import com.hupa.exp.servermng.exception.RoleException;

public interface IApiRoleControllerService {

    RoleOutputDto createOrEditRole(RoleMenuInputDto inputDto) throws BizException;

    RoleAllOutputDto queryRoleList(RoleAllInputDto inputDto) throws BizException;

    RoleListOutputDto queryRolePageList(RoleListInputDto inputDto) throws BizException;

    RoleOutputDto queryRoleById(long id) throws RoleException;
}
