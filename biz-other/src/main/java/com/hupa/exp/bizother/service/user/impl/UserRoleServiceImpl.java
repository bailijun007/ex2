package com.hupa.exp.bizother.service.user.impl;

import com.hupa.exp.bizother.entity.user.ExpUserRoleBizBo;
import com.hupa.exp.bizother.service.user.def.IUserRoleService;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserRoleDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserRolePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService{

    @Autowired
    IExpUserRoleDao iExpUserRoleDao;
    @Override
    public long createUserRole(ExpUserRolePo expUserRolePo) {
        return iExpUserRoleDao.insert(expUserRolePo);
    }

    @Override
    public int deleteById(long id) {
        return iExpUserRoleDao.deleteById(id);
    }

   @Override
    public List<ExpUserRoleBizBo> queryPosByUserId(long userId) {
        List<ExpUserRolePo> poList=iExpUserRoleDao.selectPosByUserId(userId);
       List<ExpUserRoleBizBo> boList=new ArrayList<>();
       for(ExpUserRolePo expUserRolePo:poList)
       {
           ExpUserRoleBizBo expUserBo= ConventObjectUtil.conventObject(expUserRolePo,ExpUserRoleBizBo.class);
           boList.add(expUserBo);
       }
        return boList;
    }

    @Override
    public Integer deleteUserRoleByUserId(long userId) {
        return iExpUserRoleDao.deleteUserRoleByUserId(userId);
    }
}
