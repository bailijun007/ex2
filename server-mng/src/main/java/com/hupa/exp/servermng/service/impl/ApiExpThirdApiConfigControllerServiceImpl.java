package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpThirdApiConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpThirdApiConfigPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.expthirdapiconfig.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiExpThirdApiConfigControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiExpThirdApiConfigControllerServiceImpl implements IApiExpThirdApiConfigControllerService {


    @Autowired
    private IExpThirdApiConfigDao iExpThirdApiConfigDao;


    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public ExpThirdApiConfigOutputDto createExpThirdApiConfig(ExpThirdApiConfigInputDto inputDto) throws BizException {

        ExpThirdApiConfigPo po = new ExpThirdApiConfigPo();
        po.setId(inputDto.getId());
        po.setApiModule(inputDto.getApiModule());
        po.setEnableFlag(inputDto.getEnableFlag());
        po.setLimitTime(inputDto.getLimitTime());
        po.setLimitCount(inputDto.getLimitCount());
        po.setThirdApiId(inputDto.getThirdApiId());
        po.setThirdApiName(inputDto.getThirdApiName());
        po.setTimeUnit(inputDto.getTimeUnit());
        po.setCtime(System.currentTimeMillis());
        po.setMtime(System.currentTimeMillis());
        iExpThirdApiConfigDao.insert(po);
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.ExpThirdApiConfig.toString(), OperationType.Insert.toString(),
                JSON.toJSONString(po), JSON.toJSONString(""));
        ExpThirdApiConfigOutputDto outputDto = new ExpThirdApiConfigOutputDto();
        return outputDto;
    }

    @Override
    public ExpThirdApiConfigOutputDto editExpThirdApiConfig(ExpThirdApiConfigInputDto inputDto) throws BizException {
        ExpThirdApiConfigPo beforePo = iExpThirdApiConfigDao.selectPoById(inputDto.getId());
        String beforeStr=JSON.toJSONString(beforePo);
        beforePo.setId(inputDto.getId());
        beforePo.setApiModule(inputDto.getApiModule());
        beforePo.setEnableFlag(inputDto.getEnableFlag());
        beforePo.setLimitTime(inputDto.getLimitTime());
        beforePo.setLimitCount(inputDto.getLimitCount());
        beforePo.setThirdApiId(inputDto.getThirdApiId());
        beforePo.setThirdApiName(inputDto.getThirdApiName());
        beforePo.setTimeUnit(inputDto.getTimeUnit());
        beforePo.setMtime(System.currentTimeMillis());
        iExpThirdApiConfigDao.updateById(beforePo);
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.ExpThirdApiConfig.toString(), OperationType.Update.toString(),
                beforeStr, JSON.toJSONString(beforePo));
        ExpThirdApiConfigOutputDto outputDto = new ExpThirdApiConfigOutputDto();
        return outputDto;
    }

    @Override
    public ExpThirdApiConfigInfoOutputDto getExpThirdApiConfigById(ExpThirdApiConfigInfoInputDto infoInputDto) throws BizException {
        ExpThirdApiConfigPo po = iExpThirdApiConfigDao.selectPoById(infoInputDto.getId());
        ExpThirdApiConfigInfoOutputDto outputDto = new ExpThirdApiConfigInfoOutputDto();
        if (po != null) {
            outputDto.setId(String.valueOf(po.getId()));
            outputDto.setApiModule(String.valueOf(po.getApiModule()));
            outputDto.setEnableFlag(String.valueOf(po.getEnableFlag()));
            outputDto.setLimitTime(String.valueOf(po.getLimitTime()));
            outputDto.setLimitCount(String.valueOf(po.getLimitCount()));
            outputDto.setThirdApiId(String.valueOf(po.getThirdApiId()));
            outputDto.setThirdApiName(po.getThirdApiName());
            outputDto.setTimeUnit(po.getTimeUnit());
            outputDto.setCtime(String.valueOf(po.getCtime()));
            outputDto.setMtime(String.valueOf(po.getMtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ExpThirdApiConfigListOutputDto getExpThirdApiConfigPageData(ExpThirdApiConfigListInputDto inputDto) throws BizException {
        IPage<ExpThirdApiConfigPo> pageData = iExpThirdApiConfigDao.selectPageData(inputDto.getCurrentPage(), inputDto.getPageSize());
        ExpThirdApiConfigListOutputDto outputDto = new ExpThirdApiConfigListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<ExpThirdApiConfigInfoOutputDto> rows = new ArrayList<>();
        pageData.getRecords().forEach(po -> {
            ExpThirdApiConfigInfoOutputDto row = new ExpThirdApiConfigInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setApiModule(String.valueOf(po.getApiModule()));
            row.setEnableFlag(String.valueOf(po.getEnableFlag()));
            row.setLimitTime(String.valueOf(po.getLimitTime()));
            row.setLimitCount(String.valueOf(po.getLimitCount()));
            row.setThirdApiId(String.valueOf(po.getThirdApiId()));
            row.setThirdApiName(po.getThirdApiName());
            row.setTimeUnit(po.getTimeUnit());
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            rows.add(row);
        });
        outputDto.setRows(rows);
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteExpThirdApiConfig(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpThirdApiConfigDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.ExpThirdApiConfig.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
