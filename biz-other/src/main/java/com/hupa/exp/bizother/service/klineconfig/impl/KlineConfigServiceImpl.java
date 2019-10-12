package com.hupa.exp.bizother.service.klineconfig.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigListBizBo;
import com.hupa.exp.bizother.service.klineconfig.def.IKlineConfigService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpKlineRequestConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpKlineRequestConfigPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlineConfigServiceImpl implements IKlineConfigService {

    @Autowired
    IExpKlineRequestConfigDao configDao;
    @Override
    public long createKlineConfig(ExpKlineConfigBizBo bo) throws BizException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpKlineRequestConfigPo po= ConventObjectUtil.conventObject(bo,ExpKlineRequestConfigPo.class);
        return  configDao.insert(po);
    }

    @Override
    public long editKlineConfig(ExpKlineConfigBizBo bo) throws BizException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpKlineRequestConfigPo po= ConventObjectUtil.conventObject(bo,ExpKlineRequestConfigPo.class);
        return  configDao.updateById(po);
    }

    @Override
    public ExpKlineConfigListBizBo querySmsTempList(long currentPage, long pageSize) throws BizException {
        ExpKlineConfigListBizBo listBizBo=new ExpKlineConfigListBizBo();
        List<ExpKlineConfigBizBo> boList=new ArrayList();
        IPage<ExpKlineRequestConfigPo> list=configDao.selectConfigList(currentPage,pageSize);
        for(ExpKlineRequestConfigPo po:list.getRecords())
        {
            ExpKlineConfigBizBo bo=ConventObjectUtil.conventObject(po,ExpKlineConfigBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(list.getTotal());

        return listBizBo;
    }

    @Override
    public ExpKlineConfigBizBo querySmsTempById(long id) throws BizException {
        ExpKlineRequestConfigPo po= configDao.selectPoById(id);
        if(po==null)
            return null;
        ExpKlineConfigBizBo bo=ConventObjectUtil.conventObject(po,ExpKlineConfigBizBo.class);
        return  bo;
    }
}
