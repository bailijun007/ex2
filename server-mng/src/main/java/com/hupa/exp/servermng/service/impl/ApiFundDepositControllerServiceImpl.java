package com.hupa.exp.servermng.service.impl;

import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.fund.extension.api.DepositRecordExtApi;
import com.hp.sh.expv3.fund.extension.vo.DepositRecordHistoryVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositInfoOutputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListInputDto;
import com.hupa.exp.servermng.entity.funddeposit.FundDepositListOutputDto;
import com.hupa.exp.servermng.service.def.IApiFundDepositControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundDepositControllerServiceImpl implements IApiFundDepositControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiFundDepositControllerServiceImpl.class);

    @Autowired
    private DepositRecordExtApi depositRecordExtApi;

    /**
     * 充值历史记录查询
     * 调用第三方接口
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public FundDepositListOutputDto getAccountAllFundDeposit(FundDepositListInputDto inputDto) throws BizException {
        //返回对象
        FundDepositListOutputDto outputDto = new FundDepositListOutputDto();
        try{
           // 调用第三方接口：查询充值历史记录
           PageResult<DepositRecordHistoryVo> pageResult = depositRecordExtApi.queryAllUserHistory(
                   inputDto.getAccountId()==null || inputDto.getAccountId()==0?null:inputDto.getAccountId(),
                   StringUtils.isNotBlank(inputDto.getAsset())?inputDto.getAsset():null,
                   inputDto.getCurrentPage()!=0?(int)inputDto.getCurrentPage():1,
                   inputDto.getPageSize());
           if(pageResult!=null){
               //遍历赋值
               List<FundDepositInfoOutputDto> list = new ArrayList();
               if(CollectionUtils.isNotEmpty(pageResult.getList())){
                   FundDepositInfoOutputDto fundDepositInfoOutputDto = null;
                   for (DepositRecordHistoryVo depositRecordHistoryVo : pageResult.getList()) {
                       fundDepositInfoOutputDto = new FundDepositInfoOutputDto();
                       fundDepositInfoOutputDto.setId(String.valueOf(depositRecordHistoryVo.getId()));
                       fundDepositInfoOutputDto.setAccountId(String.valueOf(depositRecordHistoryVo.getUserId()));
                       fundDepositInfoOutputDto.setAddress(depositRecordHistoryVo.getAddress());
                       fundDepositInfoOutputDto.setAsset(depositRecordHistoryVo.getAsset());
                       fundDepositInfoOutputDto.setVolume(String.valueOf(depositRecordHistoryVo.getVolume()));
                       fundDepositInfoOutputDto.setStatus(String.valueOf(depositRecordHistoryVo.getStatus()));//状态
                       fundDepositInfoOutputDto.setTxHash(depositRecordHistoryVo.getTxHash());
                       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       fundDepositInfoOutputDto.setCtime(depositRecordHistoryVo.getMtime()==null ? null : String.valueOf(depositRecordHistoryVo.getMtime()));//创建时间
                       fundDepositInfoOutputDto.setDepositTime(depositRecordHistoryVo.getDepositTime()==null?null:String.valueOf(depositRecordHistoryVo.getDepositTime()));//充值时间
                       fundDepositInfoOutputDto.setPayTime(depositRecordHistoryVo.getPayTime()==null? null : sdf.format(depositRecordHistoryVo.getPayTime()));//支付时间
                       fundDepositInfoOutputDto.setMtime(depositRecordHistoryVo.getMtime()==null ? null : String.valueOf(depositRecordHistoryVo.getMtime()));
                       fundDepositInfoOutputDto.setModified(depositRecordHistoryVo.getModified()==null?null:sdf.format(depositRecordHistoryVo.getModified()));
                       list.add(fundDepositInfoOutputDto);
                   }
               }
               outputDto.setTotal(pageResult.getRowTotal());
               outputDto.setTotalCount(pageResult.getRowTotal());
               outputDto.setRows(list);
           }
           outputDto.setSizePerPage(inputDto.getPageSize());
           outputDto.setTime(String.valueOf(System.currentTimeMillis()));
       }catch(Exception e){
           logger.info("ApiFundDepositControllerServiceImpl getAccountAllFundDeposit exception: " + e.getMessage());
       }
       return outputDto;
    }
}

