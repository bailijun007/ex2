package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.BbFeeBizBo;
import com.hupa.exp.bizother.entity.account.BbFeeListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IBbFeeBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IBbFeeDao;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.bbfee.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiBbFeeControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/6.
 */
@Service
public class ApiBbFeeControllerServiceImpl implements IApiBbFeeControllerService {


    @Autowired
    private IBbFeeBiz iBbFeeBiz;

    @Autowired
    private IBbFeeDao iBbFeeDao;
    @Autowired
    private IExpOperationLogService logService;
    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public BbFeeOutputDto createOrEditBbFee(BbFeeInputDto inputDto) throws BizException {
        BbFeeBizBo bizBo= ConventObjectUtil.conventObject(inputDto,BbFeeBizBo.class);
        bizBo.setMtime(System.currentTimeMillis());
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        if(bizBo.getId()>0)
        {
            BbFeeBizBo before=iBbFeeBiz.getBbFeeById(inputDto.getId());
            iBbFeeBiz.editBbFee(bizBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    "BbFee", OperationType.Update.toString(),
                    JsonUtil.toJsonString(before),JsonUtil.toJsonString(bizBo));
        }
        else
        {
            bizBo.setCtime(System.currentTimeMillis());
            iBbFeeBiz.createBbFee(bizBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    "BbFee", OperationType.Insert.toString(),
                    JsonUtil.toJsonString(bizBo),JsonUtil.toJsonString(""));
        }
        BbFeeOutputDto outputDto=new BbFeeOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public BbFeeInfoOutputDto getBbFeeInfo(BbFeeInfoInputDto inputDto) throws BizException {
        BbFeeBizBo bizBo=iBbFeeBiz.getBbFeeById(inputDto.getId());
        BbFeeInfoOutputDto outputDto=new BbFeeInfoOutputDto();
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
    public BbFeeListOutputDto getBbFeePageData(BbFeeListInputDto inputDto) throws BizException {
        BbFeeListBizBo listBizBo=iBbFeeBiz.getBbFeePageData(inputDto.getCurrentPage(),inputDto.getPageSize());
        BbFeeListOutputDto outputDto=new BbFeeListOutputDto();
        List<BbFeeInfoOutputDto> outputDtoList=new ArrayList<>();
        for(BbFeeBizBo bizBo:listBizBo.getRows())
        {
            BbFeeInfoOutputDto row=new BbFeeInfoOutputDto();
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

    @Override
    public DeleteOutputDto deleteBbFee(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                "BbFee", OperationType.Delete.toString(),
                inputDto.getIds(),"");

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iBbFeeDao.deleteById(Integer.valueOf(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;

    }
    
}
