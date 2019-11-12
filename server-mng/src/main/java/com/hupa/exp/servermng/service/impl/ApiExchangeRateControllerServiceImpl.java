package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExchangeRateDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExchangeRatePo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.exchangerate.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiExchangeRateControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiExchangeRateControllerServiceImpl implements IApiExchangeRateControllerService {
    @Autowired
    private IExchangeRateDao iExchangeRateDao;
    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public ExchangeRateOutputDto createExchangeRate(ExchangeRateInputDto inputDto) throws BizException {
        ExchangeRatePo po= ConventObjectUtil.conventObject(inputDto,ExchangeRatePo.class);
        iExchangeRateDao.insert(po);
        ExchangeRateOutputDto outputDto=new ExchangeRateOutputDto();
        return outputDto;
    }

    @Override
    public ExchangeRateOutputDto editExchangeRate(ExchangeRateInputDto inputDto) throws BizException {
        ExchangeRatePo po= ConventObjectUtil.conventObject(inputDto,ExchangeRatePo.class);
        iExchangeRateDao.updateById(po);
        ExchangeRateOutputDto outputDto=new ExchangeRateOutputDto();
        return outputDto;
    }

    @Override
    public ExchangeRateInfoOutputDto getExchangeRateById(ExchangeRateInfoInputDto inputDto) throws BizException {
        ExchangeRatePo po= iExchangeRateDao.selectPoById(inputDto.getId());
        ExchangeRateInfoOutputDto outputDto=new ExchangeRateInfoOutputDto();
        outputDto.setTargetAsset(po.getTargetAsset());
        outputDto.setSourceAsset(po.getSourceAsset());
        outputDto.setAutoRefresh(po.isAutoRefresh()?"1":"0");
        outputDto.setExchangeRate(DecimalUtil.toTrimLiteral(po.getExchangeRate()));
        outputDto.setId(String.valueOf(po.getId()));
        return outputDto;
    }

    @Override
    public ExchangeRateListOutputDto queryExchangeRateList(ExchangeRateListInputDto inputDto) throws BizException {
        IPage<ExchangeRatePo> pageData=iExchangeRateDao.selectPageData(inputDto.getCurrentPage(),inputDto.getPageSize());
        ExchangeRateListOutputDto outputDto=new ExchangeRateListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<ExchangeRateInfoOutputDto> rows=new ArrayList<>();
        pageData.getRecords().forEach(row->{
            ExchangeRateInfoOutputDto info=new ExchangeRateInfoOutputDto();
            info.setExchangeRate(DecimalUtil.toTrimLiteral(row.getExchangeRate()));
            info.setSourceAsset(row.getSourceAsset());
            info.setTargetAsset(row.getTargetAsset());
            info.setId(String.valueOf(row.getId()));
            info.setAutoRefresh(row.isAutoRefresh()?"是":"否");
            info.setCtime(String.valueOf(row.getCtime()));
            info.setMtime(String.valueOf(row.getMtime()));
            rows.add(info);
        });
        outputDto.setRows(rows);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteExchangeRate(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExchangeRateDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.ExchangeRate.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
