package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigInputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigListOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigOutputDto;


public interface IApiWsExpConfigControllerService {

    WsExpConfigOutputDto createOrEdit(WsExpConfigInputDto inputDto) throws BizException;

    WsExpConfigListOutputDto getWsExpConfigPageData(WsExpConfigInputDto inputDto) throws BizException;

    WsExpConfigOutputDto getWsExpConfig(WsExpConfigInputDto inputDto) throws BizException;

    DeleteOutputDto delete(DeleteInputDto inputDto) throws BizException;

}
