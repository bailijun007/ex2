package com.hupa.exp.servermng.validate;

import com.hupa.exp.servermng.entity.menu.MenuInputDto;
import com.hupa.exp.servermng.enums.MenuExceptionCode;
import com.hupa.exp.servermng.exception.MenuException;
import org.springframework.stereotype.Service;

@Service("menuValidateImpl")
public class MenuValidateImpl implements IValidate<MenuInputDto> {
    @Override
    public void validate(MenuInputDto obj) throws MenuException {
        if(obj.getMenuname()==null||obj.getMenuname().isEmpty())
            throw new MenuException(MenuExceptionCode.MENUNAME_NULL_ERROR);
    }
}
