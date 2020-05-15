package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;

/**
 * @author BaiLiJun  on 2020/5/12
 */
public interface IApiRobotMarketRootConfigControllerService {
    RobotMarketRootConfigListOutputDto pageQuery(RobotMarketRootConfigInputDto inputDto) throws BizException;

    RobotMarketRootConfigOutputDto queryById(Long id)throws BizException;

    RobotMarketRootConfigOutputDto edit(RobotMarketRootConfigInputDto inputDto) throws BizException;

    RobotMarketRootConfigOutputDto create(RobotMarketRootConfigInputDto inputDto)throws BizException;
}
