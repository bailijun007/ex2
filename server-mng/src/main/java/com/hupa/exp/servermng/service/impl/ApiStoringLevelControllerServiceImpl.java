package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.account.PcStoringLevelBizBo;
import com.hupa.exp.bizother.entity.account.StoringLevelPageListBizBo;
import com.hupa.exp.bizother.service.account.def.IStoringLevelBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.servermng.entity.storinglevel.*;
import com.hupa.exp.servermng.service.def.IApiStoringLevelControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiStoringLevelControllerServiceImpl implements IApiStoringLevelControllerService {
    @Autowired
    private IStoringLevelBiz iStoringLevelBiz;

    @Override
    public StoringLevelOutputDto createOrEditStoringLevel(StoringLevelInputDto inputDto) throws BizException {
        PcStoringLevelBizBo bo= ConventObjectUtil.conventObject(inputDto,PcStoringLevelBizBo.class);
        if(inputDto.getId()>0)
        {
            bo.setMtime(System.currentTimeMillis());
            iStoringLevelBiz.editStoringLevel(bo);
        }
        else
        {
            bo.setMtime(System.currentTimeMillis());
            bo.setCtime(System.currentTimeMillis());
            iStoringLevelBiz.createStoringLevel(bo);
        }

        StoringLevelOutputDto outputDto=new StoringLevelOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public StoringLevelInfoOutputDto getStoringLevel(StoringLevelInfoInputDto inputDto) throws BizException {
        PcStoringLevelBizBo bo=iStoringLevelBiz.queryStoringLevelById(inputDto.getId());
        StoringLevelInfoOutputDto outputDto=ConventObjectUtil.conventObject(bo,StoringLevelInfoOutputDto.class);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public StoringLevelListOutputDto getStoringLevelList(StoringLevelListInputDto inputDto) throws BizException {
        StoringLevelListOutputDto outputDto=new StoringLevelListOutputDto();
        StoringLevelPageListBizBo listBizBo=iStoringLevelBiz.queryStoringLevelList(inputDto.getPair(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<StoringLevelInfoOutputDto> outputDtoList=new ArrayList<>();
        for(PcStoringLevelBizBo bo:listBizBo.getRows())
        {
            StoringLevelInfoOutputDto info=new StoringLevelInfoOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setPair(bo.getPair());
            info.setGear(String.valueOf(bo.getGear()));
            info.setMinAmt(String.valueOf(bo.getMinAmt()));
            info.setMaxAmt(String.valueOf(bo.getMaxAmt()));
            info.setMaxLeverage(String.valueOf(bo.getMaxLeverage()));
            info.setPosMatinMarginRatio(DecimalUtil.toTrimLiteral(bo.getPosMatinMarginRatio()));
            outputDtoList.add(info);
        }
        outputDto.setRows(outputDtoList);
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTotal(listBizBo.getTotal());
        return outputDto;
    }
}
