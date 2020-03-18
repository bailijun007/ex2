package com.hupa.exp.bizother.service.klineconfig.def;

import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface IKlineConfigService {
    long createKlineConfig(ExpKlineConfigBizBo bo) throws BizException;

    long editKlineConfig(ExpKlineConfigBizBo bo) throws BizException;

    ExpKlineConfigListBizBo queryKlineConfigList(Integer klineType, long currentPage, long pageSize)throws BizException;

    ExpKlineConfigBizBo queryKlineConfigById(long id)throws BizException;
}
