package com.hupa.exp.bizother.service.menu.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.menu.ExpMenuBizBo;
import com.hupa.exp.bizother.entity.menu.ExpMenuListBizBo;
import com.hupa.exp.bizother.entity.menu.ExpMenuTreeBizBo;
import com.hupa.exp.bizother.service.menu.def.IMenuService;
import com.hupa.exp.daomysql.dao.expv2.def.IExpMenuDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpListMenuTree;
import com.hupa.exp.daomysql.entity.po.expv2.ExpMenuPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpMenuTree;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    IExpMenuDao iExpMenuDao;
    @Override
    public long createMenu(ExpMenuBizBo menuBo) throws ValidateException {
        if(menuBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpMenuPo po=ConventObjectUtil.conventObject(menuBo,ExpMenuPo.class);
        return iExpMenuDao.insert(po);
    }

    @Override
    public List<ExpMenuPo> queryMenuLsitByParm(boolean enable) {
        return null;
    }

    @Override
    public List<ExpMenuPo> queryMenuLsit() {
        return iExpMenuDao.selectList();
    }

    @Override
    public List<ExpMenuPo> queryMenuLsitByParent(Integer parendId, boolean enable) {
        return null;
    }

    @Override
    public List<ExpMenuTreeBizBo> queryTreeView(Integer pid) {
        List<ExpMenuTree> menuTrees=iExpMenuDao.selectTreeView(pid);
        List<ExpMenuTreeBizBo> boList=new ArrayList<>();
        for(ExpMenuTree po:menuTrees)
        {
            ExpMenuTreeBizBo bo= ConventObjectUtil.conventObject(po,ExpMenuTreeBizBo.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public List<ExpMenuBizBo> queryArrTreeView(Integer pid) {
        List<ExpListMenuTree> listMenuTrees=iExpMenuDao.selectArrTreeView(pid);
        List<ExpMenuBizBo> boList=new ArrayList<>();
        for(ExpListMenuTree po:listMenuTrees)
        {
            ExpMenuBizBo bo= ConventObjectUtil.conventObject(po,ExpMenuBizBo.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ExpMenuBizBo queryPoById(long id) {
        ExpMenuPo po=iExpMenuDao.selectPoById(id);
        if(po==null)
            return null;
        ExpMenuBizBo bo= ConventObjectUtil.conventObject(po,ExpMenuBizBo.class);
        return bo;
    }

    @Override
    public ExpMenuListBizBo pagePosByParam(long parentId, int currentPage, int pageSize) {
        IPage<ExpMenuPo> menuPos=iExpMenuDao.pagePosByParam(parentId,currentPage,pageSize);
        ExpMenuListBizBo listBizBo=new ExpMenuListBizBo();
        listBizBo.setTotal(menuPos.getTotal());
        List<ExpMenuBizBo> boList=new ArrayList<>();
        for(ExpMenuPo po:menuPos.getRecords())
        {
            ExpMenuBizBo bo=ConventObjectUtil.conventObject(po,ExpMenuBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        return listBizBo;
    }

    @Override
    public long editById(ExpMenuBizBo menuBo) throws ValidateException {
        if(menuBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpMenuPo po=ConventObjectUtil.conventObject(menuBo,ExpMenuPo.class);
        return iExpMenuDao.updateById(po);
    }

    @Override
    public String queryHtmlTreeView(List<Integer> usermenus) {
        return iExpMenuDao.selectHtmlTreeView(usermenus);
    }


}
