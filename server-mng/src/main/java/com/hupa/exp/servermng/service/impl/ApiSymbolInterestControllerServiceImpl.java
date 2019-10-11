package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daoex2.dao.expv2.def.IPcSymbolInterestDao;
import com.hupa.exp.daoex2.entity.po.expv2.PcSymbolInterestPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.symbolinterest.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiSymbolInterestControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiSymbolInterestControllerServiceImpl implements IApiSymbolInterestControllerService {

    @Autowired
    private IPcSymbolInterestDao iPcSymbolInterestDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public SymbolInterestOutputDto createOrEditSymbolInterest(SymbolInterestInputDto inputDto) throws BizException {
        PcSymbolInterestPo po= ConventObjectUtil.conventObject(inputDto,PcSymbolInterestPo.class);
        if(po.getId()>0)
        {
            po.setMtime(System.currentTimeMillis());
            iPcSymbolInterestDao.updateById(po);
        }
        else
        {
            po.setCtime(System.currentTimeMillis());
            po.setMtime(System.currentTimeMillis());
            iPcSymbolInterestDao.insert(po);
        }
        SymbolInterestOutputDto outputDto=new SymbolInterestOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public SymbolInterestInfoOutputDto getSymbolInterest(SymbolInterestInfoInputDto inputDto) throws BizException {
        PcSymbolInterestPo po=iPcSymbolInterestDao.selectPoById(inputDto.getId());
        SymbolInterestInfoOutputDto outputDto=new SymbolInterestInfoOutputDto();
        outputDto.setId(po.getId());
        outputDto.setCtime(po.getCtime());
        outputDto.setMtime(po.getMtime());
        outputDto.setSymbol(po.getSymbol());
        outputDto.setSymbolInterest(po.getSymbolInterest());
        outputDto.setInterestTime(po.getInterestTime());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public SymbolInterestListOutputDto getSymbolInterestList(SymbolInterestListInputDto inputDto) throws BizException {
        SymbolInterestListOutputDto outputDto=new SymbolInterestListOutputDto();
        IPage<PcSymbolInterestPo> listBizBo=iPcSymbolInterestDao.selectPcSymbolInterestPage(inputDto.getSymbol(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<SymbolInterestInfoOutputDto> rows=new ArrayList<>();
        for(PcSymbolInterestPo po:listBizBo.getRecords())
        {
            SymbolInterestInfoOutputDto row= ConventObjectUtil.conventObject(po,SymbolInterestInfoOutputDto.class);
            rows.add(row);
        }
        outputDto.setRows(rows);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteSymbolInterest(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iPcSymbolInterestDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Dic.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;

    }
}
