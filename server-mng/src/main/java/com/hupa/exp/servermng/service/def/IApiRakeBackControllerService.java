package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.rakeback.RakeBackInputDto;
import com.hupa.exp.servermng.entity.rakeback.RakeBackListOutputDto;

/**
 * Created by Administrator on 2020/3/30.
 */
public interface IApiRakeBackControllerService {

    RakeBackListOutputDto getPosPageByParam(RakeBackInputDto inputDto) throws BizException;

}
