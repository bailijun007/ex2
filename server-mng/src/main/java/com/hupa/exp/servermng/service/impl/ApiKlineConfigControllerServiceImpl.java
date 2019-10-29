package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.klineconfig.ExpKlineConfigListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.klineconfig.def.IKlineConfigService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.klineconfig.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiKlineConfigControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiKlineConfigControllerServiceImpl implements IApiKlineConfigControllerService {

    @Autowired
    IKlineConfigService configService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpOperationLogService logService;

    @Override
    public KlineConfigOutputDto createKlineConfig(KlineConfigInputDto inputDto) throws BizException {

        ExpKlineConfigBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpKlineConfigBizBo.class);
        long id= configService.createKlineConfig(bo);
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        outputDto.setId(id);
        return outputDto;
    }

    @Override
    public KlineConfigInfoOutputDto queryKlineConfigById(KlineConfigInfoInputDto inputDto) throws BizException {
        ExpKlineConfigBizBo bo=configService.queryKlineConfigById(inputDto.getId());
        KlineConfigInfoOutputDto outputDto=new KlineConfigInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setSymbol(bo.getSymbol().equals("null")?"":bo.getSymbol());
        outputDto.setAsset(bo.getAsset().equals("null")?"":bo.getAsset());
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setKlineInterval(bo.getKlineInterval());
        outputDto.setStatTime(String.valueOf(bo.getStatTime()));
        outputDto.setEndTime(String.valueOf(bo.getEndTime()));
        outputDto.setType(String.valueOf(bo.getType()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public KlineConfigOutputDto editKlineConfig(KlineConfigInputDto inputDto) throws BizException {
        ExpKlineConfigBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpKlineConfigBizBo.class);
        ExpKlineConfigBizBo beforeBo= configService.queryKlineConfigById(bo.getId());
        configService.editKlineConfig(bo);

        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.EarningRate.toString(), OperationType.Delete.toString(),
                JSON.toJSONString(beforeBo), JSON.toJSONString(bo));
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        return outputDto;
    }

    @Override
    public KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws BizException {
        KlineConfigListOutputDto outputDto=new KlineConfigListOutputDto();
        ExpKlineConfigListBizBo boList=configService.queryKlineConfigList(inputDto.getCurrentPage(),inputDto.getPageSize());
        List<KlineConfigListOutputPage> pageList=new ArrayList<>();
        for(ExpKlineConfigBizBo bo:boList.getRows())
        {
            KlineConfigListOutputPage po=new KlineConfigListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol().equals("null")?"":bo.getSymbol());
            po.setAsset(bo.getAsset().equals("null")?"":bo.getAsset());
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setKlineInterval(bo.getKlineInterval());
            po.setStatTime(String.valueOf(bo.getStatTime()));
            po.setEndTime(String.valueOf(bo.getEndTime()));
            po.setType(String.valueOf(bo.getType()));
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(boList.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }
}
