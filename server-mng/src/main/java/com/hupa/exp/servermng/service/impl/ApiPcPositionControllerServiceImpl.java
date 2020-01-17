package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.pc.extension.api.PcPositionExtendApi;
import com.hp.sh.expv3.pc.extension.vo.CurrentPositionVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.pcposition.PcPositionInfo;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageInputDto;
import com.hupa.exp.servermng.entity.pcposition.PcPositionPageOutputDto;
import com.hupa.exp.servermng.service.def.IApiPcPositionControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiPcPositionControllerServiceImpl implements IApiPcPositionControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiPcPositionControllerServiceImpl.class);

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private PcPositionExtendApi pcPositionExtendApi;

    @Override
    public PcPositionPageOutputDto getPcPositionPageData(PcPositionPageInputDto inputDto)throws BizException {
        PcPositionPageOutputDto outputDto=new PcPositionPageOutputDto();
        try {
            if (!StringUtils.isEmpty(inputDto.getAccount())) {
                ExpUserPo userPo = iExpUserDao.selectUserByAccount(inputDto.getAccount());
                if (userPo != null)
                    inputDto.setAccountId(userPo.getId());
            }

            PageResult<CurrentPositionVo> pageResult = pcPositionExtendApi.findPositionList(inputDto.getAccountId(), inputDto.getAsset(), inputDto.getPosId(), inputDto.getLiqStatus(), inputDto.getSymbol(),
                    inputDto.getCurrentPage() != 0 ? (int) inputDto.getCurrentPage() : 1, inputDto.getPageSize());
            if(pageResult!=null){
                //遍历赋值
                List<PcPositionInfo> list = new ArrayList();
                if(CollectionUtils.isNotEmpty(pageResult.getList())){
                    for (CurrentPositionVo currentPositionVo : pageResult.getList()) {
                        PcPositionInfo pcPositionInfo = new PcPositionInfo();;
                        pcPositionInfo.setId(currentPositionVo.getId());
                        pcPositionInfo.setUserId(currentPositionVo.getUserId());
                        pcPositionInfo.setAsset(currentPositionVo.getAsset());
                        pcPositionInfo.setSymbol(currentPositionVo.getSymbol());
                        pcPositionInfo.setMarginMode(currentPositionVo.getMarginMode());
                        pcPositionInfo.setAvailQty(currentPositionVo.getAvailQty());
                        pcPositionInfo.setEntryPrice(currentPositionVo.getEntryPrice());
                        pcPositionInfo.setLeverage(currentPositionVo.getLeverage());
                        pcPositionInfo.setLiquidationPrice(currentPositionVo.getLiquidationPrice());
                        pcPositionInfo.setPosMargin(currentPositionVo.getPosMargin());
                        pcPositionInfo.setPosMarginRatio(currentPositionVo.getPosMarginRatio());
                        pcPositionInfo.setMaintMarginRatio(currentPositionVo.getMaintMarginRatio());
                        pcPositionInfo.setQty(currentPositionVo.getQty());
                        pcPositionInfo.setPosPnlRatio(currentPositionVo.getPosPnlRatio());
                        pcPositionInfo.setRealisedPnl(currentPositionVo.getRealisedPnl());
                        pcPositionInfo.setPnl(currentPositionVo.getPnl());
                        pcPositionInfo.setBidFlag(currentPositionVo.getBidFlag());
                        pcPositionInfo.setAutoIncreaseFlag(currentPositionVo.getAutoIncreaseFlag());
                        pcPositionInfo.setCtime(currentPositionVo.getCtime());
                        list.add(pcPositionInfo);
                    }
                }
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
                outputDto.setSizePerPage(inputDto.getPageSize());
            }
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
            outputDto.setPageData(JSON.toJSONString(outputDto, SerializerFeature.WriteNonStringValueAsString));
        }catch(Exception e){
            logger.info("ApiPcPositionControllerServiceImpl getPcPositionPageData exception: " + e.getMessage());
        }
        return outputDto;
    }
}
