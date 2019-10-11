package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.CoinBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.SymbolListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.ICoinBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.servermng.entity.coin.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiCoinControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCoinControllerServiceImpl implements IApiCoinControllerService {
    @Autowired
    private ICoinBiz iCoinService;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public CoinOutputDto createCoin(CoinInputDto inputDto) throws BizException {

        CoinBizBo bo= ConventObjectUtil.conventObject(inputDto,CoinBizBo.class);
        bo.setId(inputDto.getId());
        bo.setSymbol(inputDto.getSymbol());
        bo.setChainSymbolId(inputDto.getChainSymbolId());
        bo.setChainName(inputDto.getChainNname());
        bo.setCoinName(inputDto.getCoinName());
        bo.setDisplayName(inputDto.getDisplaynName());
        bo.setPrecision(inputDto.getPrecision());
        bo.setPrivilege(inputDto.getPrivilege());
        bo.setStatus(inputDto.getStatus());
        bo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        bo.setWithdrawFee(inputDto.getWithdrawFee());
        bo.setMtime(System.currentTimeMillis());
        bo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        iCoinService.createCoin(bo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Coin.toString(),
                OperationType.Update.toString(),
                JsonUtil.toJsonString(bo),"");
        CoinOutputDto outputDto=new CoinOutputDto();
        return outputDto;
    }

    @Override
    public CoinOutputDto editCoin(CoinInputDto inputDto) throws BizException {
        CoinBizBo beforeBo=iCoinService.queryCoinById(inputDto.getId());

        CoinBizBo afterBo=new CoinBizBo();
        afterBo.setId(inputDto.getId());
        afterBo.setSymbol(inputDto.getSymbol());
        afterBo.setChainSymbolId(inputDto.getChainSymbolId());
        afterBo.setChainName(inputDto.getChainNname());
        afterBo.setCoinName(inputDto.getCoinName());
        afterBo.setDisplayName(inputDto.getDisplaynName());
        afterBo.setPrecision(inputDto.getPrecision());
        afterBo.setPrivilege(inputDto.getPrivilege());
        afterBo.setStatus(inputDto.getStatus());
        afterBo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        afterBo.setWithdrawFee(inputDto.getWithdrawFee());
        afterBo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        afterBo.setMtime(System.currentTimeMillis());

        iCoinService.editCoin(afterBo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Coin.toString(),
                OperationType.Update.toString(),
                JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        CoinOutputDto outputDto=new CoinOutputDto();
        return outputDto;
    }

    @Override
    public GetCoinOutputDto getCoinById(GetCoinInputDto inputDto) throws BizException {
        CoinBizBo bo=  iCoinService.queryCoinById(inputDto.getId());
        GetCoinOutputDto outputDto=new GetCoinOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setSymbol(bo.getSymbol());
        outputDto.setChainSymbolId(String.valueOf(bo.getChainSymbolId()));
        outputDto.setChainName(bo.getChainName());
        outputDto.setCoinName(bo.getCoinName());
        outputDto.setDisplayName(bo.getDisplayName());
        outputDto.setPrecision(String.valueOf(bo.getPrecision()));
        outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setMtime(String.valueOf(System.currentTimeMillis()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        outputDto.setMinWithdrawVolume(DecimalUtil.trimZeroPlainString(bo.getMinWithdrawVolume()));
        outputDto.setWithdrawFee(DecimalUtil.trimZeroPlainString(bo.getWithdrawFee()));
        outputDto.setChainTransactionUrl(bo.getChainTransactionUrl());
        return outputDto;
    }

    @Override
    public CoinListOutputDto getCoinList(CoinListInputDto inputDto) throws BizException {
        CoinPageListBizBo listBizBo=iCoinService.queryCoinList(inputDto.getCurrentPage(),inputDto.getPageSize());
        CoinListOutputDto outputDto=new CoinListOutputDto();
        List<CoinListOutputPage> pageList=new ArrayList<>();
        for(CoinBizBo bo:listBizBo.getRows())
        {
            CoinListOutputPage po=new CoinListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol());
            po.setChainSymbolId(String.valueOf(bo.getChainSymbolId()));
            po.setChainName(bo.getChainName());
            po.setCoinName(bo.getCoinName());
            po.setDisplayName(bo.getDisplayName());
            po.setPrecision(String.valueOf(bo.getPrecision()));
            po.setPrivilege(String.valueOf(bo.getPrivilege()));
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setMtime(String.valueOf(bo.getMtime()));
            po.setCtime(String.valueOf(bo.getCtime()));
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
    public SymbolListOutPutDto getSymbolList(SymbolListInputDto inputDto) throws BizException {
        SymbolListBizBo bizBo= iCoinService.querySymbolList();
        SymbolListOutPutDto outPutDto=new SymbolListOutPutDto();
        outPutDto.setSymbolListBizBo(bizBo);
        outPutDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outPutDto;
    }

    @Override
    public CheckHasCoinOutputDto checkHasCoin(CheckHasCoinInputDto inputDto) throws BizException {
        boolean hasCoin=iCoinService.checkHasCoin(inputDto.getSymbol());
        CheckHasCoinOutputDto outputDto=new CheckHasCoinOutputDto();
        outputDto.setHasCoin(hasCoin);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
