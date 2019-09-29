package com.hupa.exp.bizother.service.dictype.def;

import com.hupa.exp.bizother.entity.ExpDicBizBo;
import com.hupa.exp.bizother.entity.ExpDicListBizBo;
import com.hupa.exp.bizother.entity.ExpDicTypeBizBo;
import com.hupa.exp.bizother.entity.ExpDicTypeListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.util.List;

public interface IDicTypeService {
    long createDicType(ExpDicTypeBizBo expDicTypeBizBo) throws BizException;
    long editDicType(ExpDicTypeBizBo expDicTypeBizBo) throws BizException;

    ExpDicTypeListBizBo queryDicTypeList(long currentPage, long pageSize)throws BizException;

    ExpDicTypeBizBo queryDicTypeById(long id)throws BizException;

    List<ExpDicTypeBizBo> queryAllDicType();

    boolean deleteById(long id) throws BizException;

    ExpDicTypeBizBo selectPoByKey(String key);
}
