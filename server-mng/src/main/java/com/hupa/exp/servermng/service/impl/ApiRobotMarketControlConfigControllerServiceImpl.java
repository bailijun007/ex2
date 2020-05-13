package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IRobotMarketControlConfigDao;
import com.hupa.exp.daomysql.dao.expv2.def.IRobotMarketRootConfigDao;
import com.hupa.exp.daomysql.dao.expv2.mapper.IExpUserMapper;
import com.hupa.exp.daomysql.entity.po.expv2.RobotMarketControlConfigPo;
import com.hupa.exp.daomysql.entity.po.expv2.RobotMarketRootConfigPo;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigInfo;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketcontrolconfig.RobotMarketControlConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiRobotMarketControlConfigControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/5/13
 */
@Service
public class ApiRobotMarketControlConfigControllerServiceImpl implements IApiRobotMarketControlConfigControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiRobotMarketControlConfigControllerServiceImpl.class);
    @Autowired
    private IRobotMarketControlConfigDao controlConfigDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private IExpUserMapper iExpUserMapper;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public RobotMarketControlConfigListOutputDto pageQuery(RobotMarketControlConfigInputDto inputDto) throws BizException {
        RobotMarketControlConfigListOutputDto outputDto = new RobotMarketControlConfigListOutputDto();
        try {
            String asset = inputDto.getAsset();
            String symbol = inputDto.getSymbol();
            Integer expAreaType = inputDto.getExpAreaType();
            List<RobotMarketControlConfigInfo> boList = new ArrayList();
            IPage<RobotMarketControlConfigPo> list = controlConfigDao.selectAllPoByPage(asset, symbol, expAreaType, inputDto.getPageNo(), inputDto.getPageSize());

            for (RobotMarketControlConfigPo po : list.getRecords()) {
                RobotMarketControlConfigInfo bo = ConventObjectUtil.conventObject(po, RobotMarketControlConfigInfo.class);
                boList.add(bo);
            }
            outputDto.setRows(boList);
            outputDto.setTotal(list.getTotal());
        } catch (Exception e) {
            logger.info("RobotMarketControlConfigListOutputDto pageQuery exception: " + e.getMessage());
        }

        return outputDto;
    }

    @Override
    public RobotMarketControlConfigOutputDto queryById(Long id) throws BizException {
        RobotMarketControlConfigOutputDto outputDto = new RobotMarketControlConfigOutputDto();
        RobotMarketControlConfigPo po = controlConfigDao.selectPoById(id);
        if (null != po) {
            BeanUtils.copyProperties(po, outputDto);
        }
        return outputDto;
    }

    @Override
    public RobotMarketControlConfigOutputDto edit(RobotMarketControlConfigInputDto inputDto) throws BizException {
        RobotMarketControlConfigPo beforeBo = controlConfigDao.selectPoById(inputDto.getId());
        if (beforeBo == null) {
            throw new MngException(MngExceptionCode.DATA_NOT_EXIST_ERROR);
        }
        RobotMarketControlConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketControlConfigPo.class);
        bo.setMtime(System.currentTimeMillis());
        controlConfigDao.updateById(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(bo));
        RobotMarketControlConfigOutputDto outputDto = new RobotMarketControlConfigOutputDto();
        return outputDto;
    }

    @Override
    public RobotMarketControlConfigOutputDto create(RobotMarketControlConfigInputDto inputDto) throws BizException {
        RobotMarketControlConfigPo selectOnePo = controlConfigDao.selectOnePo(inputDto.getAsset(), inputDto.getSymbol(), inputDto.getExpAreaType());
        if (null != selectOnePo) {
            throw new MngException(MngExceptionCode.DATA_EXIST_ERROR);
        }
        RobotMarketControlConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketControlConfigPo.class);
        long time = System.currentTimeMillis();
        bo.setCtime(time);
        bo.setMtime(time);
        controlConfigDao.insert(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Insert.toString(), JsonUtil.toJsonString(bo), "");
        RobotMarketControlConfigOutputDto outputDto = new RobotMarketControlConfigOutputDto();
        return outputDto;
    }
}
