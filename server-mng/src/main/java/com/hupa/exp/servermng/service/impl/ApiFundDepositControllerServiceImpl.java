package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.DepositStatusEnum;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomongo.dao.expv2.def.IFundDepositSymbolMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundDepositSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositInfoOutputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListInputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListOutputDto;
import com.hupa.exp.servermng.service.def.IApiFundDepositControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiFundDepositControllerServiceImpl implements IApiFundDepositControllerService {

    @Autowired
    private IFundDepositSymbolMongoDao depositSymbolMongoDao;
    @Autowired
    private IAssetDao iAssetDao;

    @Override
    public FundDepositListOutputDto getAccountAllFundDeposit(FundDepositListInputDto inputDto) throws BizException {
        List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<FundDepositSymbolMongoPo> depositSymbolMongoPoArrayList = new ArrayList<>();
        int counts = 0;
        MongoSortEnum sort = MongoSortEnum.desc;
        List<FundDepositSymbolMongoPo> newList = new ArrayList<>();
        if (inputDto.getPageStatus() == -1)
            sort = MongoSortEnum.asc;
        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {
            sort = MongoSortEnum.desc;
            inputDto.setDepositTime(null);
            inputDto.setDepositId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<FundDepositSymbolMongoPo> withdrawSymbolMongoPoMongoPage = depositSymbolMongoDao.pageAllDepositPosByAccountId(
                    inputDto.getAccountId(), assetPo.getRealName(),
                    inputDto.getDepositTime(), inputDto.getDepositId(),
                    inputDto.getPageStatus(),
                    inputDto.getCurrentPage(), inputDto.getPageSize(),
                    sort
            );
            depositSymbolMongoPoArrayList.addAll(withdrawSymbolMongoPoMongoPage.getRows());
            counts += withdrawSymbolMongoPoMongoPage.getTotalCount();
        }
        newList = depositSymbolMongoPoArrayList.stream().sorted(Comparator.comparing(FundDepositSymbolMongoPo::getId).reversed())
                .collect(Collectors.toList());
        if (newList.size() > 10) {
            if (inputDto.getPageStatus() == -1&&inputDto.getCurrentPage()!=1)
                newList = newList.subList(newList.size() - 10, newList.size());
            else
                newList = newList.subList(0, 10);
        } else {
            newList = newList.subList(0, newList.size());
        }


        FundDepositListOutputDto outputDto = new FundDepositListOutputDto();
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setTotalCount(Long.parseLong(String.valueOf(counts)));
        List<FundDepositInfoOutputDto> list = new ArrayList<>();
        for (FundDepositSymbolMongoPo po : newList) {
            FundDepositInfoOutputDto row = new FundDepositInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            //row.setAssetByDisplay(po.getSymbol());
            row.setId(String.valueOf(po.getId()));
            row.setChainServerOrderId(po.getChainServerOrderId());
            row.setAccountId(String.valueOf(po.getAccountId()));
            //row.setAsset(po.getAsset());
            row.setAsset(po.getSymbol());
            row.setAddress(po.getAddress());
            //row.setChainTransactionUrl(po.getChainTransactionUrl());
            row.setVolume(DecimalUtil.plainString(po.getVolume()));
            row.setDepositTime(String.valueOf(po.getDepositTime()));
            row.setLastConfirmTime(String.valueOf(po.getLastConfirmTime()));
            row.setTxHash(po.getTxHash());
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

