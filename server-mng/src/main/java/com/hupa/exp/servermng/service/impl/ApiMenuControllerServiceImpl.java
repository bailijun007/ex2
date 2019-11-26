package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.menu.ExpMenuBizBo;
import com.hupa.exp.bizother.entity.menu.ExpMenuTreeBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserRoleBizBo;
import com.hupa.exp.bizother.service.menu.def.IMenuService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.role.def.IRoleMenuService;
import com.hupa.exp.bizother.service.user.def.IUserRoleService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpMenuDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpRoleMenuPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.locale.LocaleDeleteOutputDto;
import com.hupa.exp.servermng.entity.menu.*;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import com.hupa.exp.servermng.enums.MenuExceptionCode;
import com.hupa.exp.servermng.exception.MenuException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiMenuControllerService;
import com.hupa.exp.servermng.validate.MenuValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiMenuControllerServiceImpl implements IApiMenuControllerService {
    @Autowired
    private IUserRoleService iUserRoleService;
    @Autowired
    private IRoleMenuService iRoleMenuService;
    @Autowired
    private IMenuService iMenuService;

    @Autowired
    private IExpMenuDao iExpMenuDao;
    @Autowired
    private MenuValidateImpl menuValidate;
    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public MenuHtmlOutputDto getMenuHtmlByUserId() throws BizException{
        ExpUserBizBo user= null;
        user = sessionHelper.getUserInfoBySession();
        if(user==null)
            throw new ValidateException(LoginExceptionCode.TOKEN_NULL_ERROR);
        MenuHtmlOutputDto outputDto=new MenuHtmlOutputDto();
        MenuHtmlInputDto inputDto=new MenuHtmlInputDto();
        //获取用户所有权限
        List<ExpUserRoleBizBo> expUserRoleList=iUserRoleService.queryPosByUserId(user.getId());
        List<Integer> userMenus=new ArrayList<>();
        for(int i=0;i<expUserRoleList.size();i++)
        {
            //获取角色对应的菜单
            List<ExpRoleMenuPo> roleMenuList=iRoleMenuService.queryPosByRoleId(expUserRoleList.get(i).getRoleid());
            for(int j=0;j<roleMenuList.size();j++)
            {
                //把用户所有的菜单获取到集合里
                userMenus.add(Integer.parseInt(String.valueOf(roleMenuList.get(j).getMenu_id())));
            }
        }
        //获取菜单列表
        String menuTrees=iMenuService.queryHtmlTreeView(userMenus);
        outputDto.setHtmlStr(menuTrees);
        return outputDto;
    }

    @Override
    public GetMenuOutputDto getMenuById(GetMenuInputDto inputDto) throws MenuException {
        //menuValidate.validate(inputDto);
        if(inputDto.getId()==null||inputDto.getId()==0)
            throw new MenuException(MenuExceptionCode.ID_NULL_ERROR);
        ExpMenuBizBo expMenuPo=iMenuService.queryPoById(inputDto.getId());
        GetMenuOutputDto outputDto= ConventObjectUtil.conventObject(expMenuPo,GetMenuOutputDto.class);
        return outputDto;
    }

    @Override
    public MenuListOutputDto queryMenuList(MenuListInputDto inputDto) throws MenuException {
        MenuListOutputDto outputDto=new MenuListOutputDto();
        List<ExpMenuBizBo> listMenuTrees=iMenuService.queryArrTreeView(0);
        outputDto.setPageData(listMenuTrees);
        return outputDto;
//        ExpMenuListBizBo listMenuTrees=iMenuService.pagePosByParam(0,inputDto.getCurrentPage(),
//                inputDto.getPageSize());
//        List<MenuListOutputPage> pageList=new ArrayList<>();
//        for(ExpMenuBizBo bo:listMenuTrees.getRows())
//        {
//            MenuListOutputPage row=new MenuListOutputPage();
//            row.setId(String.valueOf(bo.getId()));
//            row.setMenuname(bo.getMenuname());
//            row.setParentmenuid(String.valueOf(bo.getParentmenuid()));
//            row.setMenuurl(bo.getMenuurl());
//            row.setSort(String.valueOf(bo.getSort()));
//            row.setStatus(String.valueOf(bo.isEnable()));
//            row.setIcon(bo.getIcon());
//            row.setLevel(String.valueOf(bo.getLevel()));
//            pageList.add(row);
//        }
//        outputDto.setRows(pageList);
//        outputDto.setTotal(listMenuTrees.getTotal());
//        outputDto.setSizePerPage(inputDto.getPageSize());
//        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        //return outputDto;
    }

    @Override
    public MenuTreeListOutputDto queryMenuTreeList(MenuTreeListInputDto inputDto) throws MenuException {
        MenuTreeListOutputDto outputDto=new MenuTreeListOutputDto();
        List<ExpMenuTreeBizBo> listMenuTrees=iMenuService.queryTreeView(0);
        outputDto.setTreeBoList(listMenuTrees);
        return outputDto;
    }

    @Override
    public MenuOutputDto createMenu(MenuInputDto inputDto) throws BizException {
        menuValidate.validate(inputDto);
        ExpMenuBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpMenuBizBo.class);
        MenuOutputDto outputDto=new MenuOutputDto();
        if(inputDto.getId()>0)
        {
            inputDto.setMtime(System.currentTimeMillis());
            iMenuService.editById(bo);
        }
        else//不存在新增
        {
            inputDto.setMtime(System.currentTimeMillis());
            inputDto.setCtime(System.currentTimeMillis());
            iMenuService.createMenu(bo);
        }
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteMenu(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Locale.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpMenuDao.deleteById(Integer.valueOf(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


}
