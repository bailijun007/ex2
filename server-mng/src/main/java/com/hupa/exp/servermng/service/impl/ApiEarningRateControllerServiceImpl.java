package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcEarningRateDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcEarningRatePo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.earningrate.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiEarningRateControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiEarningRateControllerServiceImpl implements IApiEarningRateControllerService {
    @Autowired
    private IPcEarningRateDao iPcEarningRateDao;
    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpOperationLogService logService;


    @Override
    public PcEarningRatePageDataOutputDto queryEarningRatePageData(PcEarningRatePageDataInputDto inputDto) throws BizException {
        IPage<PcEarningRatePo> pageData=iPcEarningRateDao.selectPcFeePageData(inputDto.getAccount(),inputDto.getRateTime(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        PcEarningRatePageDataOutputDto outputDto=new PcEarningRatePageDataOutputDto();
        outputDto.setTotal(pageData.getTotal());
        outputDto.setRows(pageData.getRecords());
        outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString,
                SerializerFeature.WriteBigDecimalAsPlain));
        return outputDto;
    }

    @Override
    public PcEarningRateOutputDto createEarningRate(PcEarningRateInputDto inputDto) throws BizException {
        PcEarningRatePo po=new PcEarningRatePo();
        po.setAccount(inputDto.getAccount());
        po.setCtime(System.currentTimeMillis());
        po.setEarningRate(inputDto.getEarningRate());
        po.setMtime(System.currentTimeMillis());
        po.setPair(inputDto.getPair());
        po.setSort(inputDto.getSort());
        po.setSymbol(inputDto.getSymbol());
        po.setEarningRateTime(inputDto.getEarningRateTime());
        iPcEarningRateDao.insert(po);
        PcEarningRateOutputDto outputDto=new PcEarningRateOutputDto();
        return outputDto;
    }

    @Override
    public PcEarningRateOutputDto editEarningRate(PcEarningRateInputDto inputDto) throws BizException {
        PcEarningRatePo po=iPcEarningRateDao.selectPoById(inputDto.getId());
        PcEarningRateOutputDto outputDto=new PcEarningRateOutputDto();
        if(po==null)
            return outputDto;
        po.setAccount(inputDto.getAccount());
        po.setCtime(System.currentTimeMillis());
        po.setEarningRate(inputDto.getEarningRate());
        po.setMtime(System.currentTimeMillis());
        po.setPair(inputDto.getPair());
        po.setSort(inputDto.getSort());
        po.setSymbol(inputDto.getSymbol());
        po.setEarningRateTime(inputDto.getEarningRateTime());
        iPcEarningRateDao.updateById(po);

        return outputDto;
    }

    @Override
    public PcEarningRateInfoOutputDto getEarningRateById(PcEarningRateInfoInputDto inputDto) throws BizException {
        PcEarningRateInfoOutputDto outputDto=new PcEarningRateInfoOutputDto();
        PcEarningRatePo po=iPcEarningRateDao.selectPoById(inputDto.getId());
    if(po!=null)
        outputDto= ConventObjectUtil.conventObject(po,PcEarningRateInfoOutputDto.class);

        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteEarningRate(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.EarningRate.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iPcEarningRateDao.deleteById(Long.parseLong(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasEarningRateOutputDto checkHasEarningRate(CheckHasEarningRateInputDto inputDto) throws BizException {
        CheckHasEarningRateOutputDto outputDto=new CheckHasEarningRateOutputDto();
        outputDto.setHasEarningRate(false);
        if(iPcEarningRateDao.selectOneByParam(inputDto.getAccount(),inputDto.getRateTime())!=null)
        {
            outputDto.setHasEarningRate(true);
        }
        return outputDto;
    }
}
