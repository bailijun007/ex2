package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.ExpDicBizBo;
import com.hupa.exp.bizother.entity.ExpDicListBizBo;
import com.hupa.exp.bizother.entity.ExpUserBizBo;
import com.hupa.exp.bizother.service.dic.def.IDicService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dic.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiDicControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApiDicControllerServiceImpl implements IApiDicControllerService {
    @Autowired
    private IDicService dicService;



    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public DicAllListOutputDto queryDicListByType(DicAllListInputDto inputDto) throws BizException {
        DicAllListOutputDto outputDto=new DicAllListOutputDto();
        ExpDicBizBo dicTypeBizBo =dicService.queryDicByKey(inputDto.getTypekey());
        if(dicTypeBizBo==null)
            return outputDto;
        List<ExpDicBizBo> boList=dicService.queryDicListByType(Integer.valueOf(String.valueOf(dicTypeBizBo.getId())));
        List<DicInfoOutputDto> pageList=new ArrayList<>();
        for(ExpDicBizBo bo:boList)
        {
            DicInfoOutputDto pageData=new DicInfoOutputDto();
            pageData.setId(String.valueOf(bo.getId()));
            pageData.setKey(bo.getKey());
            pageData.setValue(bo.getValue());
            pageData.setDicType(String.valueOf(bo.getDicType()));
            pageList.add(pageData);
        }

        outputDto.setDicList(pageList);
        return outputDto;
    }

    @Override
    public DicOutputDto createDic(DicInputDto inputDto) throws BizException {
        ExpDicBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpDicBizBo.class);
        dicService.createDic(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Dic.toString(), OperationType.Insert.toString(),
                JSON.toJSONString(bo),JSON.toJSONString(""));
        DicOutputDto outputDto=new DicOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicOutputDto editDic(DicInputDto inputDto) throws BizException {
        ExpDicBizBo bo=dicService.queryDicById(inputDto.getId());// ConventObjectUtil.conventObject(inputDto,ExpDicBizBo.class);
        String before=JSON.toJSONString(bo);
        bo.setDicType(inputDto.getDicType());
        bo.setKey(inputDto.getKey());
        bo.setValue(inputDto.getValue());
        String after=JSON.toJSONString(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Dic.toString(), OperationType.Update.toString(),
                before,after);
        dicService.editDic(bo);
        DicOutputDto outputDto=new DicOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicInfoOutputDto getDicById(DicInfoInputDto inputDto) throws BizException {
        ExpDicBizBo bo= dicService.queryDicById(inputDto.getId());
        DicInfoOutputDto outputDto=new DicInfoOutputDto();
        outputDto.setDicType(String.valueOf(bo.getDicType()));
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setKey(bo.getKey());
        outputDto.setValue(bo.getValue());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DicListOutputDto queryDicList(DicListInputDto inputDto) throws BizException {
        ExpDicListBizBo boList=dicService.queryDicList(inputDto.getType(),inputDto.getCurrentPage(),inputDto.getPageSize());
        List<DicInfoOutputDto> pageList=new ArrayList<>();
        for(ExpDicBizBo bo:boList.getRows())
        {
            DicInfoOutputDto pageData=new DicInfoOutputDto();
            pageData.setId(String.valueOf(bo.getId()));
            pageData.setKey(bo.getKey());
            pageData.setValue(bo.getValue());
            pageData.setDicType(String.valueOf(bo.getDicType()));
            pageList.add(pageData);
        }
        DicListOutputDto outputDto=new DicListOutputDto();
        outputDto.setRows(pageList);
        outputDto.setTotal(boList.getTotal());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteDic(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            dicService.deleteById(Long.parseLong(id));
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
