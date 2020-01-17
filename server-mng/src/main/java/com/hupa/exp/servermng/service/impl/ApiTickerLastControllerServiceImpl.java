package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IThirdTickerLastDataDao;
import com.hupa.exp.daomysql.entity.po.expv2.ThirdTickerLastDataPo;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataInputDto;
import com.hupa.exp.servermng.entity.tickerlast.PcTickerLastPageDataOutputDto;
import com.hupa.exp.servermng.service.def.IApiTickerLastControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiTickerLastControllerServiceImpl implements IApiTickerLastControllerService{

    @Autowired
    private IThirdTickerLastDataDao iThirdTickerLastDataDao;

    /**
     * 查询行情列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public PcTickerLastPageDataOutputDto getTickerLastPageData(PcTickerLastPageDataInputDto inputDto) throws BizException {
       IPage<ThirdTickerLastDataPo> pos= iThirdTickerLastDataDao.selectPageData(inputDto.getPair(),
               inputDto.getCurrentPage(),inputDto.getPageSize());
        PcTickerLastPageDataOutputDto outputDto=new PcTickerLastPageDataOutputDto();
        outputDto.setRows(pos.getRecords());
        outputDto.setTotal(pos.getTotal());
        outputDto.setPageData(JSON.toJSONString(outputDto,SerializerFeature.WriteNonStringValueAsString));
        return outputDto;
    }

}
