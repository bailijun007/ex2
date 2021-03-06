package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.contract.PcContractBizBo;
import com.hupa.exp.bizother.entity.contract.PcContractListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.contract.def.IPcContractBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcContractDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcContractPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.*;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.ContractException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiContractControllerService;
import com.hupa.exp.servermng.validate.ContractValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private IPcContractDao iPcContractDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private com.hupa.exp.common.component.redis.RedisUtil redisUtilDb0;

    @Autowired
    private IExpDicDao iExpDicDao;

    /**
     * 新增或修改交易对
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public ContractOutputDto createOrEditContract(ContractInputDto inputDto) throws BizException {
        contractValidate.validate(inputDto);
        PcContractBizBo bo= ConventObjectUtil.conventObject(inputDto,PcContractBizBo.class);
        long id=0;
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        if(bo.getId()>0)
        {
            PcContractBizBo beforeBo=iPcContractBiz.getContractById(bo.getId());
            String beforeStr=JsonUtil.toJsonString(beforeBo);
            PcContractBizBo input= iPcContractBiz.checkHasContract(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName());
            //修改 传进来的参数能查询到数据 且查出来的数据不相等
            if(input!=null&&beforeBo.getId()!=input.getId())
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
            id=bo.getId();
            //不让修改币种和交易对
            beforeBo.setAsset(bo.getAsset());
            beforeBo.setSymbol(bo.getSymbol());

            beforeBo.setSymbolType(bo.getSymbolType());
            //outputDto.setCurrency(bo.getCurrency());
            beforeBo.setPrecision(bo.getPrecision());
            beforeBo.setContractName(bo.getContractName());
            beforeBo.setContractNameSplit(bo.getContractNameSplit());
            beforeBo.setContractGroup(bo.getContractGroup());
            beforeBo.setContractType(bo.getContractType());
            beforeBo.setSettlePrice(bo.getSettlePrice());
            beforeBo.setDisplayName(bo.getDisplayName());
            beforeBo.setDisplayNameSplit(bo.getDisplayNameSplit());
            beforeBo.setDefaultPrice(bo.getDefaultPrice());
            beforeBo.setLastPrice(bo.getLastPrice());
            beforeBo.setStep(bo.getStep());
            beforeBo.setFaceValue(bo.getFaceValue());
            beforeBo.setSort(bo.getSort());
            beforeBo.setStatus(bo.getStatus());
            beforeBo.setEnableCreate(bo.getEnableCreate());
            beforeBo.setEnableCancel(bo.getEnableCancel());
            beforeBo.setPrivilege(bo.getPrivilege());
            beforeBo.setQuoteCurrency(bo.getQuoteCurrency());
            beforeBo.setMtime(System.currentTimeMillis());
            beforeBo.setBaseCurrency(bo.getBaseCurrency());
            beforeBo.setSettleCurrency(bo.getSettleCurrency());
            beforeBo.setFaceCurrency(bo.getFaceCurrency());
            beforeBo.setContractChineseName(bo.getContractChineseName());
            iPcContractBiz.editContract(beforeBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.Contract.toString(),
                    OperationType.Update.toString(),
                    beforeStr,JsonUtil.toJsonString(beforeBo));
        }
        else
        {
            //true已存在  报错
            if(iPcContractBiz.checkHasContract(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName())!=null)
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
            bo.setCtime(System.currentTimeMillis());
            bo.setMtime(System.currentTimeMillis());
            id= iPcContractBiz.createPcContract(bo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                    OperationModule.Contract.toString(),
                    OperationType.Update.toString(),
                    JsonUtil.toJsonString(bo),"");
        }
        ContractOutputDto outputDto=new ContractOutputDto();
        outputDto.setId(id);
        return outputDto;
    }

    @Override
    public GetContractOutputDto getContract(ContractInputDto inputDto) throws ContractException {
        PcContractBizBo bo=iPcContractBiz.getContractById(inputDto.getId());
        GetContractOutputDto outputDto=new GetContractOutputDto();
        if(bo!=null){
            outputDto.setId(String.valueOf(bo.getId()));
            outputDto.setSymbol(bo.getSymbol());
            outputDto.setSymbolType(String.valueOf(bo.getSymbolType()));
            outputDto.setAsset(bo.getAsset());
            //outputDto.setCurrency(bo.getCurrency());
            outputDto.setPrecision(String.valueOf(bo.getPrecision()));
            outputDto.setContractName(bo.getContractName());
            outputDto.setContractNameSplit(bo.getContractNameSplit());
            outputDto.setContractGroup(bo.getContractGroup());
            outputDto.setContractType(bo.getContractType());
            outputDto.setSettlePrice(bo.getSettlePrice());
            outputDto.setDisplayName(bo.getDisplayName());
            outputDto.setDisplayNameSplit(bo.getDisplayNameSplit());
            outputDto.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            outputDto.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            outputDto.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            outputDto.setFaceValue(String.valueOf(bo.getFaceValue()));
            outputDto.setSort(String.valueOf(bo.getSort()));
            outputDto.setStatus(String.valueOf(bo.getStatus()));
            outputDto.setEnableCreate(String.valueOf(bo.getEnableCreate()));
            outputDto.setEnableCancel(String.valueOf(bo.getEnableCancel()));
            outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));
            outputDto.setCtime(String.valueOf(bo.getCtime()));
            outputDto.setMtime(String.valueOf(bo.getMtime()));
            outputDto.setBaseCurrency(bo.getBaseCurrency());
            outputDto.setSettleCurrency(bo.getSettleCurrency());
            outputDto.setFaceCurrency(bo.getFaceCurrency());
            outputDto.setQuoteCurrency(bo.getQuoteCurrency());
            outputDto.setContractChineseName(bo.getContractChineseName());
        }
        return outputDto;
    }

    @Override
    public ContractListOutputDto getPosPageByParam(ContractListInputDto inputDto) throws ContractException  {
        PcContractListBizBo listBizBo= iPcContractBiz.selectPosPageByParam(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getCurrentPage(),inputDto.getPageSize());
        ContractListOutputDto outputDto=new ContractListOutputDto();
        List<ContractListOutputPage> pageList=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(listBizBo.getRows())){
            ContractListOutputPage po = null;
            for(PcContractBizBo bo:listBizBo.getRows()) {
                po = new ContractListOutputPage();
                po.setId(String.valueOf(bo.getId()));
                po.setSymbol(bo.getSymbol());
                po.setSymbolType(String.valueOf(bo.getSymbolType()));
                po.setAsset(bo.getAsset());
                //po.setCurrency(bo.getCurrency());
                po.setPrecision(String.valueOf(bo.getPrecision()));
                po.setContractName(bo.getContractName());
                po.setContractNameSplit(bo.getContractNameSplit());
                po.setContractGroup(bo.getContractGroup());
                po.setContractType(bo.getContractType());
                po.setSettlePrice(bo.getSettlePrice());
                po.setDisplayName(bo.getDisplayName());
                po.setDisplayNameSplit(bo.getDisplayNameSplit());
                po.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
                po.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
                po.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
                po.setFaceValue(String.valueOf(bo.getFaceValue()));
                po.setSort(String.valueOf(bo.getSort()));
                po.setStatus(String.valueOf(bo.getStatus()));
                po.setEnableCreate(String.valueOf(bo.getEnableCreate()));
                po.setEnableCancel(String.valueOf(bo.getEnableCancel()));
                po.setPrivilege(String.valueOf(bo.getPrivilege()));
                po.setCtime(String.valueOf(bo.getCtime()));
                po.setMtime(String.valueOf(bo.getMtime()));
                po.setQuoteCurrency(bo.getQuoteCurrency());
                po.setBaseCurrency(bo.getBaseCurrency());
                po.setSettleCurrency(bo.getSettleCurrency());
                po.setFaceCurrency(bo.getFaceCurrency());
                po.setContractChineseName(bo.getContractChineseName());
                pageList.add(po);
            }
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ContractOutputDto getAllActiveContract(ContractInputDto inputDto) throws BizException {
        ContractOutputDto outputDto =new ContractOutputDto();
        List<PcContractPo> pcContractPos= iPcContractDao.selectPosByStatus(1);
        List<GetContractOutputDto> activeContract = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(pcContractPos)){
            pcContractPos.forEach(bo->{
                GetContractOutputDto info=new GetContractOutputDto();
                info.setId(String.valueOf(bo.getId()));
                info.setSymbol(bo.getSymbol());
                info.setSymbolType(String.valueOf(bo.getSymbolType()));
                info.setAsset(bo.getAsset());
                //info.setCurrency(bo.getCurrency());
                info.setPrecision(String.valueOf(bo.getPrecision()));
                info.setContractName(bo.getContractName());
                info.setContractNameSplit(bo.getContractNameSplit());
                info.setContractGroup(bo.getContractGroup());
                info.setContractType(bo.getContractType());
                info.setSettlePrice(bo.getSettlePrice());
                info.setDisplayName(bo.getDisplayName());
                info.setDisplayNameSplit(bo.getDisplayNameSplit());
                info.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
                info.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
                info.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
                info.setFaceValue(String.valueOf(bo.getFaceValue()));
                info.setSort(String.valueOf(bo.getSort()));
                info.setStatus(String.valueOf(bo.getStatus()));
                info.setEnableCreate(String.valueOf(bo.getEnableCreate()));
                info.setEnableCancel(String.valueOf(bo.getEnableCancel()));
                info.setPrivilege(String.valueOf(bo.getPrivilege()));
                info.setCtime(String.valueOf(bo.getCtime()));
                info.setMtime(String.valueOf(bo.getMtime()));
                info.setQuoteCurrency(bo.getQuoteCurrency());
                info.setBaseCurrency(bo.getBaseCurrency());
                info.setSettleCurrency(bo.getSettleCurrency());
                info.setFaceCurrency(bo.getFaceCurrency());
                info.setContractChineseName(bo.getContractChineseName());
                activeContract.add(info);
            });
        }
        outputDto.setActiveContract(activeContract);
        return outputDto;
    }

    @Override
    public ContractOutputDto getAllSymbolList(ContractInputDto inputDto) throws ContractException {
        List<String> symbolList= iPcContractBiz.selectAllSymbol();
        ContractOutputDto outputDto=new ContractOutputDto();
        Set<String> set = new HashSet<>(symbolList);
        List<String> list= new ArrayList<>(set);
        outputDto.setSymbolList(list);
        return outputDto;
    }

    @Override
    public ContractOutputDto checkHasContract(ContractInputDto inputDto) throws  MngException {

        if(inputDto.getId()>0)
        {
            PcContractBizBo beforeBo=iPcContractBiz.getContractById(inputDto.getId());
            PcContractBizBo input= iPcContractBiz.checkHasContract(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName());
            //修改 传进来的参数能查询到数据 且查出来的数据不相等
            if(input!=null&&beforeBo.getId()!=input.getId())
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
        }
        else
        {
            //true已存在  报错
            if(iPcContractBiz.checkHasContract(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName())!=null)
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);

        }
        ContractOutputDto outputDto = new ContractOutputDto();
        //outputDto.setHasContract(hasContract);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto) throws ContractException {
        CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
//        if(iLastPriceBiz.get(inputDto.getSymbol())!=null)
//            outputDto.setHasLastPrice(true);
//        else
            outputDto.setHasLastPrice(false);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteContract(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            PcContractPo po= iPcContractDao.selectPoById(Long.parseLong(id));
            if(po!=null)
            {
                if(iPcContractDao.deleteById(po.getId())>0)
                {
                    //删除交易对的时候顺便删除redis中的值
                    ExpDicPo dicPo= iExpDicDao.selectDicByKey("ContractRedisKey");
                    if(dicPo!=null)
                    {
                        redisUtilDb0.hdel(dicPo.getValue(),po.getSymbol());
                    }
                    ExpDicPo dicPoDisplay= iExpDicDao.selectDicByKey("DisplayNameRedisKey");
                    if(dicPoDisplay!=null)
                    {
                        redisUtilDb0.hdel(dicPoDisplay.getValue(),po.getDisplayName());
                    }
                }
            }
            iPcContractDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Contract.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ContractOutputDto GetContractListByAsset(ContractInputDto inputDto) throws BizException {
        List<PcContractPo> poList=iPcContractDao.selectPosByAsset(inputDto.getAsset());
        List<GetContractOutputDto> assetSymbolList=new ArrayList<>();
        poList.forEach(bo->{
            GetContractOutputDto info=new GetContractOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setSymbol(bo.getSymbol());
            info.setSymbolType(String.valueOf(bo.getSymbolType()));
            info.setAsset(bo.getAsset());
            //info.setCurrency(bo.getCurrency());
            info.setPrecision(String.valueOf(bo.getPrecision()));
            info.setContractName(bo.getContractName());
            info.setContractNameSplit(bo.getContractNameSplit());
            info.setContractGroup(bo.getContractGroup());
            info.setContractType(bo.getContractType());
            info.setSettlePrice(bo.getSettlePrice());
            info.setDisplayName(bo.getDisplayName());
            info.setDisplayNameSplit(bo.getDisplayNameSplit());
            info.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            info.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            info.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            info.setFaceValue(String.valueOf(bo.getFaceValue()));
            info.setSort(String.valueOf(bo.getSort()));
            info.setStatus(String.valueOf(bo.getStatus()));
            info.setEnableCreate(String.valueOf(bo.getEnableCreate()));
            info.setEnableCancel(String.valueOf(bo.getEnableCancel()));
            info.setPrivilege(String.valueOf(bo.getPrivilege()));
            info.setCtime(String.valueOf(bo.getCtime()));
            info.setMtime(String.valueOf(bo.getMtime()));
            info.setQuoteCurrency(bo.getQuoteCurrency());
            info.setBaseCurrency(bo.getBaseCurrency());
            info.setSettleCurrency(bo.getSettleCurrency());
            info.setFaceCurrency(bo.getFaceCurrency());
            info.setContractChineseName(bo.getContractChineseName());
            assetSymbolList.add(info);
        });
        ContractOutputDto outputDto=new ContractOutputDto();
        outputDto.setAssetSymbolList(assetSymbolList);
        return outputDto;
    }


    /**
     * 根据组，查询每组，有多少合约交易对
     * 合约交易对,每组只能设置3个
     * @param inputDto
     * @return
     * @throws BizException
     */
    public ContractOutputDto getContractGroupNum(ContractInputDto inputDto) throws BizException{
        ContractOutputDto outputDto=new ContractOutputDto();
        List<PcContractPo> lists = iPcContractDao.selectPoGroup(inputDto.getContractGroup());
        if(lists!=null && lists.size()>0){
            outputDto.setNumber(lists.size());
        }
        return outputDto;
    }


    /**
     * 获取所有的货币对
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public ContractOutputDto findContractListByAll(ContractInputDto inputDto) throws BizException {
        List<PcContractPo> poList= iPcContractDao.selectPosByAsset(inputDto.getAsset());
        List<GetContractOutputDto> assetSymbolList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(poList)){
            poList.forEach(bo->{
                GetContractOutputDto info = new GetContractOutputDto();
                info.setId(String.valueOf(bo.getId()));
                info.setSymbol(bo.getSymbol());
                info.setSymbolType(String.valueOf(bo.getSymbolType()));
                info.setAsset(bo.getAsset());
                info.setPrecision(String.valueOf(bo.getPrecision()));
                info.setContractName(bo.getContractName());
                info.setContractNameSplit(bo.getContractNameSplit());
                info.setContractGroup(bo.getContractGroup());
                info.setContractType(bo.getContractType());
                info.setSettlePrice(bo.getSettlePrice());
                info.setDisplayName(bo.getDisplayName());
                info.setDisplayNameSplit(bo.getDisplayNameSplit());
                info.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
                info.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
                info.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
                info.setFaceValue(String.valueOf(bo.getFaceValue()));
                info.setSort(String.valueOf(bo.getSort()));
                info.setStatus(String.valueOf(bo.getStatus()));
                info.setEnableCreate(String.valueOf(bo.getEnableCreate()));
                info.setEnableCancel(String.valueOf(bo.getEnableCancel()));
                info.setPrivilege(String.valueOf(bo.getPrivilege()));
                info.setCtime(String.valueOf(bo.getCtime()));
                info.setMtime(String.valueOf(bo.getMtime()));
                info.setQuoteCurrency(bo.getQuoteCurrency());
                info.setBaseCurrency(bo.getBaseCurrency());
                info.setSettleCurrency(bo.getSettleCurrency());
                info.setFaceCurrency(bo.getFaceCurrency());
                info.setContractChineseName(bo.getContractChineseName());
                assetSymbolList.add(info);
            });
        }
        ContractOutputDto outputDto = new ContractOutputDto();
        outputDto.setAssetSymbolList(assetSymbolList);
        return outputDto;
    }

}
