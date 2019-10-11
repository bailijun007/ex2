package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.PcStoringLevelBizBo;
import com.hupa.exp.bizother.entity.account.StoringLevelPageListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.io.Serializable;

public interface IStoringLevelBiz {

    long createStoringLevel(PcStoringLevelBizBo expUserBo) throws BizException;
    long editStoringLevel(PcStoringLevelBizBo expUserBo) throws BizException;

    StoringLevelPageListBizBo queryStoringLevelList(String pair, long currentPage, long pageSize)throws BizException;

    PcStoringLevelBizBo queryStoringLevelById(Serializable id)throws BizException;

    boolean checkHasStoringLevel(String pair, String gear);


}
