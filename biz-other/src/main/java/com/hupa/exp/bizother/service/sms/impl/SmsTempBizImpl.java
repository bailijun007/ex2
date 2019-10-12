package com.hupa.exp.bizother.service.sms.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.sms.ExpSmsListBizBo;
import com.hupa.exp.bizother.entity.sms.ExpSmsTempBizBo;
import com.hupa.exp.bizother.service.sms.def.ISmsTempBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpSmsTempDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpSmsTempPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsTempBizImpl implements ISmsTempBiz {
    @Autowired
    IExpSmsTempDao iExpSmsTempDao;
    @Override
    public long createUser(ExpSmsTempBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpSmsTempPo po=  ConventObjectUtil.conventObject(expUserBo, ExpSmsTempPo.class);
        return iExpSmsTempDao.insert(po);
    }

    @Override
    public long editUser(ExpSmsTempBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpSmsTempPo po=  ConventObjectUtil.conventObject(expUserBo, ExpSmsTempPo.class);
        return iExpSmsTempDao.updateById(po);
    }

    @Override
    public ExpSmsListBizBo querySmsTempList(long currentPage, long pageSize) {
        ExpSmsListBizBo listBizBo=new ExpSmsListBizBo();
        List<ExpSmsTempBizBo> boList=new ArrayList();
        IPage<ExpSmsTempPo> list=iExpSmsTempDao.selectSmsTempList(currentPage,pageSize);
        for(ExpSmsTempPo po:list.getRecords())
        {
            ExpSmsTempBizBo bo=ConventObjectUtil.conventObject(po,ExpSmsTempBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(list.getTotal());
        return listBizBo;
    }

    @Override
    public ExpSmsTempBizBo querySmsTempById(long id) throws BizException {
        ExpSmsTempPo po =  iExpSmsTempDao.selectPoById(id);
        if(po==null)
            return null;
        ExpSmsTempBizBo bo=ConventObjectUtil.conventObject(po,ExpSmsTempBizBo.class);
        return bo;
    }
}
