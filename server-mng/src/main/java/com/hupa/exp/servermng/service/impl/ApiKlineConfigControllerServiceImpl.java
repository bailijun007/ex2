package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.ExpKlineConfigBizBo;
import com.hupa.exp.bizother.entity.ExpKlineConfigListBizBo;
import com.hupa.exp.bizother.service.klineconfig.def.IKlineConfigService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.klineconfig.*;
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
        ExpKlineConfigBizBo bo=configService.querySmsTempById(inputDto.getId());
        KlineConfigInfoOutputDto outputDto=new KlineConfigInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setPair(bo.getPair());
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
        configService.editKlineConfig(bo);
        KlineConfigOutputDto outputDto=new KlineConfigOutputDto();
        return outputDto;
    }

    @Override
    public KlineConfigListOutputDto queryKlineConfigList(KlineConfigListInputDto inputDto) throws BizException {
        KlineConfigListOutputDto outputDto=new KlineConfigListOutputDto();
        ExpKlineConfigListBizBo boList=configService.querySmsTempList(inputDto.getCurrentPage(),inputDto.getPageSize());
        List<KlineConfigListOutputPage> pageList=new ArrayList<>();
        for(ExpKlineConfigBizBo bo:boList.getRows())
        {
            KlineConfigListOutputPage po=new KlineConfigListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setPair(bo.getPair());
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
