package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserApiDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserApiPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.userapi.UserApiInfoOutputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListInputDto;
import com.hupa.exp.servermng.entity.userapi.UserApiListOutputDto;
import com.hupa.exp.servermng.service.def.IApiUserApiControllerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiUserApiControllerServiceImpl implements IApiUserApiControllerService {

    @Autowired
    private IExpUserApiDao iExpUserApiDao;
    @Autowired
    private IExpUserDao iExpUserDao;
    @Override
    public UserApiListOutputDto getUserApiPageData(UserApiListInputDto inputDto) throws BizException {
        if(!StringUtils.isEmpty(inputDto.getUserName()))
        {
            ExpUserPo userPo=iExpUserDao.selectUserByAccount(inputDto.getUserName());
            if(userPo!=null)
                inputDto.setAccountId(userPo.getId());
        }
        IPage<ExpUserApiPo> pageData= iExpUserApiDao.selectUserApiPageData(inputDto.getAccountId(),inputDto.getCurrentPage(),inputDto.getPageSize());
        UserApiListOutputDto outputDto=new UserApiListOutputDto();
        List<UserApiInfoOutputDto> rows=new ArrayList<>();
        for(ExpUserApiPo po:pageData.getRecords())
        {
            UserApiInfoOutputDto row=new UserApiInfoOutputDto();

            row.setApiKey(po.getApiKey());
            row.setApiName(po.getApiName());
            row.setApiSecret(po.getApiSecret());
            row.setBindIp(po.getBindIp());
            row.setId(String.valueOf(po.getId()));
            row.setPurview(String.valueOf(po.getPurview()));
            row.setStatus(String.valueOf(po.getStatus()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            row.setUserId(String.valueOf(po.getUserId()));
            ExpUserPo userPo=iExpUserDao.selectPoById(po.getUserId());
            if(userPo!=null)
                row.setUserName(userPo.getPhone()==null?userPo.getEmail():userPo.getPhone());
            row.setRemark(po.getRemark());
            rows.add(row);
        }
        outputDto.setRows(rows);
        outputDto.setTotal(pageData.getTotal());

        return outputDto;
    }
}
