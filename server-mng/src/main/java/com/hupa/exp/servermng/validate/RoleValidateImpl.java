package com.hupa.exp.servermng.validate;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.role.RoleMenuInputDto;
import com.hupa.exp.servermng.enums.RoleExceptionCode;
import com.hupa.exp.servermng.exception.RoleException;
import org.springframework.stereotype.Service;

@Service("roleValidateImpl")
public class RoleValidateImpl implements IValidate<RoleMenuInputDto> {
    @Override
    public void validate(RoleMenuInputDto obj) throws BizException {
        if(obj.getRoleName().isEmpty())
            throw new RoleException(RoleExceptionCode.ROLENAME_NULL_ERROR);

    }
}
