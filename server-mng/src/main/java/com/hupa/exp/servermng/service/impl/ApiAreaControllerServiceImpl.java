package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.base.helper.validate.IValidateService;
import com.hupa.exp.bizother.entity.area.ExpAreaBizBo;
import com.hupa.exp.bizother.entity.area.ExpAreaListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.area.def.IAreaService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpAreaDao;
import com.hupa.exp.servermng.entity.area.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAreaControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiAreaControllerServiceImpl implements IApiAreaControllerService {
    @Autowired
    private IAreaService iAreaService;
    @Autowired
    private IValidateService validateService;
    @Autowired
    private IExpAreaDao iExpAreaDao;


    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;
    @Override
    public AreaOutputDto createArea(AreaInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,true);
        ExpAreaBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpAreaBizBo.class);
        iAreaService.createArea(bo);
        AreaOutputDto outputDto=new AreaOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public AreaOutputDto editArea(AreaInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,true);
        ExpAreaBizBo bo= ConventObjectUtil.conventObject(inputDto,ExpAreaBizBo.class);
        iAreaService.editArea(bo);
        AreaOutputDto outputDto=new AreaOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public GetAreaOutputDto getAreaById(GetAreaInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,true);
        ExpAreaBizBo bo= iAreaService.queryAreaById(inputDto.getId());
        GetAreaOutputDto outputDto= new GetAreaOutputDto();
        outputDto.setAreaCode(bo.getAreaCode());
        outputDto.setAreaName(bo.getAreaName());
        outputDto.setEnable(bo.isEnable()?"1":"0");
        outputDto.setId(String.valueOf(bo.getId()));
        return outputDto;
    }

    @Override
    public AreaListOutputDto getAreaList(AreaListInputDto inputDto) throws BizException {
        validateService.validate(inputDto,true,true,true);
        ExpAreaListBizBo listBizBo=iAreaService.queryAreaList(inputDto.getCurrentPage(),inputDto.getPageSize());
        AreaListOutputDto outputDto=new AreaListOutputDto();
        List<AreaListOutputPage> areaList=new ArrayList<>();
        for(ExpAreaBizBo bo:listBizBo.getRows())
        {
            AreaListOutputPage area=new AreaListOutputPage();
            area.setAreaCode(bo.getAreaCode());
            area.setAreaName(bo.getAreaName());
            area.setEnable(bo.isEnable()?"1":"0");
            area.setId(String.valueOf(bo.getId()));
            areaList.add(area);
        }
        outputDto.setRows(areaList);
        outputDto.setTotal(listBizBo.getTotal());
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteArea(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpAreaDao.deleteById(Long.parseLong(id));
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
