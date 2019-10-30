package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomongo.dao.expv2.def.IAccountTransferSymbolMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.AccountTransferSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.servermng.service.def.IApiAccountTransferControllerService;
import com.hupa.exp.servermng.entity.transfer.*;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiAccountTransferControllerServiceImpl implements IApiAccountTransferControllerService {
    @Autowired
    private IAccountTransferSymbolMongoDao transferSymbolMongoDao;

    @Autowired
    private IAssetDao iAssetDao;

    @Override
    public TransferListOutputDto getAllAccountTransferByAccount(TransferListInputDto inputDto) throws BizException {
        List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<AccountTransferSymbolMongoPo> transferPoList = new ArrayList<>();
        int counts = 0;
        MongoSortEnum sort = MongoSortEnum.desc;
        List<AccountTransferSymbolMongoPo> newList = new ArrayList<>();
        Integer pageStatus=inputDto.getPageStatus();
        Long transferId=inputDto.getTransferId();
        Long transferTime=inputDto.getTransferTime();
        Long userId = inputDto.getAccountId()==null?0: inputDto.getAccountId();
        int pageSize = Integer.valueOf(inputDto.getPageSize());
        long currentPage = Long.valueOf(inputDto.getCurrentPage());
        String asset=inputDto.getAsset();
        if (pageStatus == -1)
            sort = MongoSortEnum.asc;
        //为第一页的时候重置查询条件
        if (currentPage == 1) {
            sort = MongoSortEnum.desc;
            transferTime = null;
            transferId = null;
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(asset)) {
                if (!assetPo.getRealName().equals(asset))
                    continue;
            }
            //查合约账户数据
            MongoPage<AccountTransferSymbolMongoPo> pcAccountTransferDesc = transferSymbolMongoDao.queryPcAccountTransferDesc(
                    userId, assetPo.getRealName(),
                    transferId,transferTime,
                    pageStatus,
                    pageSize,currentPage
            );
            transferPoList.addAll(pcAccountTransferDesc.getRows());
            counts += pcAccountTransferDesc.getTotalCount();
            //查资金账户数据
            MongoPage<AccountTransferSymbolMongoPo> fundAccountTransferDesc = transferSymbolMongoDao.queryFundAccountTransferDesc(
                    userId, assetPo.getRealName(),
                    transferId,transferTime,
                    pageStatus,
                    pageSize,currentPage
            );
            transferPoList.addAll(fundAccountTransferDesc.getRows());
            counts += fundAccountTransferDesc.getTotalCount();
        }
        //时间排序  id排序
        newList = transferPoList.stream().
                sorted(Comparator.comparing(AccountTransferSymbolMongoPo::getId).reversed()
                )//.thenComparing(FundWithdrawSymbolMongoPo::getWithdrawTime).reversed()
                .collect(Collectors.toList());
        if (newList.size() > 10) {
            if (inputDto.getPageStatus() == -1&&inputDto.getCurrentPage()!=1)
                newList = newList.subList(newList.size() - 10, newList.size());
            else
                newList = newList.subList(0, 10);

        } else {
            newList = newList.subList(0, newList.size());
        }
        TransferListOutputDto outputDto=new TransferListOutputDto();
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        List<TransferInfoOutputDto> rows=new ArrayList<>();
        for(AccountTransferSymbolMongoPo po:newList)
        {
            TransferInfoOutputDto row=new TransferInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setAsset(String.valueOf(po.getAsset()));
            row.setSrcAccountId(String.valueOf(po.getSrcAccountId()));
            row.setTarAccountId(String.valueOf(po.getTarAccountId()));
            row.setSrcAccType(String.valueOf(po.getSrcAccType()));
            row.setTarAccType(String.valueOf(po.getTarAccType()));
            row.setVolume(String.valueOf(po.getVolume()));
            row.setReceiveFlag(String.valueOf(po.getReceiveFlag()));
            row.setReceiveTime(String.valueOf(po.getReceiveTime()));
            row.setRemark(String.valueOf(po.getRemark()));
            row.setOperator(String.valueOf(po.getOperator()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }
}
