package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.pc.extension.api.PcOrderExtendApi;
import com.hp.sh.expv3.pc.extension.vo.UserOrderVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.pcorder.PcOrderInfo;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageInputDto;
import com.hupa.exp.servermng.entity.pcorder.PcOrderPageOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcOrderControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcOrderControllerServiceImpl implements IApiPcOrderControllerService  {

    private static Logger logger = LoggerFactory.getLogger(ApiPcOrderControllerServiceImpl.class);

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private PcOrderExtendApi pcOrderExtendApi;


    @Override
    public PcOrderPageOutputDto getPcOrderPageData(PcOrderPageInputDto inputDto) throws BizException{
        PcOrderPageOutputDto outputDto=new PcOrderPageOutputDto();
        try{
            if(!StringUtils.isEmpty(inputDto.getAccount()))
            {
                ExpUserPo userPo= iExpUserDao.selectUserByAccount(inputDto.getAccount());
                inputDto.setAccountId(userPo.getId());
            }
            PageResult<UserOrderVo> pageResult = pcOrderExtendApi.pageQueryOrderList(inputDto.getAccountId(), inputDto.getAsset(), inputDto.getSymbol(),
                    inputDto.getStatus(),inputDto.getCloseFlag(),inputDto.getOrderId(),
                    inputDto.getCurrentPage()!=0?(int)inputDto.getCurrentPage():1, inputDto.getPageSize());
            //遍历赋值
            List<PcOrderInfo> list = new ArrayList();
            if(CollectionUtils.isNotEmpty(pageResult.getList())){
                PcOrderInfo pcOrderInfo = null;
                for (UserOrderVo userOrderVo : pageResult.getList()) {
                    pcOrderInfo = new PcOrderInfo();
                    pcOrderInfo.setId(userOrderVo.getId());
                    pcOrderInfo.setUserId(userOrderVo.getUserId());
                    pcOrderInfo.setStatus(userOrderVo.getStatus());
                    pcOrderInfo.setFee(userOrderVo.getFee());
                    pcOrderInfo.setPrice(userOrderVo.getPrice());
                    pcOrderInfo.setQty(userOrderVo.getQty());
                    pcOrderInfo.setLongFlag(userOrderVo.getLongFlag());
                    pcOrderInfo.setLeverage(userOrderVo.getLeverage());
                    pcOrderInfo.setAsset(userOrderVo.getAsset());
                    pcOrderInfo.setSymol(userOrderVo.getSymol());
                    pcOrderInfo.setCtime(userOrderVo.getCtime());
                    pcOrderInfo.setAvgPrice(userOrderVo.getAvgPrice());
                    pcOrderInfo.setFilledQty(userOrderVo.getFilledQty());
                    pcOrderInfo.setCloseFlag(userOrderVo.getCloseFlag());
                    pcOrderInfo.setTradeRatio(userOrderVo.getTradeRatio());
                    pcOrderInfo.setOrderMargin(userOrderVo.getOrderMargin());
                    pcOrderInfo.setOrderType(userOrderVo.getOrderType());
                    pcOrderInfo.setRealisedPnl(userOrderVo.getRealisedPnl());
                    pcOrderInfo.setClientOid(userOrderVo.getClientOid());
                    list.add(pcOrderInfo);
                }
            }
            outputDto.setTotal(pageResult.getRowTotal());
            outputDto.setTotalCount(pageResult.getRowTotal());
            outputDto.setRows(list);
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
             outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        }catch(Exception e){
            logger.info("ApiPcOrderControllerServiceImpl getPcOrderPageData exception: " + e.getMessage());
        }
        return outputDto;
    }
}
