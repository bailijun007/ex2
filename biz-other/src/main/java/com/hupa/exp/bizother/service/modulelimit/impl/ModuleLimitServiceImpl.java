package com.hupa.exp.bizother.service.modulelimit.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitBizBo;
import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitListBizBo;
import com.hupa.exp.bizother.service.modulelimit.def.IModuleLimitService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IExpModuleLimitDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpModuleLimitPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleLimitServiceImpl implements IModuleLimitService{
    @Autowired
    private IExpModuleLimitDao iExpModuleLimitDao;

    @Override
    public long createModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo) throws BizException {
        if(moduleLimitBizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpModuleLimitPo po= ConventObjectUtil.conventObject(moduleLimitBizBo,ExpModuleLimitPo.class);
        return iExpModuleLimitDao.insert(po);
    }

    @Override
    public long editModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo) throws BizException {
        if(moduleLimitBizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpModuleLimitPo po= ConventObjectUtil.conventObject(moduleLimitBizBo,ExpModuleLimitPo.class);
        return iExpModuleLimitDao.insert(po);
    }

    @Override
    public ExpModuleLimitListBizBo queryModuleLimitLsit(long currentPage, long pageSize) throws BizException {
        ExpModuleLimitListBizBo listBizBo=new ExpModuleLimitListBizBo();
        IPage<ExpModuleLimitPo> poList=iExpModuleLimitDao.selectModuleLimitList(currentPage,pageSize);
        List<ExpModuleLimitBizBo> boList=new ArrayList<>();
        for(ExpModuleLimitPo po:poList.getRecords())
        {
            ExpModuleLimitBizBo bo=ConventObjectUtil.conventObject(po,ExpModuleLimitBizBo.class);
            boList.add(bo);
        }
        listBizBo.setTotal(poList.getTotal());
        listBizBo.setRows(boList);
        return listBizBo;
    }

    @Override
    public ExpModuleLimitBizBo queryModuleLimitById(long id) throws BizException {
        ExpModuleLimitPo po=iExpModuleLimitDao.selectPoById(id);
        if(po==null)
            return null;
        ExpModuleLimitBizBo bo=ConventObjectUtil.conventObject(po,ExpModuleLimitBizBo.class);
        return bo;
    }
}
