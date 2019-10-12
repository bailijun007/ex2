package com.hupa.exp.bizother.service.menu.def;

import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.menu.ExpMenuBizBo;
import com.hupa.exp.bizother.entity.menu.ExpMenuListBizBo;
import com.hupa.exp.bizother.entity.menu.ExpMenuTreeBizBo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpMenuPo;

import java.util.List;

public interface IMenuService {
    long createMenu(ExpMenuBizBo menuPo) throws ValidateException;

    List<ExpMenuTreeBizBo> queryTreeView(Integer pid);

    List<ExpMenuBizBo> queryArrTreeView(Integer pid);

    ExpMenuBizBo queryPoById(long id);

    long editById(ExpMenuBizBo expMenuPo) throws ValidateException;

    String queryHtmlTreeView(List<Integer> usermenus);
}
