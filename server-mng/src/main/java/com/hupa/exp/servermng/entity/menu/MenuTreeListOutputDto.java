package com.hupa.exp.servermng.entity.menu;

import com.hupa.exp.bizother.entity.ExpMenuTreeBizBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;

public class MenuTreeListOutputDto extends BaseOutputDto{

    public List<ExpMenuTreeBizBo> getTreeBoList() {
        return treeBoList;
    }

    public void setTreeBoList(List<ExpMenuTreeBizBo> treeBoList) {
        this.treeBoList = treeBoList;
    }


    private List<ExpMenuTreeBizBo> treeBoList;
}
