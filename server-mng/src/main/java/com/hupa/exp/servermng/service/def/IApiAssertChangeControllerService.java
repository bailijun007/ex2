package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.assertchange.*;

public interface IApiAssertChangeControllerService {
    FundAssertChangeOutputDto getFundAssertChange(FundAssertChangeInputDto inputDto) throws BizException;

    FundAssertChangeListOutputDto getFundAssertChangeList(FundAssertChangeListInputDto inputDto)throws BizException;

    PcAssertChangeOutputDto getPcAssertChange(PcAssertChangeInputDto inputDto) throws BizException;

    PcAssertChangeListOutputDto getPcAssertChangeList(PcAssertChangeListInputDto inputDto)throws BizException;

}
