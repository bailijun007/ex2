package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomongo.dao.expv2.def.IPcOrderAssetSymbolMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcOrderAssetSymbolMongoPo;
import com.hupa.exp.daomongo.enums.MongoSortEnum;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.pcorder.PcOrderInfo;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageInputDto;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcOrderControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcOrderControllerServiceImpl implements IApiPcOrderControllerService  {
    @Autowired
    private IPcOrderAssetSymbolMongoDao iPcOrderAssetSymbolMongoDao;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Override
    public PcOrderPageOutputDto getPcOrderPageData(PcOrderPageInputDto inputDto) throws BizException{

        if(!StringUtils.isEmpty(inputDto.getAccount()))
        {
            ExpUserPo userPo= iExpUserDao.selectUserByAccount(inputDto.getAccount());
            inputDto.setAccountId(userPo.getId());
        }

        MongoPage<PcOrderAssetSymbolMongoPo> pos= iPcOrderAssetSymbolMongoDao.pagePosByParamMng(
                inputDto.getAccountId(),inputDto.getSymbol(),inputDto.getAsset(),
                inputDto.getOrderId(),inputDto.getCloseFlag(),
                inputDto.getStatus(),inputDto.getPrice(),inputDto.getCurrentPage(),
                inputDto.getPageSize(), MongoSortEnum.desc);

        List<PcOrderInfo> rows=new ArrayList<>();
        for(PcOrderAssetSymbolMongoPo po:pos.getRows())
        {
            PcOrderInfo row= ConventObjectUtil.conventObject(po,PcOrderInfo.class);
            rows.add(row);
        }
        PcOrderPageOutputDto outputDto=new PcOrderPageOutputDto();
        outputDto.setRows(rows);
        outputDto.setTotal(pos.getTotalCount());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        return outputDto;
    }
}
