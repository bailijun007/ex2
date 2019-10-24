package com.hupa.exp.bizother.service.dic.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.dic.ExpDicBizBo;
import com.hupa.exp.bizother.entity.dic.ExpDicListBizBo;
import com.hupa.exp.bizother.service.dic.def.IDicService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DicServiceImpl implements IDicService {
    @Autowired
    private IExpDicDao iExpDicDao;
    @Override
    public long createDic(ExpDicBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpDicPo po= ConventObjectUtil.conventObject(expUserBo,ExpDicPo.class);
        return iExpDicDao.insert(po);
    }

    @Override
    public long editDic(ExpDicBizBo expUserBo) throws BizException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpDicPo po= ConventObjectUtil.conventObject(expUserBo,ExpDicPo.class);
        return iExpDicDao.updateById(po);
    }

    @Override
    public ExpDicListBizBo queryDicList(Integer type, long currentPage, long pageSize) throws BizException {

        ExpDicListBizBo listBizBo=new ExpDicListBizBo();
        List<ExpDicBizBo> boList=new ArrayList();
        IPage<ExpDicPo> list=iExpDicDao.selectDicList(type,currentPage,pageSize);
        for(ExpDicPo po:list.getRecords())
        {
            ExpDicBizBo bo=ConventObjectUtil.conventObject(po,ExpDicBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(list.getTotal());
        return listBizBo;
    }

    @Override
    public List<ExpDicBizBo> queryDicListByType(int type) throws BizException {
        List<ExpDicPo> list=iExpDicDao.selectDicListByParentId(type);
        List<ExpDicBizBo> boList=new ArrayList<>();
        for(ExpDicPo po:list)
        {
            ExpDicBizBo bo=ConventObjectUtil.conventObject(po,ExpDicBizBo.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public List<ExpDicBizBo> queryParentDic() throws BizException {
        List<ExpDicPo> list=iExpDicDao.selectParentDic();
        List<ExpDicBizBo> boList=new ArrayList<>();
        for(ExpDicPo po:list)
        {
            ExpDicBizBo bo=ConventObjectUtil.conventObject(po,ExpDicBizBo.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ExpDicBizBo queryDicById(long id) throws BizException {
        ExpDicPo po=iExpDicDao.selectPoById(id);
        if(po==null)
            return null;
        ExpDicBizBo bo=ConventObjectUtil.conventObject(po,ExpDicBizBo.class);
        return bo;
    }

    @Override
    public boolean deleteById(long id) throws BizException {
        return iExpDicDao.deleteById(id)>0;
    }

    @Override
    public ExpDicBizBo queryDicByKey(String key) throws BizException {
       ExpDicPo po= iExpDicDao.selectDicByKey(key);
       if(po==null)
           return null;
        ExpDicBizBo bo=ConventObjectUtil.conventObject(po,ExpDicBizBo.class);
        return bo;
    }
}
