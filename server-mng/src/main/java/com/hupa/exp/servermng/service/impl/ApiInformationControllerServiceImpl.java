package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.information.ExpInformationBizBo;
import com.hupa.exp.bizother.entity.information.ExpInformationPageDataBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.information.def.IInformationService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.information.*;
import com.hupa.exp.servermng.help.OSSClientUtil;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiInformationControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiInformationControllerServiceImpl implements IApiInformationControllerService {

    @Autowired
    private IInformationService informationService;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @Override
    public GetInformationByTypeOutputDto getInformationByType(GetInformationByTypeInputDto inputDto) throws BizException {
        ExpInformationPageDataBizBo bizBo=informationService.getInformationPageData(inputDto.getType(),inputDto.getTitle(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<GetInformationInfoOutputDto> rows=new ArrayList<>();
        for(ExpInformationBizBo po:bizBo.getRows())
        {
            GetInformationInfoOutputDto row=new GetInformationInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setTitle(po.getTitle());
            row.setCoverImg(po.getCoverImg());
            row.setContent(po.getContent());
            row.setType(String.valueOf(po.getType()));
            row.setLinkUrl(po.getLinkUrl());
            row.setSort(String.valueOf(po.getSort()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            rows.add(row);
        }
        GetInformationByTypeOutputDto outputDto=new GetInformationByTypeOutputDto();
        outputDto.setRows(rows);
        outputDto.setTotal(bizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }

    @Override
    public GetInformationInfoOutputDto getInformationInfo(GetInformationInfoInputDto inputDto) throws BizException {
        ExpInformationBizBo po=informationService.getInformationById(inputDto.getId());
        GetInformationInfoOutputDto outputDto=new GetInformationInfoOutputDto();
        if(po!=null)
        {
            outputDto.setId(String.valueOf(po.getId()));
            outputDto.setTitle(po.getTitle());
            outputDto.setCoverImg(po.getCoverImg());
            outputDto.setContent(po.getContent());
            outputDto.setType(String.valueOf(po.getType()));
            outputDto.setLinkUrl(po.getLinkUrl());
            outputDto.setSort(String.valueOf(po.getSort()));
            outputDto.setCtime(String.valueOf(po.getCtime()));
            outputDto.setMtime(String.valueOf(po.getMtime()));
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public InformationOutputDto createOrEditInformation(InformationInputDto inputDto) throws BizException {
        ExpInformationBizBo bizBo= ConventObjectUtil.conventObject(inputDto,ExpInformationBizBo.class);

        String contentStr = "";
        String titleStr="";
        try {
            contentStr = URLDecoder.decode(inputDto.getContent(), "UTF-8");
            titleStr=URLDecoder.decode(inputDto.getTitle(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> contentMap=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split("[┊]");
            contentMap.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        bizBo.setContent(JSON.toJSONString(contentMap));
        String[] titles= titleStr.split("[|]");
        Map<String,String> titleMap=new HashMap<>();
        for(String str:titles)
        {
            String[] keyValue=str.split("[┊]");
            titleMap.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        bizBo.setTitle(JSON.toJSONString(titleMap));
        if(inputDto.getId()>0)
        {
            bizBo.setMtime(System.currentTimeMillis());
            informationService.editInformation(bizBo);
            ExpInformationBizBo beforeBo=informationService.getInformationById(inputDto.getId());
                ExpUserBizBo user=sessionHelper.getUserInfoBySession();
                logService.createOperationLog(user.getId(),user.getUserName(),
                        OperationModule.DicType.toString(), OperationType.Update.toString(),
                        JSON.toJSONString(beforeBo==null?"":beforeBo),JSON.toJSONString(bizBo));

        }
        else
        {
            bizBo.setMtime(System.currentTimeMillis());
            bizBo.setCtime(System.currentTimeMillis());
            informationService.createInformation(bizBo);
        }
        if(!StringUtils.isEmpty(inputDto.getOldImg()))
        {
            String[] strArr=inputDto.getOldImg().split("/");
            ossClientUtil.deleteFile(strArr[strArr.length-1]);//把旧图片删掉
        }
        InformationOutputDto outputDto=new InformationOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteInformation(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Information.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            informationService.deleteInformation(Long.parseLong(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
