package com.hupa.exp.servermng.service.impl;

import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.fund.extension.api.FundTransferExtApi;
import com.hp.sh.expv3.fund.extension.vo.FundTransferExtVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.service.def.IApiAccountTransferControllerService;
import com.hupa.exp.servermng.entity.transfer.*;
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
public class ApiAccountTransferControllerServiceImpl implements IApiAccountTransferControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiAccountTransferControllerServiceImpl.class);

    @Autowired
    private FundTransferExtApi fundTransferExtApi;

    /**
     * 查询用户划转历史记录
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public TransferListOutputDto getAllAccountTransferByAccount(TransferListInputDto inputDto) throws BizException {
        //返回对象
        TransferListOutputDto outputDto = new TransferListOutputDto();
        try{
            // 调用第三方接口：查询划转历史记录
            PageResult<FundTransferExtVo> pageResult = fundTransferExtApi.queryAllUserHistory(
                    inputDto.getAccountId()==null || inputDto.getAccountId()==0?null:inputDto.getAccountId(),
                    StringUtils.isNotBlank(inputDto.getAsset())?inputDto.getAsset():null,
                    inputDto.getCurrentPage()!=0?(int)inputDto.getCurrentPage():1,
                    inputDto.getPageSize());

            if(pageResult!=null){
                //遍历赋值
                List<TransferInfoOutputDto> list = new ArrayList<TransferInfoOutputDto>();
                if(CollectionUtils.isNotEmpty(pageResult.getList())){
                    for (FundTransferExtVo fundTransferExtVo : pageResult.getList()) {
                        TransferInfoOutputDto transferInfoOutputDto = new TransferInfoOutputDto();
                        transferInfoOutputDto.setId(String.valueOf(fundTransferExtVo.getTransferId()));
                        transferInfoOutputDto.setAsset(transferInfoOutputDto.getAsset());
                        transferInfoOutputDto.setAmount(String.valueOf(fundTransferExtVo.getQty()));
                        transferInfoOutputDto.setFromAccount(fundTransferExtVo.getFromAccount());
                        transferInfoOutputDto.setToAccount(fundTransferExtVo.getToAccount());
                        transferInfoOutputDto.setStatus(String.valueOf(fundTransferExtVo.getStatus()));
                        transferInfoOutputDto.setCtime(fundTransferExtVo.getCtime()==null ? null : String.valueOf(fundTransferExtVo.getCtime()));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        transferInfoOutputDto.setCreated(fundTransferExtVo.getCreated()==null ? null : sdf.format(fundTransferExtVo.getCreated()));
                        list.add(transferInfoOutputDto);
                    }
                }
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
            }
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        }catch(Exception e){
            logger.info("ApiAccountTransferControllerServiceImpl getAllAccountTransferByAccount exception: " + e.getMessage());
        }
        return outputDto;
    }
}
