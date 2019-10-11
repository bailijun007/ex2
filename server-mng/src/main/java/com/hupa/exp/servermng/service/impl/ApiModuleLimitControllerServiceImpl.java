package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitBizBo;
import com.hupa.exp.bizother.entity.modulelimit.ExpModuleLimitListBizBo;
import com.hupa.exp.bizother.service.modulelimit.def.IModuleLimitService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.modulelimit.*;
import com.hupa.exp.servermng.service.def.IApiModuleLimitControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiModuleLimitControllerServiceImpl implements IApiModuleLimitControllerService {
    @Autowired
    private IModuleLimitService iModuleLimitService;

    @Override
    public ModuleLimitOutputDto createOrEditModuleLimit(ModuleLimitInputDto inputDto) throws BizException {
        ExpModuleLimitBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpModuleLimitBizBo.class);
        if(inputDto.getId()>0)
            iModuleLimitService.editModuleLimit(bo);
        else
            iModuleLimitService.createModuleLimit(bo);
        ModuleLimitOutputDto outputDto=new ModuleLimitOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public GetModuleLimitOutputDto getModuleLimitById(GetModuleLimitInputDto inputDto) throws BizException {

        ExpModuleLimitBizBo bo= iModuleLimitService.queryModuleLimitById(inputDto.getId());
        GetModuleLimitOutputDto outputDto=ConventObjectUtil.conventObject(bo,GetModuleLimitOutputDto.class);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ModuleLimitListOutputDto getModuleLimitList(ModuleLimitListInputDto inputDto) throws BizException {
        ModuleLimitListOutputDto outputDto=new ModuleLimitListOutputDto();
        ExpModuleLimitListBizBo listBizBo=iModuleLimitService.queryModuleLimitLsit(inputDto.getCurrentPage(),
                inputDto.getPageSize());
        outputDto.setPageData(listBizBo);
        return outputDto;
    }
}
