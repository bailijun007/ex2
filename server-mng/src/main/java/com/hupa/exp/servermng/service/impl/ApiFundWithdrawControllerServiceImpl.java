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
import com.hupa.exp.daomongo.dao.expv2.def.IFundWithdrawSymbolMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundWithdrawSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.fundwithdraw.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiFundWithdrawControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IFundWithdrawSymbolMongoDao withdrawSymbolMongoDao;

    @Autowired
    private IAssetDao iAssetDao;


    @Override
    public FundWithdrawListOutputDto getFundWithdrawList(FundWithdrawListInputDto inputDto) throws BizException {
        ExpUserPo userPo = new ExpUserPo();
        Long accountId = null;
        if (!StringUtils.isEmpty(inputDto.getAccount())) {
            userPo = iExpUserDao.selectUserByAccount(inputDto.getAccount());
            if (userPo != null) {
                accountId = userPo.getId();
            }
        }
        FundWithdrawMongoPageBizBo pageBizBo = iWithdrawBiz.selectFundWithdrawPageData(
                accountId,
                inputDto.getSymbol(), inputDto.getId(),
                inputDto.getCurrentPage(), inputDto.getPageSize());
        List<FundWithdrawOutputDto> list = new ArrayList<>();
        for (FundWithdrawMongoBizBo bo : pageBizBo.getRows()) {
            FundWithdrawOutputDto info = new FundWithdrawOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setAsset(bo.getSymbol());
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
        FundWithdrawListOutputDto outputDto = new FundWithdrawListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return outputDto;
    }

    @Override
    public AuditFundWithdrawOutputDto auditPassFundWithdraw(AuditPassFundWithdrawInputDto inputDto) throws BizException {
        FundWithdrawMongoBizBo beforeBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        boolean bol = fundAccount4MngDef.withdrawAuditPass(String.valueOf(inputDto.getAccountId()),inputDto.getAccountId(), inputDto.getSymbol()
                , inputDto.getWithdrawId(), FundAccount4MngTokenUtil.genToken4WithdrawAuditPass(String.valueOf(inputDto.getAccountId()),inputDto.getAccountId(), inputDto.getSymbol()
                        , inputDto.getWithdrawId()));
        FundWithdrawMongoBizBo afterBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();

        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.FundAccount.toString(),
                OperationType.Audit.toString(),
                JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(afterBo));
        AuditFundWithdrawOutputDto outputDto = new AuditFundWithdrawOutputDto();
        outputDto.setAuditStatus(bol);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public AuditFundWithdrawOutputDto auditFailFundWithdraw(AuditFailFundWithdrawInputDto inputDto) throws BizException {
        FundWithdrawMongoBizBo beforeBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        boolean bol = fundAccount4MngDef.withdrawAuditFail(String.valueOf(inputDto.getAccountId()),inputDto.getAccountId(), inputDto.getSymbol(), inputDto.getWithdrawId(),
                inputDto.getReason(),
                FundAccount4MngTokenUtil.genToken4WithdrawAuditFail(String.valueOf(inputDto.getAccountId()),inputDto.getAccountId(), inputDto.getSymbol(), inputDto.getWithdrawId(),
                        inputDto.getReason()));
        FundWithdrawMongoBizBo afterBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.FundAccount.toString(),
                OperationType.Audit.toString(),
                JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(afterBo));
        AuditFundWithdrawOutputDto outputDto = new AuditFundWithdrawOutputDto();
        outputDto.setAuditStatus(bol);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public FundWithdrawAccountListOutputDto getAccountAllFundWith(FundWithdrawAccountListInputDto inputDto) throws BizException {
        List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<FundWithdrawSymbolMongoPo> withdrawSymbolMongoPoList = new ArrayList<>();
        int counts = 0;
        MongoSortEnum sort = MongoSortEnum.desc;
        List<FundWithdrawSymbolMongoPo> newList = new ArrayList<>();
        if (inputDto.getPageStatus() == -1)
            sort = MongoSortEnum.asc;
        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {
            sort = MongoSortEnum.desc;
            inputDto.setWithdrawTime(null);
            inputDto.setWithdrawId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<FundWithdrawSymbolMongoPo> withdrawSymbolMongoPoMongoPage = withdrawSymbolMongoDao.pageAllFundWithdrawPosByAccountId(
                    inputDto.getAccountId(), assetPo.getRealName(),
                    inputDto.getWithdrawTime(), inputDto.getWithdrawId(),
                    inputDto.getPageStatus(),
                    inputDto.getCurrentPage(), inputDto.getPageSize(),
                    sort
            );
            withdrawSymbolMongoPoList.addAll(withdrawSymbolMongoPoMongoPage.getRows());
            counts += withdrawSymbolMongoPoMongoPage.getTotalCount();
        }
        newList = withdrawSymbolMongoPoList.stream().sorted(Comparator.comparing(FundWithdrawSymbolMongoPo::getId).reversed())
                //Comparator.comparing(FundWithdrawSymbolMongoPo::getWithdrawTime
                .collect(Collectors.toList());
        if (newList.size() > 10) {
            if (inputDto.getPageStatus() == -1&&inputDto.getCurrentPage()!=1)
                newList = newList.subList(newList.size() - 10, newList.size());
            else
                newList = newList.subList(0, 10);

        } else {
            newList = newList.subList(0, newList.size());
        }

        FundWithdrawAccountListOutputDto outputDto = new FundWithdrawAccountListOutputDto();
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setTotalCount(Long.parseLong(String.valueOf(counts)));
        List<FundWithdrawOutputDto> list = new ArrayList<>();
        for (FundWithdrawSymbolMongoPo po : newList) {
            FundWithdrawOutputDto row = new FundWithdrawOutputDto();

            row.setId(String.valueOf(po.getId()));
            row.setAsset(po.getSymbol());
            row.setAccountId(String.valueOf(po.getAccountId()));
            row.setTargetAddr(po.getTargetAddr());
            row.setVolume(DecimalUtil.trimZeroPlainString(po.getVolume()));
            row.setFee(DecimalUtil.trimZeroPlainString(po.getFee()));
            row.setWithdrawTime(String.valueOf(po.getWithdrawTime()));
            row.setStatus(String.valueOf(po.getStatus()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));

            list.add(row);
        }
        outputDto.setRows(list);
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
