package com.hupa.exp.bizother.service.dictype.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.ExpDicTypeBizBo;
import com.hupa.exp.bizother.entity.ExpDicTypeListBizBo;
import com.hupa.exp.bizother.service.dictype.def.IDicTypeService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IExpDicTypeDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpDicTypePo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DicTypeServiceImpl implements IDicTypeService {
    @Autowired
    private IExpDicTypeDao iExpDicTypeDao;

    @Override
    public long createDicType(ExpDicTypeBizBo expDicTypeBizBo) throws BizException {
        if(expDicTypeBizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpDicTypePo po= ConventObjectUtil.conventObject(expDicTypeBizBo,ExpDicTypePo.class);
        return iExpDicTypeDao.insert(po);
    }

    @Override
    public long editDicType(ExpDicTypeBizBo expDicTypeBizBo) throws BizException {
        if(expDicTypeBizBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpDicTypePo po= ConventObjectUtil.conventObject(expDicTypeBizBo,ExpDicTypePo.class);
        return iExpDicTypeDao.updateById(po);
    }

    @Override
    public ExpDicTypeListBizBo queryDicTypeList(long currentPage, long pageSize) throws BizException {
        IPage<ExpDicTypePo> pageData=iExpDicTypeDao.selectDicTypePageData(currentPage,pageSize);
        ExpDicTypeListBizBo bizBo=new ExpDicTypeListBizBo();
        bizBo.setTotal(pageData.getTotal());
        List<ExpDicTypeBizBo> bizBoList=new ArrayList<>();
        for(ExpDicTypePo po:pageData.getRecords())
        {
            ExpDicTypeBizBo bo=ConventObjectUtil.conventObject(po,ExpDicTypeBizBo.class);
            bizBoList.add(bo);
        }
        bizBo.setRows(bizBoList);
        return bizBo;
    }

    @Override
    public ExpDicTypeBizBo queryDicTypeById(long id) throws BizException {
        ExpDicTypePo po= iExpDicTypeDao.selectPoById(id);
        ExpDicTypeBizBo bo=ConventObjectUtil.conventObject(po,ExpDicTypeBizBo.class);
        return bo;
    }

    @Override
    public List<ExpDicTypeBizBo> queryAllDicType() {
        List<ExpDicTypePo> pageData=iExpDicTypeDao.selectAllDicType();

        List<ExpDicTypeBizBo> bizBoList=new ArrayList<>();
        for(ExpDicTypePo po:pageData)
        {
            ExpDicTypeBizBo bo=ConventObjectUtil.conventObject(po,ExpDicTypeBizBo.class);
            bizBoList.add(bo);
        }
        return bizBoList;
    }

    @Override
    public boolean deleteById(long id) throws BizException {
        return iExpDicTypeDao.deleteById(id)>0;
    }

    @Override
    public ExpDicTypeBizBo selectPoByKey(String key) {
        ExpDicTypePo po=iExpDicTypeDao.selectPoByKey(key);
        if(po!=null)
            return ConventObjectUtil.conventObject(po,ExpDicTypeBizBo.class);
        return null;
    }
}
