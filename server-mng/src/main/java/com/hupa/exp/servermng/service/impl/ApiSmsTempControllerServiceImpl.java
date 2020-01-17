package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.base.helper.validate.IValidateService;
import com.hupa.exp.bizother.entity.sms.ExpSmsListBizBo;
import com.hupa.exp.bizother.entity.sms.ExpSmsTempBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.sms.def.ISmsTempBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpSmsTempDao;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.sms.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiSmsTempControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiSmsTempControllerServiceImpl implements IApiSmsTempControllerService {

    private Logger logger = LoggerFactory.getLogger(ApiSmsTempControllerServiceImpl.class);

    @Autowired
    private ISmsTempBiz iSmsTempBiz;

    @Autowired
    private IValidateService validateService;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpSmsTempDao iExpSmsTempDao;

    /**
     * 创建通知模板
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public SmsTempOutputDto createSmsTemp(SmsTempInputDto inputDto) throws BizException {
        SmsTempOutputDto outputDto=new SmsTempOutputDto();
        String contentStr = "";
        try {
            contentStr = URLDecoder.decode(inputDto.getContent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.info("ApiSmsTempControllerServiceImpl createSmsTemp Exception:"+e.getMessage());
        }
        String[] contents= contentStr.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents) {
            String[] keyValue=str.split("[┊]");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setContent(JSON.toJSONString(map));

        validateService.validate(inputDto,true,true,false);
        ExpSmsTempBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpSmsTempBizBo.class);
        bo.setCtime(System.currentTimeMillis());
        bo.setMtime(System.currentTimeMillis());
        iSmsTempBiz.createUser(bo);
        return outputDto;
    }

    /**
     * 修改通知模板
     * @param inputDto
     * @return
     * @throws BizException
     */
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
            String[] keyValue=str.split("[┊]");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        inputDto.setContent(JSON.toJSONString(map));
        validateService.validate(inputDto,true,true,false);
        ExpSmsTempBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpSmsTempBizBo.class);
        ExpSmsTempBizBo beforeBo=iSmsTempBiz.querySmsTempById(inputDto.getId());
        bo.setMtime(System.currentTimeMillis());
        iSmsTempBiz.editUser(bo);
        //记日志
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.DicType.toString(), OperationType.Update.toString(),
                JSON.toJSONString(beforeBo==null?"":beforeBo),JSON.toJSONString(bo));
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

    /**
     * 删除通知模板
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public DeleteOutputDto deleteSmsTemp(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpSmsTempDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.SmsTemp.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
