package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcPosPairDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.daomysql.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomysql.entity.po.expv2mongo.PcPosPairMongoPo;
import com.hupa.exp.daomysql.enums.SortEnum;
import com.hupa.exp.servermng.entity.pcposition.PcPositionInfo;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageInputDto;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcPositionControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcPositionControllerServiceImpl implements IApiPcPositionControllerService {
    @Autowired
    private IPcPosPairDao iPcPosPairDao;
    @Autowired
    private IExpUserDao iExpUserDao;
    @Override
    public PcPositionPageOutputDto getPcPositionPageData(PcPositionPageInputDto inputDto)throws BizException {
        if(!StringUtils.isEmpty(inputDto.getAccount()))
        {
            ExpUserPo userPo= iExpUserDao.selectUserByAccount(inputDto.getAccount());
            inputDto.setAccountId(userPo.getId());
        }

       MongoPage<PcPosPairMongoPo> pos= iPcPosPairDao.selectPosByParamMng(inputDto.getPair(),inputDto.getPosId(),inputDto.getAccountId(),inputDto.getLiqStatus(),
                inputDto.getCurrentPage(),inputDto.getPageSize(), SortEnum.desc);
        List<PcPositionInfo> rows=new ArrayList<>();
       for(PcPosPairMongoPo po:pos.getRows())
       {
           PcPositionInfo row= ConventObjectUtil.conventObject(po,PcPositionInfo.class);
           rows.add(row);
       }

       PcPositionPageOutputDto outputDto=new PcPositionPageOutputDto();
       //所有值带上双引号""
       outputDto.setRows(rows);
       outputDto.setTotal(pos.getTotalCount());
       outputDto.setSizePerPage(inputDto.getPageSize());
       outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        return outputDto;
    }
}
