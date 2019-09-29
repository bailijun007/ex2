package com.hupa.exp.bizother.service.sms.def;

import com.hupa.exp.bizother.entity.ExpSmsListBizBo;
import com.hupa.exp.bizother.entity.ExpSmsTempBizBo;
import com.hupa.exp.common.exception.BizException;

public interface ISmsTempBiz {
    long createUser(ExpSmsTempBizBo expUserBo) throws BizException;
    long editUser(ExpSmsTempBizBo expUserBo) throws BizException;

    ExpSmsListBizBo querySmsTempList(long currentPage, long pageSize)throws BizException;

    ExpSmsTempBizBo querySmsTempById(long id)throws BizException;

    ExpSmsTempBizBo querySmsTempByCode(String code)throws BizException;

}
