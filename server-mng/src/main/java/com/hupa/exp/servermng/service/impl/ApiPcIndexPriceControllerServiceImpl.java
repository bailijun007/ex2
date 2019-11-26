package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListInputDto;
import com.hupa.exp.servermng.entity.pcindexprice.PcIndexPriceListOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcIndexPriceControllerService;
import org.springframework.stereotype.Service;

@Service
public class ApiPcIndexPriceControllerServiceImpl implements IApiPcIndexPriceControllerService {
//    @Autowired
//    private IPcIndexPriceDao iPcIndexPriceDao;
//
//    @Autowired
//    private IDataBaseDao iDataBaseDao;
//
//    @Autowired
//    private Expv2MySqlConfig expv2MySqlConfig;

    @Override
    public PcIndexPriceListOutputDto getPcIndexPricePageData(PcIndexPriceListInputDto inputDto) throws BizException {
//        if(!iDataBaseDao.existTable(expv2MySqlConfig.getDbName(),PcIndexPricePo.tableNamePattern+inputDto.getYear()))
//        {
//            throw new MngException(MngExceptionCode.TABLE_NOT_EXIST_ERROR);
//        }
//
//        IPage<PcIndexPricePo> pageData= iPcIndexPriceDao.selectPcIndexPricePageData(
//                PcIndexPricePo.tableNamePattern+inputDto.getYear(),inputDto.getAsset(),inputDto.getSymbol(),
//                inputDto.getCurrentPage(),inputDto.getPageSize());
//        PcIndexPriceListOutputDto outputDto=new PcIndexPriceListOutputDto();
//        outputDto.setTotal(pageData.getTotal());
//        List<PcIndexPriceInfoOutputDto> rows=new ArrayList<>();
//        for(PcIndexPricePo po:pageData.getRecords())
//        {
//            PcIndexPriceInfoOutputDto row=new PcIndexPriceInfoOutputDto();
//            row.setId(String.valueOf(po.getId()));
//            row.setAsset(po.getAsset());
//            row.setPrice(DecimalUtil.toTrimLiteral(po.getPrice()));
//            row.setSymbol(po.getSymbol());
//            row.setSourceValue(po.getSourceValue());
//            row.setCtime(String.valueOf(po.getCtime()));
//            row.setMtime(String.valueOf(po.getMtime()));
//            rows.add(row);
//        }
//        outputDto.setRows(rows);
//        return outputDto;
        return null;
    }
}
