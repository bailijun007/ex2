package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigOutputDto;

/**
 * Created by Administrator on 2020/1/13.
 */
public interface IApiC2cConfigControllerService {

    /**
     * 创建C2C配置
     * @param inputDto
     * @return
     * @throws BizException
     */
    C2cConfigOutputDto createC2cConfig(C2cConfigInputDto inputDto) throws BizException;

    /**
     * 修改C2C配置
     * @param inputDto
     * @return
     * @throws BizException
     */
    C2cConfigOutputDto editC2cConfig(C2cConfigInputDto inputDto) throws BizException;

    /**
     * 查询C2C配置
     * @param inputDto
     * @return
     * @throws BizException
     */
    C2cConfigOutputDto getC2cConfigById(C2cConfigInputDto inputDto) throws BizException;

    /**
     * 查询C2C配置列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    C2cConfigListOutputDto findC2cConfigList(C2cConfigListInputDto inputDto) throws BizException;

    /**
     * 删除C2C配置，可批量删除
     * @param inputDto
     * @return
     * @throws BizException
     */
    public DeleteOutputDto deletesConfigList(DeleteInputDto inputDto) throws BizException;


}
