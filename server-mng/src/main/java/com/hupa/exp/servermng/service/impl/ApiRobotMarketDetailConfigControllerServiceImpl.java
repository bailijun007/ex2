package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IRobotMarketDetailConfigDao;
import com.hupa.exp.daomysql.dao.expv2.mapper.IExpUserMapper;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.daomysql.entity.po.expv2.RobotMarketDetailConfigPo;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigInfo;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigInputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigListOutputDto;
import com.hupa.exp.servermng.entity.robotmarketdetailconfig.RobotMarketDetailConfigOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiRobotMarketDetailConfigControllerService;
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
public class ApiRobotMarketDetailConfigControllerServiceImpl implements IApiRobotMarketDetailConfigControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiRobotMarketDetailConfigControllerServiceImpl.class);
    @Autowired
    private IRobotMarketDetailConfigDao detailConfigDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpUserMapper iExpUserMapper;

    @Override
    public RobotMarketDetailConfigListOutputDto pageQuery(RobotMarketDetailConfigInputDto inputDto) throws BizException {
        RobotMarketDetailConfigListOutputDto outputDto = new RobotMarketDetailConfigListOutputDto();
        try {
            Integer expAreaType = inputDto.getExpAreaType();
            List<RobotMarketDetailConfigInfo> boList = new ArrayList();
            IPage<RobotMarketDetailConfigPo> list = detailConfigDao.selectAllPoByPage(inputDto.getAsset(), inputDto.getSymbol(), expAreaType, inputDto.getPageNo(), inputDto.getPageSize());

            for (RobotMarketDetailConfigPo po : list.getRecords()) {
                RobotMarketDetailConfigInfo bo = ConventObjectUtil.conventObject(po, RobotMarketDetailConfigInfo.class);
                 ExpUserPo expUserPo = iExpUserMapper.selectById(bo.getAskUserId());
                if(null!=expUserPo){
                    bo.setAskUserName(expUserPo.getPhone());
                }
                ExpUserPo expUserPo2 = iExpUserMapper.selectById(bo.getBidUserId());
                if(null!=expUserPo2){
                    bo.setBidUserName(expUserPo.getPhone());
                }
                boList.add(bo);
            }
            outputDto.setRows(boList);
            outputDto.setTotal(list.getTotal());
        } catch (Exception e) {
            logger.info("IApiRobotMarketDetailConfigControllerServiceImpl pageQuery exception: " + e.getMessage());
        }

        return outputDto;
    }

    @Override
    public RobotMarketDetailConfigOutputDto queryById(Long id) throws BizException {
        RobotMarketDetailConfigOutputDto outputDto = new RobotMarketDetailConfigOutputDto();
        RobotMarketDetailConfigPo po = detailConfigDao.selectPoById(id);
        if (null != po) {
            BeanUtils.copyProperties(po, outputDto);
        }
        return outputDto;
    }

    @Override
    public RobotMarketDetailConfigOutputDto edit(RobotMarketDetailConfigInputDto inputDto) throws BizException {
        RobotMarketDetailConfigPo beforeBo = detailConfigDao.selectPoById(inputDto.getId());
        if (beforeBo == null) {
            throw new MngException(MngExceptionCode.DATA_NOT_EXIST_ERROR);
        }
        RobotMarketDetailConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketDetailConfigPo.class);
        bo.setMtime(System.currentTimeMillis());
        detailConfigDao.updateById(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(bo));
        RobotMarketDetailConfigOutputDto outputDto = new RobotMarketDetailConfigOutputDto();
        return outputDto;
    }

    @Override
    public RobotMarketDetailConfigOutputDto create(RobotMarketDetailConfigInputDto inputDto) throws BizException {
        RobotMarketDetailConfigPo selectOnePo = detailConfigDao.selectOnePo(inputDto.getAsset(), inputDto.getSymbol(), inputDto.getExpAreaType());
        if (selectOnePo != null) {
            throw new MngException(MngExceptionCode.DATA_EXIST_ERROR);
        }
        RobotMarketDetailConfigPo bo = ConventObjectUtil.conventObject(inputDto, RobotMarketDetailConfigPo.class);
        long time = System.currentTimeMillis();
        bo.setCtime(time);
        bo.setMtime(time);
        detailConfigDao.insert(bo);
        //添加操作日志
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Insert.toString(), JsonUtil.toJsonString(bo), "");
        RobotMarketDetailConfigOutputDto outputDto = new RobotMarketDetailConfigOutputDto();
        return outputDto;
    }
}
