package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcSymbolRateDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcSymbolRatePo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbolrate.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiSymbolRateControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiSymbolRateControllerServiceImpl implements IApiSymbolRateControllerService {

    @Autowired
    private IPcSymbolRateDao iPcSymbolRateDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public SymbolRateOutputDto createOrEditSymbolRate(SymbolRateInputDto inputDto) throws BizException {
        PcSymbolRatePo po= ConventObjectUtil.conventObject(inputDto,PcSymbolRatePo.class);
        if(po.getId()>0)
        {
            po.setMtime(System.currentTimeMillis());
            iPcSymbolRateDao.updateById(po);
        }
        else
        {
            po.setCtime(System.currentTimeMillis());
            po.setMtime(System.currentTimeMillis());
            iPcSymbolRateDao.insert(po);
        }
        SymbolRateOutputDto outputDto=new SymbolRateOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public SymbolRateInfoOutputDto getSymbolRate(SymbolRateInfoInputDto inputDto) throws BizException {
        PcSymbolRatePo po= iPcSymbolRateDao.selectPoById(inputDto.getId());
        SymbolRateInfoOutputDto outputDto=new SymbolRateInfoOutputDto();
        outputDto.setId(po.getId());
        outputDto.setAsset(po.getAsset());
        outputDto.setSymbol(po.getSymbol());
        outputDto.setBaseRate(po.getBaseRate());
        outputDto.setValuationRate(po.getValuationRate());
        outputDto.setRateTime(po.getRateTime());
        outputDto.setCtime(po.getCtime());
        outputDto.setMtime(po.getMtime());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 查询利率列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public SymbolRateListOutputDto getSymbolRateList(SymbolRateListInputDto inputDto) throws BizException {
        SymbolRateListOutputDto outputDto=new SymbolRateListOutputDto();
        IPage<PcSymbolRatePo> listBizBo= iPcSymbolRateDao.selectPosPage(inputDto.getAsset(),inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<SymbolRateInfoOutputDto> rows=new ArrayList<>();
        for(PcSymbolRatePo po:listBizBo.getRecords())
        {
            SymbolRateInfoOutputDto row= ConventObjectUtil.conventObject(po,SymbolRateInfoOutputDto.class);
            rows.add(row);
        }
        outputDto.setRows(rows);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteSymbolRate(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iPcSymbolRateDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.SymbolRate.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;

    }
}
