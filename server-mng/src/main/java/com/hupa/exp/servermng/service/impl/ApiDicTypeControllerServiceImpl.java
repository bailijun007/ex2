package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.ExpDicTypeBizBo;
import com.hupa.exp.bizother.entity.ExpDicTypeListBizBo;
import com.hupa.exp.bizother.entity.ExpUserBizBo;
import com.hupa.exp.bizother.service.dictype.def.IDicTypeService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dictype.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiDicTypeControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiDicTypeControllerServiceImpl implements IApiDicTypeControllerService {
    @Autowired
    private IDicTypeService service;
    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public DicTypeListOutputDto queryDicTypeList(DicTypeListInputDto inputDto) throws BizException {
        ExpDicTypeListBizBo listBizBo=service.queryDicTypeList(inputDto.getCurrentPage(),inputDto.getPageSize());
        DicTypeListOutputDto outputDto=new DicTypeListOutputDto();
        List<DicTypeInfoOutputDto> rows=new ArrayList<>();
        for(ExpDicTypeBizBo bo:listBizBo.getRows())
        {
            DicTypeInfoOutputDto row=new DicTypeInfoOutputDto();
            row.setId(String.valueOf(bo.getId()));
            row.setValue(bo.getValue());
            row.setKey(bo.getKey());
            rows.add(row);
        }
        outputDto.setRows(rows);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicTypeOutputDto createDicType(DicTypeInputDto inputDto) throws BizException {
        ExpDicTypeBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpDicTypeBizBo.class);
        service.createDicType(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.DicType.toString(), OperationType.Insert.toString(),
                JSON.toJSONString(bo),"");
        DicTypeOutputDto outputDto=new DicTypeOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicTypeOutputDto editDicType(DicTypeInputDto inputDto) throws BizException {
        ExpDicTypeBizBo bo= service.queryDicTypeById(inputDto.getId());
        String before=JSON.toJSONString(bo);
        bo.setValue(inputDto.getValue());
        bo.setKey(inputDto.getKey());
        service.editDicType(bo);
        String after=JSON.toJSONString(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.DicType.toString(), OperationType.Insert.toString(),
                before,after);
        DicTypeOutputDto outputDto=new DicTypeOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicTypeInfoOutputDto getDicTypeById(DicTypeInfoInputDto inputDto) throws BizException {
        ExpDicTypeBizBo bo= service.queryDicTypeById(inputDto.getId());
        DicTypeInfoOutputDto outputDto=new DicTypeInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setValue(bo.getValue());
        outputDto.setKey(bo.getKey());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicTypeAllListOutputDto getDicTypeAllList(DicTypeAllListInputDto inputDto) throws BizException {
        List<ExpDicTypeBizBo> listBizBo=service.queryAllDicType();
        DicTypeAllListOutputDto outputDto=new DicTypeAllListOutputDto();
        List<DicTypeInfoOutputDto> rows=new ArrayList<>();
        for(ExpDicTypeBizBo bo:listBizBo)
        {
            DicTypeInfoOutputDto row=new DicTypeInfoOutputDto();
            row.setId(String.valueOf(bo.getId()));
            row.setValue(bo.getValue());
            row.setKey(bo.getKey());
            rows.add(row);
        }
        outputDto.setDicTypeList(rows);
        return outputDto;

    }

    @Override
    public DeleteOutputDto deleteDicType(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.DicType.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            service.deleteById(Long.parseLong(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
