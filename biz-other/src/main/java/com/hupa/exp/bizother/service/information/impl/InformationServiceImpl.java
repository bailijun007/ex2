package com.hupa.exp.bizother.service.information.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.bizother.entity.ExpInformationBizBo;
import com.hupa.exp.bizother.entity.ExpInformationListBizBo;
import com.hupa.exp.bizother.entity.ExpInformationPageDataBizBo;
import com.hupa.exp.bizother.service.information.def.IInformationService;
import com.hupa.exp.daoex2.dao.expv2.def.IExpInformationDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpInformationPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformationServiceImpl implements IInformationService {

    @Autowired
    private IExpInformationDao informationDao;

    @Override
    public ExpInformationListBizBo getInformationListByType(int type, int limit) {
        List<ExpInformationPo> poList= informationDao.selectListByType(type,limit);
        ExpInformationListBizBo bizBo=new ExpInformationListBizBo();
        List<ExpInformationBizBo> bizBoList=new ArrayList<>();
        for(ExpInformationPo po:poList)
        {
            ExpInformationBizBo bo=ConventObjectUtil.conventObject(po,ExpInformationBizBo.class);
            bizBoList.add(bo);
        }
        bizBo.setRows(bizBoList);
        return bizBo;
    }

    @Override
    public ExpInformationPageDataBizBo getInformationPageData(Integer type,String title, int currentPage, int pageSize) {
        IPage<ExpInformationPo> pageData=informationDao.selectPageDataByType(type,title,currentPage,pageSize);
        ExpInformationPageDataBizBo bizBo=new ExpInformationPageDataBizBo();
        List<ExpInformationBizBo> bizBoList=new ArrayList<>();
        for(ExpInformationPo po:pageData.getRecords())
        {
            ExpInformationBizBo bo=ConventObjectUtil.conventObject(po,ExpInformationBizBo.class);
            bizBoList.add(bo);
        }
        bizBo.setRows(bizBoList);
        bizBo.setTotal(pageData.getTotal());
        return bizBo;
    }

    @Override
    public ExpInformationBizBo getInformationById(long id) {
        ExpInformationPo po=informationDao.selectPoById(id);
        if(po!=null)
        {
            ExpInformationBizBo bo= ConventObjectUtil.conventObject(po,ExpInformationBizBo.class);
            return bo;
        }
        return null;
    }

    @Override
    public long createInformation(ExpInformationBizBo bizBo) {
        ExpInformationPo po=ConventObjectUtil.conventObject(bizBo,ExpInformationPo.class);
        return informationDao.insert(po);
    }

    @Override
    public int editInformation(ExpInformationBizBo bizBo) {
        ExpInformationPo po=ConventObjectUtil.conventObject(bizBo,ExpInformationPo.class);
        return informationDao.updateById(po);
    }

    @Override
    public int deleteInformation(long id) {
        return informationDao.deleteById(id);
    }
}
