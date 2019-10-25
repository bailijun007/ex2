package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssetControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiAssetControllerServiceImpl implements IApiAssetControllerService {
    @Autowired
    private IAssetBiz iAssetBiz;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public AssetOutputDto createAsset(AssetInputDto inputDto) throws BizException {

        AssetBizBo bo= ConventObjectUtil.conventObject(inputDto,AssetBizBo.class);
        bo.setId(inputDto.getId());
        //bo.setSymbol(inputDto.getSymbol());
        bo.setChainAppointId(inputDto.getChainAppointId());
        bo.setChainName(inputDto.getChainNname());
        bo.setRealName(inputDto.getRealName());
        bo.setDisplayName(inputDto.getDisplaynName());
        bo.setPrecision(inputDto.getPrecision());
        bo.setPrivilege(inputDto.getPrivilege());
        bo.setStatus(inputDto.getStatus());
        bo.setSort(inputDto.getSort());
        bo.setMinDepositVolume(inputDto.getMinDepositVolume());
        bo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        bo.setWithdrawFee(inputDto.getWithdrawFee());
        bo.setMtime(System.currentTimeMillis());
        bo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        iAssetBiz.createCoin(bo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Asset.toString(),
                OperationType.Insert.toString(),
                JsonUtil.toJsonString(bo),"");
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }

    @Override
    public AssetOutputDto editAsset(AssetInputDto inputDto) throws BizException {
        AssetBizBo beforeBo= iAssetBiz.queryCoinById(inputDto.getId());

        AssetBizBo afterBo=new AssetBizBo();
        afterBo.setId(inputDto.getId());
        //afterBo.setSymbol(inputDto.getSymbol());
        afterBo.setChainAppointId(inputDto.getChainAppointId());
        afterBo.setChainName(inputDto.getChainNname());
        afterBo.setRealName(inputDto.getRealName());
        afterBo.setDisplayName(inputDto.getDisplaynName());
        afterBo.setPrecision(inputDto.getPrecision());
        afterBo.setPrivilege(inputDto.getPrivilege());
        afterBo.setStatus(inputDto.getStatus());
        afterBo.setSort(inputDto.getSort());
        afterBo.setMinDepositVolume(inputDto.getMinDepositVolume());
        afterBo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        afterBo.setWithdrawFee(inputDto.getWithdrawFee());
        afterBo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        afterBo.setMtime(System.currentTimeMillis());

        iAssetBiz.editCoin(afterBo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Asset.toString(),
                OperationType.Update.toString(),
                JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }

    @Override
    public GetAssetOutputDto getAssetById(GetAssetInputDto inputDto) throws BizException {
        AssetBizBo bo=  iAssetBiz.queryCoinById(inputDto.getId());
        GetAssetOutputDto outputDto=new GetAssetOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        //outputDto.setSymbol(bo.getSymbol());
        outputDto.setChainAppointId(String.valueOf(bo.getChainAppointId()));
        outputDto.setChainName(bo.getChainName());
        outputDto.setRealName(bo.getRealName());
        outputDto.setDisplayName(bo.getDisplayName());
        outputDto.setPrecision(String.valueOf(bo.getPrecision()));
        outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setSort(String.valueOf(bo.getSort()));
        outputDto.setMtime(String.valueOf(System.currentTimeMillis()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        outputDto.setMinDepositVolume(String.valueOf(bo.getMinDepositVolume()));
        outputDto.setMinWithdrawVolume(DecimalUtil.trimZeroPlainString(bo.getMinWithdrawVolume()));
        outputDto.setWithdrawFee(DecimalUtil.trimZeroPlainString(bo.getWithdrawFee()));
        outputDto.setChainTransactionUrl(bo.getChainTransactionUrl());
        return outputDto;
    }

    @Override
    public AssetListOutputDto getAssetList(AssetListInputDto inputDto) throws BizException {
        CoinPageListBizBo listBizBo= iAssetBiz.queryAssetList(inputDto.getRealName(),inputDto.getCurrentPage(),inputDto.getPageSize());
        AssetListOutputDto outputDto=new AssetListOutputDto();
        List<AssetListOutputPage> pageList=new ArrayList<>();
        for(AssetBizBo bo:listBizBo.getRows())
        {
            AssetListOutputPage po=new AssetListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            //po.setSymbol(bo.getSymbol());
            po.setChainAppointId(String.valueOf(bo.getChainAppointId()));
            po.setChainName(bo.getChainName());
            po.setRealName(bo.getRealName());
            po.setDisplayName(bo.getDisplayName());
            po.setPrecision(String.valueOf(bo.getPrecision()));
            po.setPrivilege(String.valueOf(bo.getPrivilege()));
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setMtime(String.valueOf(bo.getMtime()));
            po.setCtime(String.valueOf(bo.getCtime()));
            po.setSort(String.valueOf(bo.getSort()));
            po.setMinDepositVolume(DecimalUtil.trimZeroPlainString(bo.getMinDepositVolume()));
            po.setMinWithdrawVolume(DecimalUtil.trimZeroPlainString(bo.getMinWithdrawVolume()));
            po.setWithdrawFee(DecimalUtil.trimZeroPlainString(bo.getWithdrawFee()));
            po.setChainTransactionUrl(bo.getChainTransactionUrl());
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        return outputDto;
    }

    @Override
    public RealNameListOutPutDto getRealNameList(RealNameListInputDto inputDto) throws BizException {
        AssetListBizBo bizBo= iAssetBiz.queryRealNameList();
        RealNameListOutPutDto outPutDto=new RealNameListOutPutDto();
        outPutDto.setAssetList(bizBo);
        outPutDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outPutDto;
    }

    @Override
    public CheckHasAssetOutputDto checkHasAsset(CheckHasAssetInputDto inputDto) throws BizException {
        boolean hasAsset= iAssetBiz.checkHasCoin(inputDto.getRealName());
        CheckHasAssetOutputDto outputDto=new CheckHasAssetOutputDto();
        outputDto.setHasAsset(hasAsset);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
