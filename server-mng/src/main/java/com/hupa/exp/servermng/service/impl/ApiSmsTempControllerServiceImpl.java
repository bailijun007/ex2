package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hupa.exp.base.helper.validate.IValidateService;
import com.hupa.exp.bizother.entity.ExpSmsListBizBo;
import com.hupa.exp.bizother.entity.ExpSmsTempBizBo;
import com.hupa.exp.bizother.service.sms.def.ISmsTempBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.sms.*;
import com.hupa.exp.servermng.service.def.IApiSmsTempControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiSmsTempControllerServiceImpl implements IApiSmsTempControllerService {

    @Autowired
    ISmsTempBiz iSmsTempBiz;
    @Autowired
    private IValidateService validateService;
    @Override
    public SmsTempOutputDto createSmsTemp(SmsTempInputDto inputDto) throws BizException {
        SmsTempOutputDto outputDto=new SmsTempOutputDto();
        String contentStr = "";
        try {
            contentStr = URLDecoder.decode(inputDto.getContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split(",");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setContent(JSON.toJSONString(map));

        validateService.validate(inputDto,true,true,false);
        ExpSmsTempBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpSmsTempBizBo.class);
        iSmsTempBiz.createUser(bo);
        return outputDto;
    }

    @Override
    public SmsTempOutputDto editSmsTemp(SmsTempInputDto inputDto) throws BizException {
        SmsTempOutputDto outputDto=new SmsTempOutputDto();
        String contentStr = "";
        try {
            contentStr = URLDecoder.decode(inputDto.getContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split(",");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setContent(JSON.toJSONString(map));
        validateService.validate(inputDto,true,true,false);
        ExpSmsTempBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpSmsTempBizBo.class);
        iSmsTempBiz.editUser(bo);
        return outputDto;
    }

    @Override
    public SmsTempListOutputDto querySmsTempList(SmsTempListInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,false);
        SmsTempListOutputDto outputDto=new SmsTempListOutputDto();
        ExpSmsListBizBo listBizBo=iSmsTempBiz.querySmsTempList(inputDto.getCurrentPage(),inputDto.getPageSize());
        //outputDto.setPageData(JSON.toJSONString(listBizBo, SerializerFeature.WriteNonStringValueAsString));
        outputDto.setPageData(listBizBo);
        return outputDto;
    }

    @Override
    public SmsTempInfoOutputDto querySmsTempById(SmsTempInfoInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,false);
        ExpSmsTempBizBo bo=iSmsTempBiz.querySmsTempById(inputDto.getId());
        SmsTempInfoOutputDto outputDto=ConventObjectUtil.conventObject(bo,SmsTempInfoOutputDto.class);
        return outputDto;
    }
}
