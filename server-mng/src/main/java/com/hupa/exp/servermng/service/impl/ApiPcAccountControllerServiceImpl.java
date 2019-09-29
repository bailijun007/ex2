package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.bizaccount.entity.MongoBo.PcAccountLogMongoBizBo;
import com.hupa.exp.bizaccount.entity.MongoBo.PcAccountLogMongoPageBizBo;
import com.hupa.exp.bizaccount.service.def.IPcAccountMongoBiz;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListInputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogListOutputDto;
import com.hupa.exp.servermng.entity.pcaccount.PcAccountLogOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcAccountControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcAccountControllerServiceImpl implements IApiPcAccountControllerService {
    @Autowired
    private IPcAccountMongoBiz pcAccountMongoBiz;

    @Override
    public PcAccountLogListOutputDto getPcAccountLogList(PcAccountLogListInputDto inputDto) {
        PcAccountLogMongoPageBizBo pageBizBo= pcAccountMongoBiz.selectPcAccountLogPageData(
                inputDto.getSymbol(),inputDto.getId(),inputDto.getCurrentPage(),inputDto.getPageSize()
        );
        List<PcAccountLogOutputDto> rows=new ArrayList<>();
        for(PcAccountLogMongoBizBo bo:pageBizBo.getRows())
        {
           PcAccountLogOutputDto row= ConventObjectUtil.conventObject(bo,PcAccountLogOutputDto.class);
            rows.add(row);
        }
        PcAccountLogListOutputDto outputDto=new PcAccountLogListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(inputDto.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(rows);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
