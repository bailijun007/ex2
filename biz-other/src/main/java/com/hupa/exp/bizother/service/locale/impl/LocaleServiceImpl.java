package com.hupa.exp.bizother.service.locale.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.ExpLocaleBizBo;
import com.hupa.exp.bizother.entity.ExpLocaleListBizBo;
import com.hupa.exp.bizother.service.locale.def.ILocaleService;
import com.hupa.exp.daoex2.dao.expv2.def.IExpLocaleDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpLocalePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocaleServiceImpl implements ILocaleService {

    @Autowired
    private IExpLocaleDao iExpLocaleDao;

    @Override
    public long createLocale(ExpLocaleBizBo bizBo) throws ValidateException {
        if(bizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpLocalePo po= ConventObjectUtil.conventObject(bizBo,ExpLocalePo.class);
        return iExpLocaleDao.insert(po);
    }

    @Override
    public int editLocale(ExpLocaleBizBo bizBo) throws ValidateException {
        if(bizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpLocalePo po= ConventObjectUtil.conventObject(bizBo,ExpLocalePo.class);
        return iExpLocaleDao.updateById(po);
    }

    @Override
    public int deleteLocale(long id) {
        return iExpLocaleDao.deleteById(id);
    }

    @Override
    public ExpLocaleBizBo getOneLocale(Integer code) {
        ExpLocalePo po=iExpLocaleDao.selectOnePo(code);
        if(po==null)
            return null;
        ExpLocaleBizBo bo=ConventObjectUtil.conventObject(po,ExpLocaleBizBo.class);
        return bo;
    }

    @Override
    public ExpLocaleBizBo getLocaleById(long id) {
        ExpLocalePo po=iExpLocaleDao.selectPoById(id);
        ExpLocaleBizBo bo=ConventObjectUtil.conventObject(po,ExpLocaleBizBo.class);
        return bo;
    }

    @Override
    public ExpLocaleListBizBo getLocalePageData(String module, Integer errorCode, long currentPage, long pageSize) {
        IPage<ExpLocalePo> pageData=iExpLocaleDao.selectLocalePageData(module,errorCode,currentPage,pageSize);
        ExpLocaleListBizBo bizBo=new ExpLocaleListBizBo();
        bizBo.setTotal(pageData.getTotal());
        List<ExpLocaleBizBo> boList=new ArrayList<>();
        pageData.getRecords().forEach(po-> boList.add(ConventObjectUtil.conventObject(po,ExpLocaleBizBo.class)));
        bizBo.setRows(boList);
        return bizBo;
    }
}
