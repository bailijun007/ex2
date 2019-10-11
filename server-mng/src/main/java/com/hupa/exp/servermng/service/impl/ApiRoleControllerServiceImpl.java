package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hupa.exp.bizother.entity.role.ExpRoleBizBo;
import com.hupa.exp.bizother.entity.role.ExpRoleListBizBo;
import com.hupa.exp.bizother.service.role.def.IRoleMenuService;
import com.hupa.exp.bizother.service.role.def.IRoleService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.entity.po.expv2.ExpRoleMenuPo;
import com.hupa.exp.daoex2.entity.po.expv2.ExpRolePo;
import com.hupa.exp.servermng.entity.role.*;
import com.hupa.exp.servermng.enums.RoleExceptionCode;
import com.hupa.exp.servermng.exception.RoleException;
import com.hupa.exp.servermng.service.def.IApiRoleControllerService;
import com.hupa.exp.servermng.validate.RoleValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiRoleControllerServiceImpl implements IApiRoleControllerService{

    @Autowired
    RoleValidateImpl roleValidate;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IRoleMenuService iRoleMenuService;
    @Override
    public RoleOutputDto createOrEditRole(RoleMenuInputDto inputDto) throws BizException {
        roleValidate.validate(inputDto);
        ExpRoleBizBo bo=  ConventObjectUtil.conventObject(inputDto,ExpRoleBizBo.class);
        ExpRolePo role=new ExpRolePo();
        long id=0;
        if(inputDto.getId()>0)
        {
            bo=iRoleService.queryRoleById(inputDto.getId());//不为空读数据库
            id=bo.getId();
        }
        else
        {

            //role.setCtime(DateUtil.getSystemDate("yyyy-MM-dd HH:mm:ss"));//为空才有创建时间
        }
        role.setRoleName(inputDto.getRoleName());
        role.setDescription(inputDto.getDescription());
        role.setEnable(inputDto.isEnable());
        // role.setMtime(DateUtil.getSystemDate("yyyy-MM-dd HH:mm:ss"));
        boolean isTrue=false;
        if(inputDto.getId()>0)
        {
            if(iRoleService.editRole(bo)>0)
            {
                isTrue=true;
                iRoleMenuService.deleteRoleMenuByRoleId(id);
            }
        }
        else
        {
            id=iRoleService.createRole(bo);
            if(id>0)
                isTrue=true;
        }
        if(isTrue)
        {
            for(int i=0;i<inputDto.getMenuList().size();i++)
            {
                ExpRoleMenuPo expRoleMenuPo=new ExpRoleMenuPo();
                expRoleMenuPo.roleid=id;
                expRoleMenuPo.menuid=inputDto.getMenuList().get(i);
                iRoleMenuService.createRoleMenu(expRoleMenuPo);
            }
        }
        return null;
    }

    @Override
    public RoleAllOutputDto queryRoleList(RoleAllInputDto inputDto) throws BizException {
        RoleAllOutputDto outputDto=new RoleAllOutputDto();
        List<ExpRoleBizBo> boBizList=iRoleService.queryRoleLsit();
        outputDto.setUserRoleBoList(boBizList);
        return outputDto;
    }

    @Override
    public RoleListOutputDto queryRolePageList(RoleListInputDto inputDto) throws BizException {
        RoleListOutputDto outputDto=new RoleListOutputDto();
        ExpRoleListBizBo boBizList=iRoleService.queryRolePageLsit(inputDto.getCurrentPage(),inputDto.getPageSize());
        List<RoleListOutputPage> pageList=new ArrayList<>();
        for(ExpRoleBizBo bo:boBizList.getRows())
        {
            RoleListOutputPage row=new RoleListOutputPage();
            row.setId(String.valueOf(bo.getId()));
             row.setRoleName(bo.getRoleName());
             row.setDescription(bo.getDescription());
             row.setEnable(String.valueOf(bo.isEnable()));
             row.setCtime(String.valueOf(bo.getCtime()));
            pageList.add(row);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(boBizList.getTotal());
        return outputDto;
    }

    @Override
    public RoleOutputDto queryRoleById(long id) throws RoleException {
        ExpRoleBizBo boBiz= iRoleService.queryRoleById(id);
        if(boBiz==null)
            throw new RoleException(RoleExceptionCode.HAS_NULL_MODEL_ERROR);//查不到对象直接返回
        RoleOutputDto outputDto=ConventObjectUtil.conventObject(boBiz,RoleOutputDto.class);
        List<ExpRoleMenuPo> roleMenuList=iRoleMenuService.queryPosByRoleId(id);
        List<Integer> menuList=new ArrayList<>();
        for(int i=0;i<roleMenuList.size();i++)
        {
            menuList.add(Integer.parseInt(String.valueOf( roleMenuList.get(i).getMenu_id())));
        }
        outputDto.setMenulist(menuList);
        String aa= JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString);
        return outputDto;
    }
}
