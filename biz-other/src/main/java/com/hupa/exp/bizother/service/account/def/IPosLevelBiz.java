package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.PcPosLevelBizBo;
import com.hupa.exp.bizother.entity.account.PosLevelPageListBizBo;
import com.hupa.exp.common.exception.BizException;

import java.io.Serializable;

public interface IPosLevelBiz {

    long createPosLevel(PcPosLevelBizBo expUserBo) throws BizException;
    long editPosLevel(PcPosLevelBizBo expUserBo) throws BizException;

    PosLevelPageListBizBo queryPosLevelList(String asset,String symbol, long currentPage, long pageSize)throws BizException;

    PcPosLevelBizBo queryPosLevelById(Serializable id)throws BizException;

    boolean checkHasStoringLevel(String asset,String symbol, String gear);


}
