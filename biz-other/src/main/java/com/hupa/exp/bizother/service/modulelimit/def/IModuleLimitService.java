package com.hupa.exp.bizother.service.modulelimit.def;

import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitBizBo;
import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface IModuleLimitService {
    long createModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo)throws BizException;

    long editModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo)throws BizException;

    ExpModuleLimitListBizBo queryModuleLimitLsit(long currentPage, long pageSize)throws BizException;

    ExpModuleLimitBizBo queryModuleLimitById(long id)throws BizException;
}
