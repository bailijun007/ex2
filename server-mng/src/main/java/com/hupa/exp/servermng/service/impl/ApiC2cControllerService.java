package com.hupa.exp.servermng.service.impl;

import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.fund.extension.api.C2cOrderExtApi;
import com.hp.sh.expv3.fund.extension.vo.C2cOrderVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.c2c.*;
import com.hupa.exp.servermng.service.def.IApiC2cControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/1/14.
 */
@Service
public class ApiC2cControllerService implements IApiC2cControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiC2cControllerService.class);

    @Autowired
    private C2cOrderExtApi c2cOrderExtApi;

    @Override
    public C2CListOutputDto findAllC2cList(C2CListInputDto inputDto) throws BizException {
        //返回对象
        C2CListOutputDto outputDto = new C2CListOutputDto();
        try {
            //  查询所有c2c体现订单
            PageResult<C2cOrderVo> pageResult = c2cOrderExtApi.queryAllWithdrawalOrder(inputDto.getUserId() == null || inputDto.getUserId() == 0 ? null : inputDto.getUserId(),
                    inputDto.getApprovalStatus(), inputDto.getCurrentPage() != 0 ? (int) inputDto.getCurrentPage() : 1, inputDto.getPageSize());
            if (pageResult != null) {
                //遍历赋值
                List<C2COutputDto> list = new ArrayList<C2COutputDto>();
                if (CollectionUtils.isNotEmpty(pageResult.getList())) {
                    for (C2cOrderVo c2cOrderVo : pageResult.getList()) {
                        C2COutputDto c2COutputDto = new C2COutputDto();
                        c2COutputDto.setId(String.valueOf(c2cOrderVo.getId()));
                        c2COutputDto.setUserId(c2cOrderVo.getUserId());
                        c2COutputDto.setCreated(c2cOrderVo.getCreated());
                        c2COutputDto.setModified(c2cOrderVo.getModified());
                        c2COutputDto.setSn(c2cOrderVo.getSn());
                        c2COutputDto.setPayCurrency(c2cOrderVo.getPayCurrency());
                        c2COutputDto.setExchangeCurrency(c2cOrderVo.getExchangeCurrency());
                        c2COutputDto.setType(c2cOrderVo.getType());
                        c2COutputDto.setPrice(c2cOrderVo.getPrice());
                        c2COutputDto.setVolume(c2cOrderVo.getVolume());
                        c2COutputDto.setAmount(c2cOrderVo.getAmount());
                        c2COutputDto.setPayStatus(c2cOrderVo.getPayStatus());
                        c2COutputDto.setPayStatusDesc(c2cOrderVo.getPayStatusDesc());
                        c2COutputDto.setPayTime(c2cOrderVo.getPayTime());
                        c2COutputDto.setPayFinishTime(c2cOrderVo.getPayFinishTime());
                        c2COutputDto.setSynchStatus(c2cOrderVo.getSynchStatus());
                        c2COutputDto.setApprovalStatus(c2cOrderVo.getApprovalStatus());
                        list.add(c2COutputDto);
                    }
                }
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
            }
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            logger.info("ApiC2cControllerService findAllC2cList exception: " + e.getMessage());
        }
        return outputDto;
    }


    /**
     * 审核
     *
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AuditC2COutputDto auditC2c(AuditC2CInputDto inputDto) throws BizException {
        AuditC2COutputDto outputDto = new AuditC2COutputDto();
        Boolean approvalStatus = false;
        try {
            String status = c2cOrderExtApi.approvalC2cOrder(inputDto.getId(), inputDto.getApprovalStatus());
            if (status != null && "success".equals(status)) {
                approvalStatus = true;
            }
        } catch (Exception e) {
            logger.info("ApiC2cControllerService auditC2c Exception:"+e.getMessage());
        }
        outputDto.setApprovalStatus(approvalStatus);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


}
