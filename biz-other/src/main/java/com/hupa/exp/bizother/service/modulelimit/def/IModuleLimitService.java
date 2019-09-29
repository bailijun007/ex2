package com.hupa.exp.bizother.service.modulelimit.def;

import com.hupa.exp.bizother.entity.ExpModuleLimitBizBo;
import com.hupa.exp.bizother.entity.ExpModuleLimitListBizBo;
import com.hupa.exp.bizother.entity.ExpRoleBizBo;
import com.hupa.exp.common.exception.BizException;

import java.io.Serializable;
import java.util.List;

public interface IModuleLimitService {
    long createModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo)throws BizException;

    long editModuleLimit(ExpModuleLimitBizBo moduleLimitBizBo)throws BizException;

    ExpModuleLimitListBizBo queryModuleLimitLsit(long currentPage, long pageSize)throws BizException;

    ExpModuleLimitBizBo queryModuleLimitById(long id)throws BizException;
}
