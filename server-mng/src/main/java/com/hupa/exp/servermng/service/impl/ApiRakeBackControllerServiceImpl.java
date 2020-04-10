package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcRakeBackDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcRakeBackPo;
import com.hupa.exp.servermng.entity.rakeback.RakeBackInputDto;
import com.hupa.exp.servermng.entity.rakeback.RakeBackListOutputDto;
import com.hupa.exp.servermng.entity.rakeback.RakeBackOutputDto;
import com.hupa.exp.servermng.service.def.IApiRakeBackControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class ApiRakeBackControllerServiceImpl implements IApiRakeBackControllerService {


    private static Logger logger = LoggerFactory.getLogger(ApiRakeBackControllerServiceImpl.class);

    @Autowired
    private IPcRakeBackDao iPcRakeBackDao;

    @Override
    public RakeBackListOutputDto getPosPageByParam(RakeBackInputDto inputDto) throws BizException {
        RakeBackListOutputDto outputDto=new RakeBackListOutputDto();
        try{
            IPage<PcRakeBackPo> poList = iPcRakeBackDao.selectPosPageByParam(
                    inputDto.getAccountId(),inputDto.getRakeBackAccountId(), inputDto.getType(), inputDto.getTime(),
                    inputDto.getState(), inputDto.getAsset(),inputDto.getSymbol(),inputDto.getCurrentPage(),inputDto.getPageSize());
            List<RakeBackOutputDto> pageList=new ArrayList<>();
            if(poList!=null && poList.getRecords()!=null && poList.getRecords().size()>0){
                RakeBackOutputDto po = null;
                for(PcRakeBackPo bo: poList.getRecords()) {
                    po=new RakeBackOutputDto();
                    po.setId(String.valueOf(bo.getId()));
                    po.setAccountId(bo.getAccountId());
                    po.setRakeBackAccountId(bo.getRakeBackAccountId());
                    po.setRakeBackLevel(bo.getRakeBackLevel());
                    po.setRakeBackAmt(bo.getRakeBackAmt().setScale(14, RoundingMode.HALF_DOWN));
                    po.setSourceMakerFee(bo.getSourceMakerFee()==null?null:bo.getSourceMakerFee().setScale(14, RoundingMode.HALF_DOWN));
                    po.setSourceTakeFee(bo.getSourceTakeFee()==null?null:bo.getSourceTakeFee().setScale(14, RoundingMode.HALF_DOWN));
                    po.setPrice(bo.getPrice()==null? null: bo.getPrice().setScale(10, RoundingMode.HALF_DOWN));
                    po.setProportion(bo.getProportion().setScale(6, RoundingMode.HALF_DOWN));
                    po.setTime(String.valueOf(bo.getTime()));
                    po.setType(String.valueOf(bo.getType()));
                    po.setSymbol(bo.getSymbol());
                    po.setAsset(bo.getAsset());
                    po.setState(String.valueOf(bo.getState()));
                    po.setCtime(bo.getCtime()==null? null : String.valueOf(bo.getCtime()));
                    po.setMtime(bo.getMtime()==null? null : String.valueOf(bo.getMtime()));
                    pageList.add(po);
                }
            }
            outputDto.setRows(pageList);
            outputDto.setTotal(poList.getTotal());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
            outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        }catch(Exception e){
            logger.info("ApiRakeBackControllerServiceImpl getPosPageByParam exception: " + e.getMessage());
        }
        return outputDto;
    }
}
