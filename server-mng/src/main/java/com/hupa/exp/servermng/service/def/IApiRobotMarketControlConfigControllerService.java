package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;

/**
 * @author BaiLiJun  on 2020/5/12
 */
public interface IApiRobotMarketControlConfigControllerService {
    RobotMarketControlConfigListOutputDto pageQuery(RobotMarketControlConfigInputDto inputDto) throws BizException;

    RobotMarketControlConfigOutputDto queryById(Long id)throws BizException;

    RobotMarketControlConfigOutputDto edit(RobotMarketControlConfigInputDto inputDto) throws BizException;

    RobotMarketControlConfigOutputDto create(RobotMarketControlConfigInputDto inputDto)throws BizException;
}
