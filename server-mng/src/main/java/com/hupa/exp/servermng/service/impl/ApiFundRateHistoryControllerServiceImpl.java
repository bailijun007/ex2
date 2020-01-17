package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomysql.config.Expv2MySqlConfig;
import com.hupa.exp.daomysql.dao.expv2.def.IDataBaseDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcFundRateHistoryDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcFundRateHistoryPo;
import com.hupa.exp.servermng.entity.fundrate.FundRateHistoryInfoOutputDto;
import com.hupa.exp.servermng.entity.fundrate.FundRateHistoryListInputDto;
import com.hupa.exp.servermng.entity.fundrate.FundRateHistoryListOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiFundRateHistoryControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundRateHistoryControllerServiceImpl implements IApiFundRateHistoryControllerService {

    @Autowired
    private IPcFundRateHistoryDao iPcFundRateHistoryDao;

    @Autowired
    private IDataBaseDao iDataBaseDao;

    @Autowired
    private Expv2MySqlConfig expv2MySqlConfig;

    /**
     *  查询资金费率
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public FundRateHistoryListOutputDto getFundRateHistoryPageData(FundRateHistoryListInputDto inputDto) throws BizException {
        if(!iDataBaseDao.existTable(expv2MySqlConfig.getDbName(), PcFundRateHistoryPo.tableNamePattern+inputDto.getYear()))
        {
            throw new MngException(MngExceptionCode.TABLE_NOT_EXIST_ERROR);
        }


        IPage<PcFundRateHistoryPo> pageData= iPcFundRateHistoryDao.selectPcFundRateHistoryPageData(
                PcFundRateHistoryPo.tableNamePattern+inputDto.getYear(),inputDto.getAsset(),inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        FundRateHistoryListOutputDto outputDto=new FundRateHistoryListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<FundRateHistoryInfoOutputDto> rows=new ArrayList<>();
        for(PcFundRateHistoryPo po:pageData.getRecords())
        {
            FundRateHistoryInfoOutputDto row=new FundRateHistoryInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setAsset(po.getAsset());
            row.setSymbol(po.getSymbol());
            row.setFundRate(DecimalUtil.toTrimLiteral(po.getFundRate()));
            row.setSourceValue(po.getSourceValue());
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }
}
