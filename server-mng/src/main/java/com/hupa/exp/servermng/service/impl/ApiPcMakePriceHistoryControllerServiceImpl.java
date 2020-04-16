package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomysql.config.Expv2MySqlConfig;
import com.hupa.exp.daomysql.dao.expv2.def.IDataBaseDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcMarkPriceHistoryDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcMarkPriceHistoryPo;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryInfoOutputDto;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListInputDto;
import com.hupa.exp.servermng.entity.pcmakepricehistory.PcMakePriceHistoryListOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiPcMakePriceHistoryControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcMakePriceHistoryControllerServiceImpl implements IApiPcMakePriceHistoryControllerService {

    @Autowired
    private IPcMarkPriceHistoryDao iPcMakePriceHistoryDao;

    @Autowired
    private IDataBaseDao iDataBaseDao;

    @Autowired
    private Expv2MySqlConfig expv2MySqlConfig;
    @Override
    public PcMakePriceHistoryListOutputDto getPcMakePriceHistoryPageData(PcMakePriceHistoryListInputDto inputDto) throws BizException {
        if(!iDataBaseDao.existTable(expv2MySqlConfig.getDbName(), PcMarkPriceHistoryPo.tableNamePattern+inputDto.getYear()))
        {
            throw new MngException(MngExceptionCode.TABLE_NOT_EXIST_ERROR);
        }

        IPage<PcMarkPriceHistoryPo> pageData= iPcMakePriceHistoryDao.selectPcMarkPriceHistoryPageData(
                PcMarkPriceHistoryPo.tableNamePattern+inputDto.getYear(),inputDto.getAsset(),inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());

        PcMakePriceHistoryListOutputDto outputDto=new PcMakePriceHistoryListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<PcMakePriceHistoryInfoOutputDto> rows=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(pageData.getRecords())) {
            PcMakePriceHistoryInfoOutputDto row = null;
            for (PcMarkPriceHistoryPo po : pageData.getRecords()) {
                row = new PcMakePriceHistoryInfoOutputDto();
                row.setId(String.valueOf(po.getId()));
                row.setAsset(po.getAsset());
                row.setFundRate(DecimalUtil.toTrimLiteral(po.getFundRate()));
                row.setPrice(DecimalUtil.toTrimLiteral(po.getPrice()));
                row.setSymbol(po.getSymbol());
                row.setSourceValue(po.getSourceValue());
                row.setCtime(String.valueOf(po.getCtime()));
                row.setMtime(String.valueOf(po.getMtime()));
                rows.add(row);
            }
        }
        outputDto.setRows(rows);
        return outputDto;
    }
}
