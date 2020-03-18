package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.bb.extension.api.BbOrderExtApi;
import com.hp.sh.expv3.bb.extension.vo.BbOrderVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.bborder.BbOrderInfo;
import com.hupa.exp.servermng.entity.bborder.BbOrderPageInputDto;
import com.hupa.exp.servermng.entity.bborder.BbOrderPageOutputDto;
import com.hupa.exp.servermng.service.def.IApiBbOrderControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/15.
 */
@Service
public class ApiBbOrderControllerServiceImpl  implements IApiBbOrderControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiBbOrderControllerServiceImpl.class);

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private BbOrderExtApi bbOrderExtApi;


    @Override
    public BbOrderPageOutputDto getBbOrderPageData(BbOrderPageInputDto inputDto) throws BizException{
        BbOrderPageOutputDto outputDto=new BbOrderPageOutputDto();
        try{
            if(!StringUtils.isEmpty(inputDto.getAccount())) {
                ExpUserPo userPo= iExpUserDao.selectUserByAccount(inputDto.getAccount());
                inputDto.setAccountId(userPo.getId());
            }
            // inputDto.getSymbol()
            PageResult<BbOrderVo> pageResult = bbOrderExtApi.queryAllBbOrederHistory(inputDto.getAccountId(), inputDto.getAsset(), inputDto.getPageSize(),
                    inputDto.getCurrentPage()!=0?(int)inputDto.getCurrentPage():1);

            //遍历赋值
            if(pageResult!=null) {
                List<BbOrderInfo> list = new ArrayList();
                if (CollectionUtils.isNotEmpty(pageResult.getList())) {
                    for (BbOrderVo bbOrderVo : pageResult.getList()) {
                        BbOrderInfo bbOrderInfo = new BbOrderInfo();
                        bbOrderInfo.setId(bbOrderVo.getId());
                        bbOrderInfo.setUserId(bbOrderVo.getUserId());
                        bbOrderInfo.setAsset(bbOrderVo.getAsset());
                        bbOrderInfo.setSymbol(bbOrderVo.getSymbol());
                        bbOrderInfo.setBidFlag(bbOrderVo.getBidFlag());
                        bbOrderInfo.setPrice(bbOrderVo.getPrice());
                        bbOrderInfo.setOrderType(bbOrderVo.getOrderType());
                        bbOrderInfo.setVolume(bbOrderVo.getVolume());
                        bbOrderInfo.setStatus(bbOrderVo.getStatus());
                        bbOrderInfo.setFeeRatio(bbOrderVo.getFeeRatio());
                        bbOrderInfo.setFeeCost(bbOrderVo.getFeeCost());
                        bbOrderInfo.setOrderMargin(bbOrderVo.getOrderMargin());
                        bbOrderInfo.setFee(bbOrderVo.getFee());
                        bbOrderInfo.setFilledVolume(bbOrderVo.getFilledVolume());
                        bbOrderInfo.setCancelVolume(bbOrderVo.getCancelVolume());
                        bbOrderInfo.setTimeInForce(bbOrderVo.getTimeInForce());
                        bbOrderInfo.setCancelTime(bbOrderVo.getCancelTime());
                        bbOrderInfo.setActiveFlag(bbOrderVo.getActiveFlag());
                        bbOrderInfo.setCreateOperator(bbOrderVo.getCreateOperator());
                        bbOrderInfo.setCancelOperator(bbOrderVo.getCancelOperator());
                        bbOrderInfo.setRemark(bbOrderVo.getRemark());
                        bbOrderInfo.setClientOrderId(bbOrderVo.getClientOrderId());
                        bbOrderInfo.setVersion(bbOrderVo.getVersion());
                        bbOrderInfo.setLeverage(bbOrderVo.getLeverage());
                        bbOrderInfo.setOrderMarginCurrency(bbOrderVo.getOrderMarginCurrency());
                        bbOrderInfo.setModified(bbOrderVo.getModified());
                        bbOrderInfo.setCreated(bbOrderVo.getCreated());
                        list.add(bbOrderInfo);
                    }
                }
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
            }
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
            outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        }catch(Exception e){
            logger.info("ApiBbOrderControllerServiceImpl getBbOrderPageData exception: " + e.getMessage());
        }
        return outputDto;
    }

}
