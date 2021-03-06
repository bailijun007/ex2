package com.hupa.exp.bizother.service.dic.def;

import com.hupa.exp.bizother.entity.dic.ExpDicBizBo;
import com.hupa.exp.bizother.entity.dic.ExpDicListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.util.List;

public interface IDicService {

    long createDic(ExpDicBizBo expUserBo) throws BizException;
    long editDic(ExpDicBizBo expUserBo) throws BizException;

    ExpDicListBizBo queryDicList(Integer type,long currentPage, long pageSize)throws BizException;

    List<ExpDicBizBo> queryDicListByType(int type)throws BizException;

    List<ExpDicBizBo> queryParentDic()throws BizException;

    ExpDicBizBo queryDicById(long id)throws BizException;

    boolean deleteById(long id)throws BizException;

    ExpDicBizBo queryDicByKey(String id)throws BizException;

}
