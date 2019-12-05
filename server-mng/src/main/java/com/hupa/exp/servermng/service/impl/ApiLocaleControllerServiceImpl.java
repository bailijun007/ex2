package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.locale.ExpLocaleBizBo;
import com.hupa.exp.bizother.entity.locale.ExpLocaleListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.locale.def.ILocaleService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.locale.*;
import com.hupa.exp.servermng.enums.LocaleExceptionCode;
import com.hupa.exp.servermng.exception.LocaleException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiLocaleControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiLocaleControllerServiceImpl implements IApiLocaleControllerService {
    @Autowired
    private ILocaleService localeService;
    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public LocaleOutputDto createLocale(LocaleInputDto inputDto) throws BizException {

        String contentStr = "";
        try {
            contentStr = URLDecoder.decode(inputDto.getLocaleContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split("[┊]");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setLocaleContent(JSON.toJSONString(map));
        ExpLocaleBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpLocaleBizBo.class);
        bo.setCtime(System.currentTimeMillis());
        bo.setMtime(System.currentTimeMillis());
        localeService.createLocale(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Locale.toString(), OperationType.Insert.toString(),
                JSON.toJSONString(bo),"");
        LocaleOutputDto outputDto=new LocaleOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public LocaleOutputDto editLocale(LocaleInputDto inputDto) throws BizException {
        String contentStr = "";
        try {
            contentStr = URLDecoder.decode(inputDto.getLocaleContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split("[┊]");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setLocaleContent(JSON.toJSONString(map));

        ExpLocaleBizBo bo= localeService.getLocaleById(inputDto.getId());
        String before=JSON.toJSONString(bo);
        bo.setType(inputDto.getType());
        bo.setCode(inputDto.getCode());
        bo.setLocaleLanguage(inputDto.getLocaleLanguage());
        bo.setLocaleConstant(inputDto.getLocaleConstant());
        bo.setLocaleContent(inputDto.getLocaleContent());
        bo.setRemark(inputDto.getRemark());
        bo.setModule(inputDto.getModule());
        bo.setCtime(System.currentTimeMillis());
        bo.setMtime(System.currentTimeMillis());
        localeService.editLocale(bo);
        String after=JSON.toJSONString(bo);
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Locale.toString(), OperationType.Update.toString(),
                before,after);
        LocaleOutputDto outputDto=new LocaleOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public LocaleInfoOutputDto getLocaleById(LocaleInfoInputDto inputDto) throws BizException {
        ExpLocaleBizBo bo= localeService.getLocaleById(inputDto.getId());
        LocaleInfoOutputDto outputDto=new LocaleInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setType(String.valueOf(bo.getType()));
        outputDto.setCode(String.valueOf(bo.getCode()));
        outputDto.setLocaleLanguage(bo.getLocaleLanguage());
        outputDto.setLocaleConstant(bo.getLocaleConstant());
        outputDto.setLocaleContent(bo.getLocaleContent());
        outputDto.setRemark(bo.getRemark());
        outputDto.setModule(bo.getModule());
        outputDto.setCtime(String.valueOf(bo.getCtime()));
        outputDto.setMtime(String.valueOf(bo.getMtime()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public LocaleListOutputDto getLocaleList(LocaleListInputDto inputDto) throws BizException {
        ExpLocaleListBizBo listBizBo=localeService.getLocalePageData(inputDto.getModule(),inputDto.getErrorCode(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        LocaleListOutputDto outputDto=new LocaleListOutputDto();
        List<LocaleInfoOutputDto> rows=new ArrayList<>();
        for (ExpLocaleBizBo bo:listBizBo.getRows())
        {
            LocaleInfoOutputDto row=new LocaleInfoOutputDto();
            row.setId(String.valueOf(bo.getId()));
            row.setType(String.valueOf(bo.getType()));
            row.setCode(String.valueOf(bo.getCode()));
            row.setLocaleLanguage(bo.getLocaleLanguage());
            row.setLocaleConstant(bo.getLocaleConstant());
            row.setLocaleContent(bo.getLocaleContent());
            row.setRemark(bo.getRemark());
            row.setModule(bo.getModule());
            row.setCtime(String.valueOf(bo.getCtime()));
            row.setMtime(String.valueOf(bo.getMtime()));
            row.setTime(String.valueOf(System.currentTimeMillis()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }

    @Override
    public LocaleDeleteOutputDto deleteLocale(LocaleDeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Locale.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            localeService.deleteLocale(Integer.valueOf(id));
        }
        LocaleDeleteOutputDto outputDto=new LocaleDeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasLocaleOutputDto checkHasLocale(CheckHasLocaleInputDto inputDto) throws BizException {
        if(localeService.getOneLocale(inputDto.getErrorCode())!=null)
            throw new LocaleException(LocaleExceptionCode.VALIDATE_HAS_CODE_ERROR);
        CheckHasLocaleOutputDto outputDto=new CheckHasLocaleOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
