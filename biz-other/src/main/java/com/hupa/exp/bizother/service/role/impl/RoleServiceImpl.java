package com.hupa.exp.bizother.service.role.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.ExpRoleBizBo;
import com.hupa.exp.bizother.entity.ExpRoleListBizBo;
import com.hupa.exp.bizother.service.role.def.IRoleService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IExpRoleDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpRolePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
@Autowired
IExpRoleDao iExpRoleDao;

    @Override
    public long createRole(ExpRoleBizBo boBiz) throws ValidateException {
        if(boBiz==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpRolePo expUserPo= ConventObjectUtil.conventObject(boBiz,ExpRolePo.class);
        return iExpRoleDao.insert(expUserPo);
    }

    @Override
    public long editRole(ExpRoleBizBo boBiz) throws ValidateException {
        if(boBiz==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpRolePo role= ConventObjectUtil.conventObject(boBiz,ExpRolePo.class);
        return iExpRoleDao.updateById(role);
    }

    @Override
    public List<ExpRoleBizBo> queryRoleLsit() {
        List<ExpRolePo> polist=iExpRoleDao.selectPosByParam();
        List<ExpRoleBizBo> boBizs=new ArrayList<>();
         for(ExpRolePo po:polist)
         {
             boBizs.add(ConventObjectUtil.conventObject(po,ExpRoleBizBo.class));
         }
        return boBizs;
    }

    @Override
    public ExpRoleListBizBo queryRolePageLsit(long currentPage, long pageSize) throws BizException {
        ExpRoleListBizBo listBizBo=new ExpRoleListBizBo();
        List<ExpRoleBizBo> boList=new ArrayList();
        IPage<ExpRolePo> list=iExpRoleDao.pagePosByParam(currentPage,pageSize);
        for(ExpRolePo po:list.getRecords())
        {
            ExpRoleBizBo bo=ConventObjectUtil.conventObject(po,ExpRoleBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(list.getTotal());
        return listBizBo;
    }

    @Override
    public ExpRoleBizBo queryRoleById(long id) {
        ExpRolePo po= iExpRoleDao.selectPoById(id);
        if(po==null)
            return null;
        ExpRoleBizBo boBiz= ConventObjectUtil.conventObject(po,ExpRoleBizBo.class);
        return boBiz;
    }
}
