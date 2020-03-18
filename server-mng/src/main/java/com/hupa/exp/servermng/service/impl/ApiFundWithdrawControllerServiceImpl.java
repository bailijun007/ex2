package com.hupa.exp.servermng.service.impl;

import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.fund.cash.api.ChainCasehApi;
import com.hp.sh.expv3.fund.extension.api.WithdrawalRecordExtApi;
import com.hp.sh.expv3.fund.extension.vo.WithdrawalRecordByAdmin;
import com.hupa.exp.bizother.service.account.def.IWithdrawBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.servermng.entity.fundwithdraw.*;
import com.hupa.exp.servermng.service.def.IApiFundWithdrawControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFundWithdrawControllerServiceImpl implements IApiFundWithdrawControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiFundWithdrawControllerServiceImpl.class);
    //@Autowired
    //private IExpOperationLogService logService;
    //@Autowired
    //private SessionHelper sessionHelper;

    @Autowired
    private IWithdrawBiz iWithdrawBiz;

    /**
     * 用户
     */
    @Autowired
    private IExpUserDao iExpUserDao;

    /**
     * 查询提现 历史记录
     */
    @Autowired
    private WithdrawalRecordExtApi withdrawalRecordExtApi;

    /**
     * 提现审核（通过、不通过）
     */
    @Autowired
    private ChainCasehApi chainCasehApi;

    /**
     *  查询用户提币历史记录
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public FundWithdrawAccountListOutputDto getAccountAllFundWith(FundWithdrawAccountListInputDto inputDto) throws BizException {
        FundWithdrawAccountListOutputDto outputDto = new FundWithdrawAccountListOutputDto();
        try{
            // 调用第三方接口：查询提现历史记录
            PageResult<WithdrawalRecordByAdmin> pageResult = withdrawalRecordExtApi.queryHistoryByAdmin(
                    inputDto.getAccountId()==null || inputDto.getAccountId()==0?null:inputDto.getAccountId(),
                    StringUtils.isNotBlank(inputDto.getAsset())?inputDto.getAsset():null,
                    inputDto.getApprovalStatus()==null? null:inputDto.getApprovalStatus(),
                    inputDto.getPayStatus()==null? null:inputDto.getPayStatus(),
                    inputDto.getCurrentPage()!=0? inputDto.getCurrentPage():1,
                    inputDto.getPageSize());

            if(pageResult!=null){
                List<FundWithdrawOutputDto> list =  new ArrayList();
                if(CollectionUtils.isNotEmpty(pageResult.getList())){
                    for (WithdrawalRecordByAdmin withdrawalRecordVo : pageResult.getList()) {
                        FundWithdrawOutputDto fundWithdrawOutputDto = new FundWithdrawOutputDto();
                        fundWithdrawOutputDto.setId(String.valueOf(withdrawalRecordVo.getId()));
                        fundWithdrawOutputDto.setAccountId(String.valueOf(withdrawalRecordVo.getUserId()));
                        fundWithdrawOutputDto.setAsset(String.valueOf(withdrawalRecordVo.getAsset()));
                        fundWithdrawOutputDto.setVolume(String.valueOf(withdrawalRecordVo.getAmount()));
                        fundWithdrawOutputDto.setTargetAddr(withdrawalRecordVo.getTargetAddress());
                        fundWithdrawOutputDto.setWithdrawTime(withdrawalRecordVo.getWithdrawTime()==null? null : String.valueOf(withdrawalRecordVo.getWithdrawTime()));
                        fundWithdrawOutputDto.setTxHash(withdrawalRecordVo.getTxHash()==null ? null : String.valueOf(withdrawalRecordVo.getTxHash()));
                        fundWithdrawOutputDto.setCtime(withdrawalRecordVo.getCtime()==null ? null : String.valueOf(withdrawalRecordVo.getCtime()));
                        fundWithdrawOutputDto.setStatus(String.valueOf(withdrawalRecordVo.getStatus()));
                        fundWithdrawOutputDto.setPayStatus(String.valueOf(withdrawalRecordVo.getPayStatus()));
                        fundWithdrawOutputDto.setPayStatusDesc(withdrawalRecordVo.getPayStatusDesc());
                        list.add(fundWithdrawOutputDto);
                    }
                }
                //返回
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
            }
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        }catch(Exception e){
            logger.info("ApiFundWithdrawControllerServiceImpl getAccountAllFundWith Exception:"+e.getMessage());
        }
        return outputDto;
    }

    /**
     * 有待修改  暂时不用
     * 查询提币审核列表
     * @param inputDto
     * @return
     * @throws BizException
     */
  /*  @Override
    public FundWithdrawListOutputDto getFundWithdrawList(FundWithdrawListInputDto inputDto) throws BizException {
        Long accountId = null;
        if (!StringUtils.isEmpty(inputDto.getAccount())) {
            //根据手机号码或邮箱查询用户
            ExpUserPo userPo = iExpUserDao.selectUserByAccount(inputDto.getAccount());
            if (userPo != null)
                accountId = userPo.getId();
        }
        FundWithdrawMongoPageBizBo pageBizBo = iWithdrawBiz.selectFundWithdrawPageData(accountId, inputDto.getSymbol(), inputDto.getId(), inputDto.getCurrentPage(), inputDto.getPageSize());
        List<FundWithdrawOutputDto> list = new ArrayList<>();
        for (FundWithdrawMongoBizBo bo : pageBizBo.getRows()) {
            FundWithdrawOutputDto info = new FundWithdrawOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setAsset(bo.getAsset());
            info.setAccountId(String.valueOf(bo.getAccountId()));
            info.setTargetAddr(bo.getTargetAddr());
            info.setVolume(DecimalUtil.trimZeroPlainString(bo.getVolume()));
            info.setFee(DecimalUtil.trimZeroPlainString(bo.getFee()));
            info.setWithdrawTime(DecimalUtil.trimZeroPlainString(bo.getWithdrawTime()));
            info.setStatus(String.valueOf(bo.getStatus()));
            info.setCtime(String.valueOf(bo.getCtime()));
            info.setMtime(String.valueOf(bo.getMtime()));
            list.add(info);
        }
        FundWithdrawListOutputDto outputDto = new FundWithdrawListOutputDto();
        outputDto.setSizePerPage(Integer.valueOf(String.valueOf(pageBizBo.getPageSize())));
        outputDto.setTotal(pageBizBo.getTotal());
        outputDto.setRows(list);
        return outputDto;
    }*/

    /**
     * 审核通过
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AuditFundWithdrawOutputDto auditPassFundWithdraw(AuditPassFundWithdrawInputDto inputDto) throws BizException {
        AuditFundWithdrawOutputDto outputDto = new AuditFundWithdrawOutputDto();
        Boolean withdrawStatus =  false;//提现状态
        try{
            //批准提现
            chainCasehApi.approve(inputDto.getAccountId(),inputDto.getWithdrawId());
            withdrawStatus = true;
        }catch(Exception e){
            logger.info("ApiFundWithdrawControllerServiceImpl auditPassFundWithdraw Exception:"+e.getMessage());
        }
      /*
        FundWithdrawMongoBizBo beforeBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        FundWithdrawMongoBizBo afterBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),OperationModule.FundAccount.toString(), OperationType.Audit.toString(), JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(afterBo));
        */
        outputDto.setAuditStatus(withdrawStatus);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 审核不通过
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AuditFundWithdrawOutputDto auditFailFundWithdraw(AuditFailFundWithdrawInputDto inputDto) throws BizException {
        AuditFundWithdrawOutputDto outputDto = new AuditFundWithdrawOutputDto();
        Boolean withdrawStatus =  false;//提现状态
        try{
            //拒绝提现
            chainCasehApi.reject(inputDto.getAccountId(),inputDto.getWithdrawId(),inputDto.getReason().trim());
            withdrawStatus = true;
        }catch(Exception e){
            logger.info("ApiFundWithdrawControllerServiceImpl auditFailFundWithdraw Exception:"+e.getMessage());
        }
        //记录日志
/*        FundWithdrawMongoBizBo beforeBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        FundWithdrawMongoBizBo afterBo = iWithdrawBiz.selectFundWithdrawById(inputDto.getWithdrawId(), inputDto.getSymbol());
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.FundAccount.toString(), OperationType.Audit.toString(), JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(afterBo));*/
        outputDto.setAuditStatus(withdrawStatus);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }



}
