package com.hupa.exp.servermng.service.impl;

/*import com.hupa.exp.daomongo.dao.expv2.def.IFundAccountAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.FundAccountLogSymbolMongoPo;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;*/
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListInputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListOutputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogOutputDto;
import com.hupa.exp.servermng.service.def.IApiFundAccountControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiFundAccountControllerServiceImpl implements IApiFundAccountControllerService {

    /*@Autowired
    private IFundAccountAssetMongoDao iFundAccountAssetMongoDao;*/

    @Autowired
    private IAssetDao iAssetDao;

    /**
     * 查询资金账户日志
     * @param inputDto
     * @return
     */
    @Override
    public FundAccountLogListOutputDto getFundAccountLogList(FundAccountLogListInputDto inputDto) {

       /* List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<FundAccountLogSymbolMongoPo> withdrawSymbolMongoPoList = new ArrayList<>();
        int counts = 0;
        List<FundAccountLogSymbolMongoPo> newList = new ArrayList<>();

        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {

            inputDto.setId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<FundAccountLogSymbolMongoPo> pageBizBo= iFundAccountAssetMongoDao.selectFundAccountLogPos(
                    assetPo.getRealName(),inputDto.getId(),
                    inputDto.getAccountId(),inputDto.getPageStatus(),
                    inputDto.getCurrentPage(),inputDto.getPageSize());
            withdrawSymbolMongoPoList.addAll(pageBizBo.getRows());
            counts += pageBizBo.getTotalCount();
        }
        newList = withdrawSymbolMongoPoList.stream().sorted(Comparator.comparing(FundAccountLogSymbolMongoPo::getId).reversed())
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

        List<FundAccountLogOutputDto> rows=new ArrayList<>();
        for(FundAccountLogSymbolMongoPo bo:newList)
        {
            FundAccountLogOutputDto row= ConventObjectUtil.conventObject(bo,FundAccountLogOutputDto.class);
            rows.add(row);
        }
        FundAccountLogListOutputDto outputDto=new FundAccountLogListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setRows(rows);*/
        FundAccountLogListOutputDto outputDto=new FundAccountLogListOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
