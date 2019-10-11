package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizaccount.entity.PcFeeBizBo;
import com.hupa.exp.bizaccount.entity.PcFeeListBizBo;
import com.hupa.exp.bizaccount.service.def.IPcFeeBiz;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.servermng.entity.pcfee.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPcFeeControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IApiPcFeeControllerServiceImpl implements IApiPcFeeControllerService {
    @Autowired
    private IPcFeeBiz iPcFeeBiz;
    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public PcFeeOutputDto createOrEditPcFee(PcFeeInputDto inputDto) throws BizException {
        PcFeeBizBo bizBo= ConventObjectUtil.conventObject(inputDto,PcFeeBizBo.class);
        bizBo.setMtime(System.currentTimeMillis());
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        if(bizBo.getId()>0)
        {
            PcFeeBizBo before=iPcFeeBiz.getPcFeeById(inputDto.getId());
            iPcFeeBiz.editPcFee(bizBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.PcFee.toString(), OperationType.Update.toString(),
                    JsonUtil.toJsonString(before),JsonUtil.toJsonString(bizBo));
        }
        else
        {
            bizBo.setCtime(System.currentTimeMillis());
            iPcFeeBiz.createPcFee(bizBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.PcFee.toString(), OperationType.Insert.toString(),
                    JsonUtil.toJsonString(bizBo),JsonUtil.toJsonString(""));
        }
        PcFeeOutputDto outputDto=new PcFeeOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PcFeeInfoOutputDto getPcFeeInfo(PcFeeInfoInputDto inputDto) throws BizException {
        PcFeeBizBo bizBo=iPcFeeBiz.getPcFeeById(inputDto.getId());
        PcFeeInfoOutputDto outputDto=new PcFeeInfoOutputDto();
        outputDto.setId(String.valueOf(bizBo.getId()));
        outputDto.setTier(String.valueOf(bizBo.getTier()));
        outputDto.setCompare(bizBo.getCompare());
        outputDto.setTradingVolume(DecimalUtil.toTrimLiteral(bizBo.getTradingVolume()));
        outputDto.setMakerFee(DecimalUtil.toTrimLiteral(bizBo.getMakerFee()));
        outputDto.setTakerFee(DecimalUtil.toTrimLiteral(bizBo.getTakerFee()));
        outputDto.setWithdrawLimit(DecimalUtil.toTrimLiteral(bizBo.getWithdrawLimit()));
        outputDto.setCtime(String.valueOf(bizBo.getCtime()));
        outputDto.setMtime(String.valueOf(bizBo.getMtime()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PcFeeListOutputDto getPcFeePageData(PcFeeListInputDto inputDto) throws BizException {
        PcFeeListBizBo listBizBo=iPcFeeBiz.getPcFeePageData(inputDto.getCurrentPage(),inputDto.getPageSize());
        PcFeeListOutputDto outputDto=new PcFeeListOutputDto();
        List<PcFeeInfoOutputDto> outputDtoList=new ArrayList<>();
        for(PcFeeBizBo bizBo:listBizBo.getRows())
        {
            PcFeeInfoOutputDto row=new PcFeeInfoOutputDto();
            row.setId(String.valueOf(bizBo.getId()));
            row.setTier(String.valueOf(bizBo.getTier()));
            row.setCompare(bizBo.getCompare());
            row.setTradingVolume(DecimalUtil.toTrimLiteral(bizBo.getTradingVolume()));
            row.setMakerFee(DecimalUtil.toTrimLiteral(bizBo.getMakerFee()));
            row.setTakerFee(DecimalUtil.toTrimLiteral(bizBo.getTakerFee()));
            row.setWithdrawLimit(DecimalUtil.toTrimLiteral(bizBo.getWithdrawLimit()));
            row.setCtime(String.valueOf(bizBo.getCtime()));
            row.setMtime(String.valueOf(bizBo.getMtime()));
            outputDtoList.add(row);
        }
        outputDto.setRows(outputDtoList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }
}
