package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.bbfee.*;

/**
 * Created by Administrator on 2020/2/6.
 */
public interface IApiBbFeeControllerService {


    BbFeeOutputDto createOrEditBbFee(BbFeeInputDto inputDto) throws BizException;

    BbFeeOutputDto getBbFeeInfo(BbFeeInputDto inputDto)throws BizException;

    BbFeeListOutputDto getBbFeePageData(BbFeeListInputDto inputDto)throws BizException;

    DeleteOutputDto deleteBbFee(DeleteInputDto inputDto) throws BizException;
}
