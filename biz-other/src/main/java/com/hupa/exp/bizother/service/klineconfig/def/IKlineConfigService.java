package com.hupa.exp.bizother.service.klineconfig.def;

import com.hupa.exp.bizother.entity.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.ExpKlineConfigListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface IKlineConfigService {
    long createKlineConfig(ExpKlineConfigBizBo bo) throws BizException;

    long editKlineConfig(ExpKlineConfigBizBo bo) throws BizException;

    ExpKlineConfigListBizBo querySmsTempList(long currentPage, long pageSize)throws BizException;

    ExpKlineConfigBizBo querySmsTempById(long id)throws BizException;
}
