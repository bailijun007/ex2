package com.hupa.exp.bizother.service.role.impl;

import com.hupa.exp.bizother.service.role.def.IRoleMenuService;
import com.hupa.exp.daomysql.dao.expv2.def.IExpRoleMenuDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpRoleMenuPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
    IExpRoleMenuDao iExpRoleMenuDao;
    @Override
    public long createRoleMenu(ExpRoleMenuPo roleMenuPo) {
        return iExpRoleMenuDao.insert(roleMenuPo);
    }

    @Override
    public List<ExpRoleMenuPo> queryPosByRoleId(long roleId) {
        return iExpRoleMenuDao.selectPosByRoleId(roleId);
    }

    @Override
    public Integer deleteRoleMenuByRoleId(long roleId) {
        return iExpRoleMenuDao.deleteRoleMenuByRoleId(roleId);
    }
}
