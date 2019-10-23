package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.LastPriceRedisConfig;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.config.BizPriceSettingConfig;
import com.hupa.exp.bizother.entity.contract.PcContractBizBo;
import com.hupa.exp.bizother.entity.contract.PcContractListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.contract.def.IPcContractBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.price.def.ILastPriceBiz;
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
    private ILastPriceBiz iLastPriceBiz;
    @Autowired
    private LastPriceRedisConfig redisConfig;
    @Autowired
    private BizPriceSettingConfig bizPriceSettingConfig;

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
        if(iLastPriceBiz.get(inputDto.getSymbol())==null)
        {
            String redisKey= MessageFormat.format(bizPriceSettingConfig.getPcLastPriceRedisKey(),bo.getSymbol());
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
        outputDto.setSymbol(bo.getSymbol());
        outputDto.setSymbolType(String.valueOf(bo.getSymbolType()));
        outputDto.setAsset(bo.getAsset());
        //outputDto.setCurrency(bo.getCurrency());
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
        PcContractListBizBo listBizBo= iPcContractBiz.selectPosPageByParam(inputDto.getSymbol(),inputDto.getCurrentPage(),inputDto.getPageSize());
        ContractListOutputDto outputDto=new ContractListOutputDto();
        List<ContractListOutputPage> pageList=new ArrayList<>();
        for(PcContractBizBo bo:listBizBo.getRows())
        {
            ContractListOutputPage po=new ContractListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol());
            po.setSymbolType(String.valueOf(bo.getSymbolType()));
            po.setAsset(bo.getAsset());
            //po.setCurrency(bo.getCurrency());
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
    public GetAllSymbolOutputDto selectAllSymbolList(GetAllSymbolInputDto inputDto) throws ContractException {
        List<String> symbolList= iPcContractBiz.selectAllSymbol();
        GetAllSymbolOutputDto outputDto=new GetAllSymbolOutputDto();
        outputDto.setSymbolList(symbolList);
        return outputDto;
    }

    @Override
    public CheckHasContractOutputDto checkHasContract(CheckHasContractInputDto inputDto) throws ContractException {
        boolean hasContract = iPcContractBiz.checkHasContract(inputDto.getSymbol());
        CheckHasContractOutputDto outputDto=new CheckHasContractOutputDto();
        outputDto.setHasContract(hasContract);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto) throws ContractException {
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
        if(iLastPriceBiz.get(inputDto.getSymbol())!=null)
            outputDto.setHasLastPrice(true);
        else
            outputDto.setHasLastPrice(false);
        return outputDto;
    }

}
