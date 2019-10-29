package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.PcPosLevelBizBo;
import com.hupa.exp.bizother.entity.account.PosLevelPageListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IPosLevelBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.servermng.entity.poslevel.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPosLevelControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiPosLevelControllerServiceImpl implements IApiPosLevelControllerService {
    @Autowired
    private IPosLevelBiz iPosLevelBiz;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public PosLevelOutputDto createOrEditPosLevel(PosLevelInputDto inputDto) throws BizException {
        PcPosLevelBizBo bo= ConventObjectUtil.conventObject(inputDto,PcPosLevelBizBo.class);
        if(inputDto.getId()>0)
        {
            PcPosLevelBizBo beforeBo= iPosLevelBiz.queryPosLevelById(bo.getId());
            bo.setMtime(System.currentTimeMillis());
            iPosLevelBiz.editPosLevel(bo);
            ExpUserBizBo user=sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.PcFee.toString(), OperationType.Insert.toString(),
                    JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(bo));
        }
        else
        {
            bo.setMtime(System.currentTimeMillis());
            bo.setCtime(System.currentTimeMillis());
            iPosLevelBiz.createPosLevel(bo);
        }

        PosLevelOutputDto outputDto=new PosLevelOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PosLevelInfoOutputDto getPosLevel(PosLevelInfoInputDto inputDto) throws BizException {
        PcPosLevelBizBo bo= iPosLevelBiz.queryPosLevelById(inputDto.getId());
        PosLevelInfoOutputDto outputDto=ConventObjectUtil.conventObject(bo,PosLevelInfoOutputDto.class);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PosLevelListOutputDto getPosLevelList(PosLevelListInputDto inputDto) throws BizException {
        PosLevelListOutputDto outputDto=new PosLevelListOutputDto();
        PosLevelPageListBizBo listBizBo= iPosLevelBiz.queryPosLevelList(inputDto.getAsset(),inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<PosLevelInfoOutputDto> outputDtoList=new ArrayList<>();
        for(PcPosLevelBizBo bo:listBizBo.getRows())
        {
            PosLevelInfoOutputDto info=new PosLevelInfoOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setAsset(bo.getAsset());
            info.setSymbol(bo.getSymbol());
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
