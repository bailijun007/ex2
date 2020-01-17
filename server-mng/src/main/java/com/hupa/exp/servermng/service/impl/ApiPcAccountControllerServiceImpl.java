package com.hupa.exp.servermng.service.impl;

/*import com.hupa.exp.daomongo.dao.expv2.def.IPcAccountAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcAccountLogAssetMongoPo;
import com.hupa.exp.daomongo.enums.MongoSortEnum;*/
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListInputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListOutputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcAccountControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiPcAccountControllerServiceImpl implements IApiPcAccountControllerService {

    //@Autowired
    //private IPcAccountAssetMongoDao iPcAccountAssetMongoDao;

    @Autowired
    private IAssetDao iAssetDao;

    /**
     * 合约账户日志查询
     * @param inputDto
     * @return
     */
    @Override
    public PcAccountLogListOutputDto getPcAccountLogList(PcAccountLogListInputDto inputDto) {
      /*  List<AssetPo> assetPos = iAssetDao.selectActiveList();
        List<PcAccountLogAssetMongoPo> withdrawSymbolMongoPoList = new ArrayList<>();
        int counts = 0;
        MongoSortEnum sort = MongoSortEnum.desc;
        List<PcAccountLogAssetMongoPo> newList = new ArrayList<>();
        if (inputDto.getPageStatus() == -1)
            sort = MongoSortEnum.asc;
        //为第一页的时候重置查询条件
        if (inputDto.getCurrentPage() == 1) {
            sort = MongoSortEnum.desc;
            inputDto.setId(null);
        }
        for (AssetPo assetPo : assetPos) {
            //如果传进来的币种部位空的时候  只查传进来的币种
            if (!StringUtils.isEmpty(inputDto.getAsset())) {
                if (!assetPo.getRealName().equals(inputDto.getAsset()))
                    continue;
            }
            MongoPage<PcAccountLogAssetMongoPo> pageBizBo= iPcAccountAssetMongoDao.selectPcAccountLogPos(
                    assetPo.getRealName(),inputDto.getId(),
                    inputDto.getAccountId(),inputDto.getPageStatus(),
                    inputDto.getCurrentPage(),inputDto.getPageSize());
            withdrawSymbolMongoPoList.addAll(pageBizBo.getRows());
            counts += pageBizBo.getTotalCount();
        }
        newList = withdrawSymbolMongoPoList.stream().sorted(Comparator.comparing(PcAccountLogAssetMongoPo::getId).reversed())
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

        List<PcAccountLogOutputDto> rows=new ArrayList<>();
        for(PcAccountLogAssetMongoPo bo:newList)
        {
           PcAccountLogOutputDto row= ConventObjectUtil.conventObject(bo,PcAccountLogOutputDto.class);
            rows.add(row);
        }
        PcAccountLogListOutputDto outputDto=new PcAccountLogListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(Long.parseLong(String.valueOf(counts)));
        outputDto.setRows(rows);*/
        PcAccountLogListOutputDto outputDto=new PcAccountLogListOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
