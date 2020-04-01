package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.symbol.BbSymbolBizBo;
import com.hupa.exp.bizother.entity.symbol.BbSymbolListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.symbol.def.IBbSymbolBiz;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IBbSymbolDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.BbSymbolPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.contract.GetAllSymbolInputDto;
import com.hupa.exp.servermng.entity.contract.GetAllSymbolOutputDto;
import com.hupa.exp.servermng.entity.symbol.*;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.BbSymbolException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiBbSymbolControllerService;
import com.hupa.exp.servermng.validate.BbSymbolValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2020/2/9.
 */
@Service
public class ApiBbSymbolControllerServiceImpl implements IApiBbSymbolControllerService {

    @Autowired
    private IBbSymbolBiz iBbSymbolBiz;
    @Autowired
    private BbSymbolValidateImpl bbSymbolValidate;
    @Autowired
    private IExpOperationLogService logService;
    @Autowired
    private SessionHelper sessionHelper;
    @Autowired
    private IBbSymbolDao iBbSymbolDao;
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
    public BbSymbolOutputDto createOrEditBbSymbol(BbSymbolInputDto inputDto) throws BizException {
        bbSymbolValidate.validate(inputDto);
        BbSymbolBizBo bo= ConventObjectUtil.conventObject(inputDto,BbSymbolBizBo.class);
        long id=0;
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();

        if(bo.getId()>0)
        {
            BbSymbolBizBo beforeBo=iBbSymbolBiz.getBbSymbolById(bo.getId());
            String beforeStr= JsonUtil.toJsonString(beforeBo);
            BbSymbolBizBo input= iBbSymbolBiz.checkHasBbSymbol(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName());
            //修改 传进来的参数能查询到数据 且查出来的数据不相等
            if(input!=null&&beforeBo.getId()!=input.getId())
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
            id=bo.getId();
            //不让修改币种和交易对
//            beforeBo.setAsset(bo.getAsset());
//            beforeBo.setSymbol(bo.getSymbol());

            beforeBo.setBbGroupId(bo.getBbGroupId());
            beforeBo.setPrecision(bo.getPrecision());
            beforeBo.setBbSymbolName(bo.getBbSymbolName());
            beforeBo.setBbSymbolNameSplit(bo.getBbSymbolNameSplit());
            beforeBo.setDisplayName(bo.getDisplayName());
            beforeBo.setDisplayNameSplit(bo.getDisplayNameSplit());
            beforeBo.setSymbolChinese(bo.getSymbolChinese());
            beforeBo.setStep(bo.getStep());
            beforeBo.setSort(bo.getSort());
            beforeBo.setStatus(bo.getStatus());
            beforeBo.setEnableCreate(bo.getEnableCreate());
            beforeBo.setEnableCancel(bo.getEnableCancel());
            beforeBo.setNumberPrecision(bo.getNumberPrecision());
            beforeBo.setMtime(System.currentTimeMillis());

   /*         beforeBo.setSymbolType(bo.getSymbolType());
            beforeBo.setBbSymbolType(bo.getBbSymbolType());
            beforeBo.setSettlePrice(bo.getSettlePrice());
            beforeBo.setDefaultPrice(bo.getDefaultPrice());
            beforeBo.setLastPrice(bo.getLastPrice());
            beforeBo.setFaceValue(bo.getFaceValue());
            beforeBo.setPrivilege(bo.getPrivilege());
            beforeBo.setQuoteCurrency(bo.getQuoteCurrency());
            beforeBo.setBaseCurrency(bo.getBaseCurrency());
            beforeBo.setSettleCurrency(bo.getSettleCurrency());
            beforeBo.setFaceCurrency(bo.getFaceCurrency());*/
            iBbSymbolBiz.editBbSymbol(beforeBo);
            logService.createOperationLog(user.getId(),user.getUserName(),
                   "BbSymbol",
                    OperationType.Update.toString(),
                    beforeStr,JsonUtil.toJsonString(beforeBo));
        }
        else
        {
            //true已存在  报错
            if(iBbSymbolBiz.checkHasBbSymbol(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName())!=null)
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
            bo.setCtime(System.currentTimeMillis());
            bo.setMtime(System.currentTimeMillis());
            id= iBbSymbolBiz.createBbSymbol(bo);
           logService.createOperationLog(user.getId(),user.getUserName(),
                   "BbSymbol",
                    OperationType.Insert.toString(),
                    JsonUtil.toJsonString(bo),"");
        }
        BbSymbolOutputDto outputDto=new BbSymbolOutputDto();
        outputDto.setId(id);
        return outputDto;
    }



    @Override
    public GetBbSymbolOutputDto getBbSymbol(BbSymbolInputDto inputDto) throws BbSymbolException {

        BbSymbolBizBo bo=iBbSymbolBiz.getBbSymbolById(inputDto.getId());
        GetBbSymbolOutputDto outputDto=new GetBbSymbolOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setSymbol(bo.getSymbol());
        outputDto.setAsset(bo.getAsset());
        outputDto.setBbGroupId(bo.getBbGroupId());
        outputDto.setPrecision(String.valueOf(bo.getPrecision()));
        outputDto.setBbSymbolName(bo.getBbSymbolName());
        outputDto.setBbSymbolNameSplit(bo.getBbSymbolNameSplit());
        outputDto.setDisplayName(bo.getDisplayName());
        outputDto.setDisplayNameSplit(bo.getDisplayNameSplit());
        outputDto.setSymbolChinese(bo.getSymbolChinese());
        outputDto.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
        outputDto.setSort(String.valueOf(bo.getSort()));
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setEnableCreate(String.valueOf(bo.getEnableCreate()));
        outputDto.setEnableCancel(String.valueOf(bo.getEnableCancel()));
        outputDto.setNumberPrecision(String.valueOf(bo.getNumberPrecision()));
        outputDto.setCtime(String.valueOf(bo.getCtime()));
        outputDto.setMtime(String.valueOf(bo.getMtime()));

       /* outputDto.setFaceValue(String.valueOf(bo.getFaceValue()));
        outputDto.setSymbolType(String.valueOf(bo.getSymbolType()));
        outputDto.setBbSymbolType(bo.getBbSymbolType());
        outputDto.setSettlePrice(bo.getSettlePrice());
        outputDto.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
        outputDto.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
        outputDto.setBaseCurrency(bo.getBaseCurrency());
        outputDto.setSettleCurrency(bo.getSettleCurrency());
        outputDto.setFaceCurrency(bo.getFaceCurrency());
        outputDto.setQuoteCurrency(bo.getQuoteCurrency());
        outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));*/
        return outputDto;
    }

    @Override
    public GetAllSymbolOutputDto getAllBbSymbolList(GetAllSymbolInputDto inputDto) throws BbSymbolException {
        List<String> symbolList= iBbSymbolBiz.selectAllSymbol();
        GetAllSymbolOutputDto outputDto=new GetAllSymbolOutputDto();
        Set<String> set = new HashSet<>(symbolList);
        List<String> list= new ArrayList<>(set);
        outputDto.setSymbolList(list);
        return outputDto;
    }


    @Override
    public BbSymbolListOutputDto getPosPageByParam(BbSymbolListInputDto inputDto) throws BbSymbolException  {
        BbSymbolListBizBo listBizBo= iBbSymbolBiz.selectPosPageByParam(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getCurrentPage(),inputDto.getPageSize());
        BbSymbolListOutputDto outputDto=new BbSymbolListOutputDto();
        List<BbSymbolListOutputPage> pageList=new ArrayList<>();
        for(BbSymbolBizBo bo:listBizBo.getRows()) {
            BbSymbolListOutputPage po=new BbSymbolListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            po.setSymbol(bo.getSymbol());
            po.setAsset(bo.getAsset());
            po.setBbGroupId(bo.getBbGroupId());
            po.setPrecision(String.valueOf(bo.getPrecision()));
            po.setBbSymbolName(bo.getBbSymbolName());
            po.setBbSymbolNameSplit(bo.getBbSymbolNameSplit());
            po.setDisplayNameSplit(bo.getDisplayNameSplit());
            po.setDisplayName(bo.getDisplayName());
            po.setSymbolChinese(bo.getSymbolChinese());
            po.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            po.setSort(String.valueOf(bo.getSort()));
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setEnableCreate(String.valueOf(bo.getEnableCreate()));
            po.setEnableCancel(String.valueOf(bo.getEnableCancel()));
            po.setNumberPrecision(String.valueOf(bo.getNumberPrecision()));
            po.setCtime(String.valueOf(bo.getCtime()));
            po.setMtime(String.valueOf(bo.getMtime()));

/*            po.setSymbolType(String.valueOf(bo.getSymbolType()));
            po.setFaceValue(String.valueOf(bo.getFaceValue()));
            po.setBbSymbolType(bo.getBbSymbolType());
            po.setSettlePrice(bo.getSettlePrice());
            po.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            po.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            po.setPrivilege(String.valueOf(bo.getPrivilege()));
            po.setQuoteCurrency(bo.getQuoteCurrency());
            po.setBaseCurrency(bo.getBaseCurrency());
            po.setSettleCurrency(bo.getSettleCurrency());
            po.setFaceCurrency(bo.getFaceCurrency());*/
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public BbSymbolOutputDto getAllActiveBbSymbol(BbSymbolInputDto inputDto) throws BizException {
        BbSymbolOutputDto outputDto =new BbSymbolOutputDto();
        List<BbSymbolPo> bbSymbolPos= iBbSymbolDao.selectPosByStatus(1);
        List<GetBbSymbolOutputDto> activeBbSymbol=new ArrayList<>();
        bbSymbolPos.forEach(bo->{
            GetBbSymbolOutputDto info=new GetBbSymbolOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setSymbol(bo.getSymbol());
            info.setAsset(bo.getAsset());
            info.setBbGroupId(bo.getBbGroupId());
            info.setPrecision(String.valueOf(bo.getPrecision()));
            info.setBbSymbolName(bo.getBbSymbolName());
            info.setBbSymbolNameSplit(bo.getBbSymbolNameSplit());
            info.setDisplayName(bo.getDisplayName());
            info.setDisplayNameSplit(bo.getDisplayNameSplit());
            info.setSymbolChinese(bo.getSymbolChinese());
            info.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            info.setSort(String.valueOf(bo.getSort()));
            info.setStatus(String.valueOf(bo.getStatus()));
            info.setEnableCreate(String.valueOf(bo.getEnableCreate()));
            info.setEnableCancel(String.valueOf(bo.getEnableCancel()));
            info.setEnableCreate(String.valueOf(bo));
            info.setNumberPrecision(String.valueOf(bo.getNumberPrecision()));
            info.setCtime(String.valueOf(bo.getCtime()));
            info.setMtime(String.valueOf(bo.getMtime()));
/*            info.setFaceValue(String.valueOf(bo.getFaceValue()));
            info.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            info.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            info.setBbSymbolType(bo.getBbSymbolType());
            info.setSettlePrice(bo.getSettlePrice());
            info.setSymbolType(String.valueOf(bo.getSymbolType()));
            info.setQuoteCurrency(bo.getQuoteCurrency());
            info.setBaseCurrency(bo.getBaseCurrency());
            info.setSettleCurrency(bo.getSettleCurrency());
            info.setFaceCurrency(bo.getFaceCurrency());
            info.setPrivilege(String.valueOf(bo.getPrivilege()));*/
            activeBbSymbol.add(info);
        });
        outputDto.setAssetSymbolList(activeBbSymbol);
        return outputDto;
    }


    @Override
    public BbSymbolOutputDto checkHasBbSymbol(BbSymbolInputDto inputDto) throws  MngException {
        if(inputDto.getId()>0) {
            BbSymbolBizBo beforeBo=iBbSymbolBiz.getBbSymbolById(inputDto.getId());
            BbSymbolBizBo input= iBbSymbolBiz.checkHasBbSymbol(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName());
            //修改 传进来的参数能查询到数据 且查出来的数据不相等
            if(input!=null&&beforeBo.getId()!=input.getId())
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
        } else {
            //true已存在  报错
            if(iBbSymbolBiz.checkHasBbSymbol(inputDto.getAsset(),inputDto.getSymbol(),inputDto.getDisplayName())!=null)
                throw new MngException(MngExceptionCode.CONTRACT_EXIST_ERROR);
        }
        BbSymbolOutputDto outputDto=new BbSymbolOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
/*
        @Override
        public CheckHasLastPriceOutputDto checkHasLastPrice(CheckHasLastPriceInputDto inputDto) throws BbSymbolException {
            CheckHasLastPriceOutputDto outputDto=new CheckHasLastPriceOutputDto();
//        if(iLastPriceBiz.get(inputDto.getSymbol())!=null)
//            outputDto.setHasLastPrice(true);
//        else
            outputDto.setHasLastPrice(false);
            return outputDto;
        }
*/

    @Override
    public BbSymbolOutputDto GetBbSymbolListByAsset(BbSymbolInputDto inputDto) throws BizException {
        BbSymbolOutputDto outputDto=new BbSymbolOutputDto();
        List<BbSymbolPo> poList=iBbSymbolDao.selectPosByAsset(inputDto.getAsset());
        List<GetBbSymbolOutputDto> assetSymbolList=new ArrayList<>();
        poList.forEach(bo->{
            GetBbSymbolOutputDto info=new GetBbSymbolOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setSymbol(bo.getSymbol());
            info.setAsset(bo.getAsset());
            info.setPrecision(String.valueOf(bo.getPrecision()));
            info.setBbSymbolName(bo.getBbSymbolName());
            info.setBbSymbolNameSplit(bo.getBbSymbolNameSplit());
            info.setSymbolChinese(bo.getSymbolChinese());
            info.setBbGroupId(bo.getBbGroupId());
            info.setDisplayName(bo.getDisplayName());
            info.setDisplayNameSplit(bo.getDisplayNameSplit());
            info.setStep(DecimalUtil.toTrimLiteral(bo.getStep()));
            info.setSort(String.valueOf(bo.getSort()));
            info.setStatus(String.valueOf(bo.getStatus()));
            info.setEnableCreate(String.valueOf(bo.getEnableCreate()));
            info.setEnableCancel(String.valueOf(bo.getEnableCancel()));
            info.setNumberPrecision(String.valueOf(bo.getNumberPrecision()));
            info.setCtime(String.valueOf(bo.getCtime()));
            info.setMtime(String.valueOf(bo.getMtime()));

/*            info.setSymbolType(String.valueOf(bo.getSymbolType()));
            info.setBbSymbolType(bo.getBbSymbolType());
            info.setSettlePrice(bo.getSettlePrice());
            info.setDefaultPrice(DecimalUtil.toTrimLiteral(bo.getDefaultPrice()));
            info.setLastPrice(DecimalUtil.toTrimLiteral(bo.getLastPrice()));
            info.setFaceValue(String.valueOf(bo.getFaceValue()));
            info.setQuoteCurrency(bo.getQuoteCurrency());
            info.setBaseCurrency(bo.getBaseCurrency());
            info.setSettleCurrency(bo.getSettleCurrency());
            info.setFaceCurrency(bo.getFaceCurrency());
            info.setPrivilege(String.valueOf(bo.getPrivilege()));*/
            assetSymbolList.add(info);
        });
        outputDto.setAssetSymbolList(assetSymbolList);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteBbSymbol(DeleteInputDto inputDto) throws BizException {
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids) {
            BbSymbolPo po = iBbSymbolDao.selectPoById(Long.parseLong(id));
            if(po!=null) {
                if(iBbSymbolDao.deleteById(po.getId())>0) {
                    //删除交易对的时候顺便删除redis中的值
                    ExpDicPo dicPo= iExpDicDao.selectDicByKey("BbSymbolRedisKey");
                    if(dicPo!=null)
                    {
                        redisUtilDb0.hdel(dicPo.getValue(),po.getSymbol());
                    }
                    ExpDicPo dicPoDisplay= iExpDicDao.selectDicByKey("BbSymbolDisplayNameRedisKey");
                    if(dicPoDisplay!=null)
                    {
                        redisUtilDb0.hdel(dicPoDisplay.getValue(),po.getDisplayName());
                    }
                    logService.createOperationLog(user.getId(), user.getUserName(),
                            "BbSymbol", OperationType.Delete.toString(), JsonUtil.toJsonString(po),"");
                }
            }
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    /**
     * 根据组，查询每组，有多少合约交易对
     * 合约交易对,每组只能设置3个
     * @param inputDto
     * @return
     * @throws BizException
     */
    public BbSymbolOutputDto getBbSymbolGroupNum(BbSymbolInputDto inputDto) throws BizException{
        BbSymbolOutputDto outputDto=new BbSymbolOutputDto();
        List<BbSymbolPo> lists = iBbSymbolDao.selectPoGroupId(inputDto.getBbGroupId());
        if(lists!=null && lists.size()>0){
            outputDto.setNumber(lists.size());
        }
        return outputDto;
    }



}
