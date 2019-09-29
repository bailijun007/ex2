package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.LastPriceRedisConfig;
import com.hupa.exp.base.config.redis.TickerRedisConfig;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizpccontract.entity.PcContractBizBo;
import com.hupa.exp.bizpccontract.entity.PcContractListBizBo;
import com.hupa.exp.bizpccontract.service.def.IPcContractBiz;
import com.hupa.exp.bizticker.config.BizTickerSettingConfig;
import com.hupa.exp.bizticker.entity.PcTickerBizBo;
import com.hupa.exp.bizticker.service.def.ITickerBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiContractControllerService;
import com.hupa.exp.servermng.validate.ContractValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiContractControllerServiceImpl implements IApiContractControllerService {
    @Autowired
    private IPcContractBiz iPcContractBiz;
    @Autowired
    private ContractValidateImpl contractValidate;
    @Autowired
    private IExpOperationLogService logService;
    @Autowired
    private SessionHelper sessionHelper;
    @Autowired
    private ITickerBiz<PcTickerBizBo> iTickerBiz;
    @Autowired
    private LastPriceRedisConfig redisConfig;
    @Autowired
    private BizTickerSettingConfig bizTickerSettingConfig;

    @Override
    public ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException {
        contractValidate.validate(inputDto);
        PcContractBizBo bo= ConventObjectUtil.conventObject(inputDto,PcContractBizBo.class);
        long id=0;
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();

        if(bo.getId()>0)
        {
            PcContractBizBo beforeBo=iPcContractBiz.getContractById(bo.getId());
            id=bo.getId();
            bo.setMtime(System.currentTimeMillis());
            iPcContractBiz.editContract(bo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.Coin.toString(),
                    OperationType.Update.toString(),
                    JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(bo));
        }
        else
        {
            bo.setCtime(System.currentTimeMillis());
            bo.setMtime(System.currentTimeMillis());
            id= iPcContractBiz.createPcContract(bo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.Coin.toString(),
                    OperationType.Update.toString(),
                    JsonUtil.toJsonString(bo),"");
        }
        //查一下有没有最新成交价
        if(iTickerBiz.getLastPrice(inputDto.getPair())==null)
        {
            String redisKey= MessageFormat.format(bizTickerSettingConfig.getPcLastPriceRedisKey(),bo.getPair());
            RedisUtil.redisClientFactory(redisConfig).set(redisKey,DecimalUtil.toTrimLiteral(bo.getLastPrice()));
        }
        ContractOutputDto outputDto=new ContractOutputDto();
        outputDto.setId(id);
        return outputDto;
    }

    @Override
    public GetContractOutputDto getContract(GetContractInputDto inputDto) throws ContractException {

        PcContractBizBo bo=iPcContractBiz.getContractById(inputDto.getId());
        GetContractOutputDto outputDto=new GetContractOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setPair(bo.getPair());
        outputDto.setPairType(String.valueOf(bo.getPairType()));
        outputDto.setCurrency(bo.getCurrency());
        outputDto.setPrecision(String.valueOf(bo.getPrecision()));
        outputDto.setContractName(bo.getContractName());
        outputDto.setDisplayName(bo.getDisplayName());
        outputDto.setDisplayNameSplit(bo.getDisplayNameSplit());
        outputDto.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
        outputDto.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
        outputDto.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
        outputDto.setFaceValue(String.valueOf(bo.getFaceValue()));
        outputDto.setSort(String.valueOf(bo.getSort()));
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));
        outputDto.setCtime(String.valueOf(bo.getCtime()));
        outputDto.setMtime(String.valueOf(bo.getMtime()));
        return outputDto;
    }

    @Override
    public ContractListOutputDto selectPosPageByParam(ContractListInputDto inputDto) throws ContractException  {
        PcContractListBizBo listBizBo= iPcContractBiz.selectPosPageByParam(inputDto.getPair(),inputDto.getCurrentPage(),inputDto.getPageSize());
        ContractListOutputDto outputDto=new ContractListOutputDto();
        List<ContractListOutputPage> pageList=new ArrayList<>();
        for(PcContractBizBo bo:listBizBo.getRows())
        {
            ContractListOutputPage po=new ContractListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setPair(bo.getPair());
            po.setPairType(String.valueOf(bo.getPairType()));
            po.setCurrency(bo.getCurrency());
            po.setPrecision(String.valueOf(bo.getPrecision()));
            po.setContractName(bo.getContractName());
            po.setDisplayName(bo.getDisplayName());
            po.setDisplayNameSplit(bo.getDisplayNameSplit());
            po.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            po.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            po.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            po.setFaceValue(String.valueOf(bo.getFaceValue()));
            po.setSort(String.valueOf(bo.getSort()));
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setPrivilege(String.valueOf(bo.getPrivilege()));
            po.setCtime(String.valueOf(bo.getCtime()));
            po.setMtime(String.valueOf(bo.getMtime()));
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasContractOutputDto checkHasContract(CheckHasContractInputDto inputDto) throws ContractException {
        boolean hasContract = iPcContractBiz.checkHasContract(inputDto.getPair());
        CheckHasContractOutputDto outputDto=new CheckHasContractOutputDto();
        outputDto.setHasContract(hasContract);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto) throws ContractException {
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
        if(iTickerBiz.getLastPrice(inputDto.getPair())!=null)
            outputDto.setHasLastPrice(true);
        else
            outputDto.setHasLastPrice(false);
        return outputDto;
    }

}