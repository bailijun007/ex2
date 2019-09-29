package com.hupa.exp.bizother.service.area.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.ExpAreaBizBo;
import com.hupa.exp.bizother.entity.ExpAreaListBizBo;
import com.hupa.exp.bizother.service.area.def.IAreaService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IExpAreaDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpAreaPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private IExpAreaDao iExpAreaDao;
    @Override
    public long createArea(ExpAreaBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpAreaPo po= ConventObjectUtil.conventObject(expUserBo, ExpAreaPo.class);
        return iExpAreaDao.insert(po);
    }

    @Override
    public long editArea(ExpAreaBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpAreaPo po= ConventObjectUtil.conventObject(expUserBo, ExpAreaPo.class);
        return iExpAreaDao.updateById(po);
    }

    @Override
    public ExpAreaListBizBo queryAreaList(long currentPage, long pageSize) throws BizException {
        ExpAreaListBizBo listBizBo=new ExpAreaListBizBo();
        List<ExpAreaBizBo> boList=new ArrayList();
        IPage<ExpAreaPo> list=iExpAreaDao.selectAreaList(currentPage,pageSize);
        for(ExpAreaPo po:list.getRecords())
        {
            ExpAreaBizBo bo=ConventObjectUtil.conventObject(po,ExpAreaBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(list.getTotal());
        return listBizBo;

    }

    @Override
    public List<ExpAreaBizBo>  queryEnableAreaList() throws BizException {
        List<ExpAreaPo> list=iExpAreaDao.selectEnableAreaList();
        List<ExpAreaBizBo> boList=new ArrayList();
        for(ExpAreaPo po:list)
        {
            ExpAreaBizBo bo=ConventObjectUtil.conventObject(po,ExpAreaBizBo.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ExpAreaBizBo queryAreaById(long id) throws BizException {
        ExpAreaPo po=iExpAreaDao.selectPoById(id);
        if(po==null)
            return null;
        ExpAreaBizBo bo= ConventObjectUtil.conventObject(po,ExpAreaBizBo.class);
        return bo;
    }
}
