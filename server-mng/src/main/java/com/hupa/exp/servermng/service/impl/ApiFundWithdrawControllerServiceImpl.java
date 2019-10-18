package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.account.def.fund.FundAccount4MngDef;
import com.hupa.exp.account.util.token.fund.FundAccount4MngTokenUtil;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.FundWithdrawMongoPageBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IWithdrawBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.fundwithdraw.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiFundWithdrawControllerService;
import com.hupa.exp.util.math.DecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundWithdrawControllerServiceImpl implements IApiFundWithdrawControllerService {
    @Autowired
    private IWithdrawBiz iWithdrawBiz;

    @Reference
    private FundAccount4MngDef fundAccount4MngDef;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpUserDao iExpUserDao;



    @Override
    public FundWithdrawListOutputDto getFundWithdrawList(FundWithdrawListInputDto inputDto) throws BizException {
        ExpUserPo userPo=new ExpUserPo();
        Long accountId=null;
        if(!StringUtils.isEmpty(inputDto.getAccount()))
        {
            userPo=iExpUserDao.selectUserByAccount(inputDto.getAccount());
            if(userPo!=null)
            {
                accountId=userPo.getId();
            }
        }
        FundWithdrawMongoPageBizBo pageBizBo=iWithdrawBiz.selectFundWithdrawPageData(
                accountId,
                inputDto.getSymbol(),inputDto.getId(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<FundWithdrawOutputDto> list=new ArrayList<>();
        for(FundWithdrawMongoBizBo bo:pageBizBo.getRows())
        {
            FundWithdrawOutputDto info=new FundWithdrawOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setSymbol(bo.getSymbol());
            info.setAccountId(String.valueOf(bo.getAccountId()));
            info.setTargetAddr(bo.getTargetAddr());
            info.setVolume(DecimalUtil.trimZeroPlainString(bo.getVolume()));
            info.setFee(DecimalUtil.trimZeroPlainString(bo.getFee()));
            info.setWithdrawTime(DecimalUtil.trimZeroPlainString(bo.getWithdrawTime()));
            info.setStatus(String.valueOf(bo.getStatus()));
            info.setCtime(String.valueOf(bo.getCtime()));
            info.setMtime(String.valueOf(bo.getMtime()));
            list.add(info);
        }
        FundWithdrawListOutputDto outputDto=new FundWithdrawListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return  outputDto;
    }

    @Override
    public AuditFundWithdrawOutputDto auditPassFundWithdraw(AuditPassFundWithdrawInputDto inputDto) throws BizException {
        FundWithdrawMongoBizBo beforeBo= iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(),inputDto.getSymbol());
        boolean bol= fundAccount4MngDef.withdrawAuditPass(inputDto.getAccountId(),inputDto.getSymbol()
                ,inputDto.getWithdrawId(), FundAccount4MngTokenUtil.genToken4WithdrawAuditPass(inputDto.getAccountId(),inputDto.getSymbol()
                        ,inputDto.getWithdrawId()));
        FundWithdrawMongoBizBo afterBo= iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(),inputDto.getSymbol());
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();

        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.FundAccount.toString(),
                OperationType.Audit.toString(),
                JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        AuditFundWithdrawOutputDto outputDto=new AuditFundWithdrawOutputDto();
        outputDto.setAuditStatus(bol);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public AuditFundWithdrawOutputDto auditFailFundWithdraw(AuditFailFundWithdrawInputDto inputDto) throws BizException {
        FundWithdrawMongoBizBo beforeBo= iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(),inputDto.getSymbol());
        boolean bol= fundAccount4MngDef.withdrawAuditFail(inputDto.getAccountId(), inputDto.getSymbol(), inputDto.getWithdrawId(),
                inputDto.getReason(),
                FundAccount4MngTokenUtil.genToken4WithdrawAuditFail(inputDto.getAccountId(), inputDto.getSymbol(), inputDto.getWithdrawId(),
                        inputDto.getReason()));
        FundWithdrawMongoBizBo afterBo= iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(),inputDto.getSymbol());
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.FundAccount.toString(),
                OperationType.Audit.toString(),
                JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        AuditFundWithdrawOutputDto outputDto=new AuditFundWithdrawOutputDto();
        outputDto.setAuditStatus(bol);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
