package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IPcIndexPriceDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcIndexPricePo;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceInfoOutputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListInputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcIndexPriceControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcIndexPriceControllerServiceImpl implements IApiPcIndexPriceControllerService {
    @Autowired
    private IPcIndexPriceDao iPcIndexPriceDao;
    @Override
    public PcIndexPriceListOutputDto getPcIndexPricePageData(PcIndexPriceListInputDto inputDto) throws BizException {
        IPage<PcIndexPricePo> pageData= iPcIndexPriceDao.selectPcIndexPricePageData(
                PcIndexPricePo.tableNamePattern+inputDto.getYear(),inputDto.getAsset(),inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        PcIndexPriceListOutputDto outputDto=new PcIndexPriceListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<PcIndexPriceInfoOutputDto> rows=new ArrayList<>();
        for(PcIndexPricePo po:pageData.getRecords())
        {
            PcIndexPriceInfoOutputDto row=new PcIndexPriceInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setAsset(po.getAsset());
            row.setPrice(DecimalUtil.toTrimLiteral(po.getPrice()));
            row.setSymbol(po.getSymbol());
            row.setSourceValue(po.getSourceValue());
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }
}
