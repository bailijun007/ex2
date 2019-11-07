package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpAppVersionDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpAppVersionPo;
import com.hupa.exp.servermng.entity.appversion.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAppVersionControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiAppVersionControllerServiceImpl implements IApiAppVersionControllerService {
    @Autowired
    private IExpAppVersionDao iExpAppVersionDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public AppVersionOutputDto createOrUpdateAppVersion(AppVersionInputDto inputDto) throws BizException {
        ExpAppVersionPo po= ConventObjectUtil.conventObject(inputDto,ExpAppVersionPo.class);
        if(inputDto.getId()>0)
        {
            ExpAppVersionPo beforePo=iExpAppVersionDao.selectPoById(inputDto.getId());
            po.setMtime(System.currentTimeMillis());
            iExpAppVersionDao.updateById(po);
            ExpUserBizBo user=sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.AppVersion.toString(), OperationType.Update.toString(),
                    JSON.toJSONString(beforePo==null?"":beforePo),JSON.toJSONString(po));
        }
        else
        {
            po.setMtime(System.currentTimeMillis());
            po.setCtime(System.currentTimeMillis());
            iExpAppVersionDao.insert(po);
            ExpUserBizBo user=sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.AppVersion.toString(), OperationType.Insert.toString(),
                   "",JSON.toJSONString(po));
        }

        AppVersionOutputDto outputDto=new AppVersionOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public AppVersionInfoOutputDto getAppVersionById(AppVersionInfoInputDto InputDto) throws BizException {
        ExpAppVersionPo po= iExpAppVersionDao.selectPoById(InputDto.getId());
        AppVersionInfoOutputDto outputDto=new AppVersionInfoOutputDto();
        if(po!=null)
        {
            outputDto.setId(String.valueOf(po.getId()));
            outputDto.setRemark(po.getRemark());
            outputDto.setType(String.valueOf(po.getType()));
            outputDto.setVersion(po.getVersion());
            outputDto.setUpdateContent(po.getUpdateContent());
            outputDto.setLinkUrl(po.getLinkUrl());
            outputDto.setReleaseTime(String.valueOf(po.getReleaseTime()));
            outputDto.setForcedUpdate(po.isForcedUpdate()?"1":"0");
        }
        return outputDto;
    }

    @Override
    public AppVersionListOutputDto getAppVersionPageData(AppVersionListInputDto inputDto) throws BizException {
        IPage<ExpAppVersionPo> pageData=iExpAppVersionDao.selectPosList(inputDto.getType(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        AppVersionListOutputDto outputDto=new AppVersionListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        List<AppVersionInfoOutputDto> rows=new ArrayList<>();
        for(ExpAppVersionPo po:pageData.getRecords())
        {
            AppVersionInfoOutputDto row=new AppVersionInfoOutputDto();
            row.setId(String.valueOf(po.getId()));
            row.setRemark(po.getRemark());
            row.setType(String.valueOf(po.getType()));
            row.setVersion(po.getVersion());
            row.setForcedUpdate(po.getUpdateContent());
            row.setLinkUrl(po.getLinkUrl());
            row.setReleaseTime(String.valueOf(po.getReleaseTime()));
            row.setForcedUpdate(po.isForcedUpdate()?"1":"0");
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteAppVersion(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpAppVersionDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.AppVersion.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
