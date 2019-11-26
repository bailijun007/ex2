package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpFundRateSettingDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpFundRateSettingPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.fundrate.*;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiFundRateSettingControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundRateSettingControllerServiceImpl implements IApiFundRateSettingControllerService {

    @Autowired
    private IExpFundRateSettingDao iExpFundRateSettingDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public FundRateSettingOutputDto createFundRateSetting(FundRateSettingInputDto inputDto) throws BizException {
        if(inputDto.getCollectNum()>4)
            throw new MngException(MngExceptionCode.COLLECT_FEE_NUM_ERROR);

        List<ExpFundRateSettingPo> pos= iExpFundRateSettingDao.selectSettingByStatus(1);
        for(ExpFundRateSettingPo po:pos)
        {
            po.setStatus(0);
            iExpFundRateSettingDao.updateById(po);
        }

        ExpFundRateSettingPo po= ConventObjectUtil.conventObject(inputDto,ExpFundRateSettingPo.class);
        po.setCtime(System.currentTimeMillis());
        po.setMtime(System.currentTimeMillis());
        po.setStatus(1);
        iExpFundRateSettingDao.insert(po);

        FundRateSettingOutputDto outputDto=new FundRateSettingOutputDto();
        return outputDto;
    }

    @Override
    public FundRateSettingOutputDto editFundRateSetting(FundRateSettingInputDto inputDto) throws BizException {

        ExpFundRateSettingPo oldPo= iExpFundRateSettingDao.selectPoById(inputDto.getId());
        String before= JSON.toJSONString(oldPo);
        oldPo.setStatus(0);
        oldPo.setMtime(System.currentTimeMillis());
        iExpFundRateSettingDao.updateById(oldPo);
        ExpFundRateSettingPo newPo=new ExpFundRateSettingPo();
        newPo.setStatus(1);
        newPo.setCtime(System.currentTimeMillis());
        newPo.setMtime(System.currentTimeMillis());
        newPo.setCollectNum(inputDto.getCollectNum());
        newPo.setCollectFirstTime(inputDto.getCollectFirstTime());
        newPo.setMaxFundRate(inputDto.getMaxFundRate());
        newPo.setMinFundRate(inputDto.getMinFundRate());
        iExpFundRateSettingDao.insert(newPo);
        //记日志
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.FundRateSetting.toString(), OperationType.Update.toString(),
                before,JSON.toJSONString(newPo));
        FundRateSettingOutputDto outputDto=new FundRateSettingOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public FundRateSettingInfoOutputDto getFundRateSettingById(FundRateSettingInfoInputDto inputDto) throws BizException {
        ExpFundRateSettingPo po= iExpFundRateSettingDao.selectPoById(inputDto.getId());
        FundRateSettingInfoOutputDto outputDto=ConventObjectUtil.conventObject(po,FundRateSettingInfoOutputDto.class);
        return outputDto;
    }

    @Override
    public FundRateSettingListOutputDto getFundRateSettingList(FundRateSettingListInputDto inputDto) throws BizException {
        IPage<ExpFundRateSettingPo> pageData= iExpFundRateSettingDao.selectSettingPageData(1,inputDto.getCurrentPage(),inputDto.getPageSize());
        FundRateSettingListOutputDto outputDto=new FundRateSettingListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        outputDto.setTotalCount(pageData.getTotal());
        List<FundRateSettingInfoOutputDto> rows=new ArrayList<>();
        for(ExpFundRateSettingPo po:pageData.getRecords())
        {
            FundRateSettingInfoOutputDto row=new FundRateSettingInfoOutputDto();
            row.setStatus(String.valueOf(po.getStatus()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            row.setCollectNum(String.valueOf(po.getCollectNum()));
            row.setCollectFirstTime(String.valueOf(po.getCollectFirstTime()));
            row.setMinFundRate(DecimalUtil.toTrimLiteral(po.getMinFundRate()));
            row.setMaxFundRate(DecimalUtil.toTrimLiteral(po.getMaxFundRate()));
            row.setId(String.valueOf(po.getId()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteFundRateSetting(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpFundRateSettingDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.FundRateSetting.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
