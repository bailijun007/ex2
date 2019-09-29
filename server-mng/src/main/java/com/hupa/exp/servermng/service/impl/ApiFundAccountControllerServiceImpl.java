package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizaccount.entity.MongoBo.FundAccountLogMongoBizBo;
import com.hupa.exp.bizaccount.entity.MongoBo.FundAccountLogMongoPageBizBo;
import com.hupa.exp.bizaccount.service.def.IFundAccountMongoBiz;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListInputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogListOutputDto;
import com.hupa.exp.servermng.entity.fundaccount.FundAccountLogOutputDto;
import com.hupa.exp.servermng.service.def.IApiFundAccountControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundAccountControllerServiceImpl implements IApiFundAccountControllerService {
    @Autowired
    private IFundAccountMongoBiz fundAccountMongoBiz;
    @Override
    public FundAccountLogListOutputDto getFundAccountLogList(FundAccountLogListInputDto inputDto) {
       FundAccountLogMongoPageBizBo pageBizBo= fundAccountMongoBiz.selectFundAccountLogPageData(
                inputDto.getSymbol(),inputDto.getId(),inputDto.getCurrentPage(),inputDto.getPageSize()
        );
        List<FundAccountLogOutputDto> rows=new ArrayList<>();
        for(FundAccountLogMongoBizBo bo:pageBizBo.getRows())
        {
            FundAccountLogOutputDto row= ConventObjectUtil.conventObject(bo,FundAccountLogOutputDto.class);
            rows.add(row);
        }
        FundAccountLogListOutputDto outputDto=new FundAccountLogListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(rows);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
