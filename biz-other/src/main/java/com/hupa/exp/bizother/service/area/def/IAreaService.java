package com.hupa.exp.bizother.service.area.def;

import com.hupa.exp.bizother.entity.ExpAreaBizBo;
import com.hupa.exp.bizother.entity.ExpAreaListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.util.List;

public interface IAreaService {
    long createArea(ExpAreaBizBo expUserBo) throws BizException;
    long editArea(ExpAreaBizBo expUserBo) throws BizException;

    ExpAreaListBizBo queryAreaList(long currentPage, long pageSize)throws BizException;

    List<ExpAreaBizBo> queryEnableAreaList()throws BizException;

    ExpAreaBizBo queryAreaById(long id)throws BizException;
}
