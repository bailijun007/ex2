package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigOutputDto;

/**
 * @author BaiLiJun  on 2020/5/12
 */
public interface IApiRobotMarketDetailConfigControllerService {
    RobotMarketDetailConfigListOutputDto pageQuery(RobotMarketDetailConfigInputDto inputDto) throws BizException;

    RobotMarketDetailConfigOutputDto queryById(Long id)throws BizException;

    RobotMarketDetailConfigOutputDto edit(RobotMarketDetailConfigInputDto inputDto) throws BizException;

    RobotMarketDetailConfigOutputDto create(RobotMarketDetailConfigInputDto inputDto)throws BizException;
}
