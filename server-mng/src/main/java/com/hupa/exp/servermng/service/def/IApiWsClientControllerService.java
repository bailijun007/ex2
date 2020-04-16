package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientInputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientListOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientOutputDto;


public interface IApiWsClientControllerService {

    WsClientListOutputDto getWsClientConfigPageData(WsClientInputDto inputDto) throws BizException;

    WsClientOutputDto createOrEdit(WsClientInputDto inputDto) throws BizException;

    WsClientOutputDto getWsClientConfig(WsClientInputDto inputDto) throws BizException;

    DeleteOutputDto delete(DeleteInputDto inputDto) throws BizException;

}
