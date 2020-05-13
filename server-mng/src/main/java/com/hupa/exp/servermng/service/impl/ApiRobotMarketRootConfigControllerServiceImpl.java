package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IRobotMarketRootConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.RobotMarketRootConfigPo;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInfo;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketrootconfig.RobotMarketRootConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiRobotMarketRootConfigControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/5/12
 */
@Service
public class ApiRobotMarketRootConfigControllerServiceImpl implements IApiRobotMarketRootConfigControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiRobotMarketRootConfigControllerServiceImpl.class);
    @Autowired
    private IRobotMarketRootConfigDao robotMarketRootConfigDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public RobotMarketRootConfigListOutputDto pageQuery(RobotMarketRootConfigInputDto inputDto) throws BizException {
        RobotMarketRootConfigListOutputDto outputDto = new RobotMarketRootConfigListOutputDto();
        try {
            Integer expAreaType = inputDto.getExpAreaType();
            List<RobotMarketRootConfigInfo> boList = new ArrayList();
            IPage<RobotMarketRootConfigPo> list = robotMarketRootConfigDao.selectAllPoByPage(expAreaType, inputDto.getPageNo(), inputDto.getPageSize());

            for (RobotMarketRootConfigPo po : list.getRecords()) {
                RobotMarketRootConfigInfo bo = ConventObjectUtil.conventObject(po, RobotMarketRootConfigInfo.class);
                boList.add(bo);
            }
            outputDto.setRows(boList);
            outputDto.setTotal(list.getTotal());
        } catch (Exception e) {
            logger.info("IApiRobotMarketRootConfigControllerServiceImpl pageQuery exception: " + e.getMessage());
        }

        return outputDto;
    }

    @Override
    public RobotMarketRootConfigOutputDto queryById(Long id) throws BizException {
        RobotMarketRootConfigOutputDto outputDto = new RobotMarketRootConfigOutputDto();
        RobotMarketRootConfigPo po = robotMarketRootConfigDao.selectPoById(id);
        if (null != po) {
            BeanUtils.copyProperties(po, outputDto);
        }
        return outputDto;
    }

    @Override
    public RobotMarketRootConfigOutputDto edit(RobotMarketRootConfigInputDto inputDto) throws BizException {
        RobotMarketRootConfigPo beforeBo = robotMarketRootConfigDao.selectPoById(inputDto.getId());
        if (beforeBo == null) {
            throw new MngException(MngExceptionCode.DATA_NOT_EXIST_ERROR);
        }
        RobotMarketRootConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketRootConfigPo.class);
        bo.setMtime(System.currentTimeMillis());
        robotMarketRootConfigDao.updateById(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(bo));
        RobotMarketRootConfigOutputDto outputDto = new RobotMarketRootConfigOutputDto();
        return outputDto;
    }

    @Override
    public RobotMarketRootConfigOutputDto create(RobotMarketRootConfigInputDto inputDto) throws BizException {
        RobotMarketRootConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketRootConfigPo.class);
        long time = System.currentTimeMillis();
        bo.setCtime(time);
        bo.setMtime(time);
        robotMarketRootConfigDao.insert(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Insert.toString(), JsonUtil.toJsonString(bo), "");
        RobotMarketRootConfigOutputDto outputDto = new RobotMarketRootConfigOutputDto();
        return outputDto;
    }
}
