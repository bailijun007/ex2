package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.bborder.BbOrderPageInputDto;
import com.hupa.exp.servermng.entity.bborder.BbOrderPageOutputDto;

/**
 * Created by Administrator on 2020/2/15.
 */
public interface IApiBbOrderControllerService {

    BbOrderPageOutputDto getBbOrderPageData(BbOrderPageInputDto inputDto) throws BizException;

}
