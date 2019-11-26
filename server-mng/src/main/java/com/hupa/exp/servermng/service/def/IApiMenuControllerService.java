package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.menu.*;
import com.hupa.exp.servermng.exception.MenuException;

public interface IApiMenuControllerService {
    MenuHtmlOutputDto getMenuHtmlByUserId() throws BizException;

    GetMenuOutputDto getMenuById(GetMenuInputDto inputDto) throws MenuException;

    MenuListOutputDto queryMenuList(MenuListInputDto inputDto) throws MenuException;

    MenuTreeListOutputDto queryMenuTreeList(MenuTreeListInputDto inputDto) throws MenuException;

    MenuOutputDto createMenu(MenuInputDto inputDto) throws BizException;

    DeleteOutputDto deleteMenu(DeleteInputDto inputDto) throws BizException;
}
