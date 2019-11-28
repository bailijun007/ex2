package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.dic.expv2.DbKeyDic;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.AssetPageListBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomongo.dao.expv2.def.IMongoTableDao;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssetControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiAssetControllerServiceImpl implements IApiAssetControllerService {
    @Autowired
    private IAssetBiz iAssetBiz;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IAssetDao iAssetDao;


    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Autowired
    private IExpDicDao iExpDicDao;

    @Autowired
    private IMongoTableDao iMongoTableDao;

    @Override
    public AssetOutputDto createAsset(AssetInputDto inputDto) throws BizException {
        if(iAssetBiz.checkHasAsset(inputDto.getRealName()))
            throw new MngException(MngExceptionCode.ASSET_EXIST_ERROR);
        AssetBizBo bo= ConventObjectUtil.conventObject(inputDto,AssetBizBo.class);
        bo.setId(inputDto.getId());
        //bo.setSymbol(inputDto.getSymbol());
        bo.setChainAppointId(inputDto.getChainAppointId());
        bo.setChainName(inputDto.getChainNname());
        bo.setRealName(inputDto.getRealName());
        bo.setDisplayName(inputDto.getDisplaynName());
        bo.setPrecision(inputDto.getPrecision());
        bo.setPrivilege(inputDto.getPrivilege());
        bo.setStatus(inputDto.getStatus());
        bo.setSort(inputDto.getSort());
        bo.setIcon(inputDto.getIcon());
        bo.setMinDepositVolume(inputDto.getMinDepositVolume());
        bo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        bo.setWithdrawFee(inputDto.getWithdrawFee());
        bo.setMtime(System.currentTimeMillis());
        bo.setDwType(inputDto.getDwType());
        bo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        iAssetBiz.createAsset(bo);

       ExpDicPo dicPo= iExpDicDao.selectDicByKey(DbKeyDic.MongoDbAssetTableKey);
       if(dicPo!=null)
       {
           List<ExpDicPo> dicPoList=iExpDicDao.selectDicListByParentId(Integer.parseInt(String.valueOf(dicPo.getId())));

           List<String> tableNames= dicPoList.stream().map(p -> p.getKey().replace("{asset}",bo.getRealName())).collect(Collectors.toList());

           iMongoTableDao.createMongoTable(tableNames);
       }

        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Asset.toString(),
                OperationType.Insert.toString(),
                JsonUtil.toJsonString(bo),"");
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }

    @Override
    public AssetOutputDto editAsset(AssetInputDto inputDto) throws BizException {
        AssetBizBo beforeBo= iAssetBiz.queryAssetById(inputDto.getId());
        if(beforeBo!=null)
        {
            //修改后币种变了
            if(!beforeBo.getRealName().equals(inputDto.getRealName()))
            {
                //判断修改的币种是否存在  存在报错出去
                if(iAssetBiz.checkHasAsset(inputDto.getRealName()))
                    throw new MngException(MngExceptionCode.ASSET_EXIST_ERROR);
            }
        }
        AssetBizBo afterBo=new AssetBizBo();
        afterBo.setId(inputDto.getId());
        //afterBo.setSymbol(inputDto.getSymbol());
        //链上ID和真实名不让修改 用之前的
        afterBo.setIcon(inputDto.getIcon());
        afterBo.setChainAppointId(beforeBo.getChainAppointId());
        afterBo.setRealName(beforeBo.getRealName());
        afterBo.setChainName(inputDto.getChainNname());
        afterBo.setDisplayName(inputDto.getDisplaynName());
        afterBo.setPrecision(inputDto.getPrecision());
        afterBo.setPrivilege(inputDto.getPrivilege());
        afterBo.setStatus(inputDto.getStatus());
        afterBo.setSort(inputDto.getSort());
        afterBo.setDwType(inputDto.getDwType());
        afterBo.setMinDepositVolume(inputDto.getMinDepositVolume());
        afterBo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        afterBo.setWithdrawFee(inputDto.getWithdrawFee());
        afterBo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        afterBo.setMtime(System.currentTimeMillis());
        afterBo.setCtime(beforeBo.getCtime());
        iAssetBiz.editAsset(afterBo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Asset.toString(),
                OperationType.Update.toString(),
                JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }

    @Override
    public GetAssetOutputDto getAssetById(GetAssetInputDto inputDto) throws BizException {
        AssetBizBo bo=  iAssetBiz.queryAssetById(inputDto.getId());
        GetAssetOutputDto outputDto=new GetAssetOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        //outputDto.setSymbol(bo.getSymbol());
        outputDto.setChainAppointId(String.valueOf(bo.getChainAppointId()==null?"":bo.getChainAppointId()));
        outputDto.setIcon(bo.getIcon());
        outputDto.setChainName(bo.getChainName());
        outputDto.setRealName(bo.getRealName());
        outputDto.setDisplayName(bo.getDisplayName());
        outputDto.setPrecision(String.valueOf(bo.getPrecision()));
        outputDto.setPrivilege(String.valueOf(bo.getPrivilege()));
        outputDto.setStatus(String.valueOf(bo.getStatus()));
        outputDto.setSort(String.valueOf(bo.getSort()));
        outputDto.setMtime(String.valueOf(System.currentTimeMillis()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        outputDto.setMinDepositVolume(String.valueOf(bo.getMinDepositVolume()));
        outputDto.setMinWithdrawVolume(DecimalUtil.trimZeroPlainString(bo.getMinWithdrawVolume()));
        outputDto.setWithdrawFee(DecimalUtil.trimZeroPlainString(bo.getWithdrawFee()));
        outputDto.setChainTransactionUrl(bo.getChainTransactionUrl());
        outputDto.setDwType(String.valueOf(bo.getDwType()));
        return outputDto;
    }

    @Override
    public AssetListOutputDto getAssetList(AssetListInputDto inputDto) throws BizException {
        AssetPageListBizBo listBizBo= iAssetBiz.queryAssetList(inputDto.getRealName(),inputDto.getCurrentPage(),inputDto.getPageSize());
        AssetListOutputDto outputDto=new AssetListOutputDto();
        List<AssetListOutputPage> pageList=new ArrayList<>();
        for(AssetBizBo bo:listBizBo.getRows())
        {
            AssetListOutputPage po=new AssetListOutputPage();
            po.setId(String.valueOf(bo.getId()));
            //po.setSymbol(bo.getSymbol());
            po.setIcon(bo.getIcon());
            po.setChainAppointId(String.valueOf(bo.getChainAppointId()==null?"":bo.getChainAppointId()));
            po.setChainName(bo.getChainName());
            po.setRealName(bo.getRealName());
            po.setDisplayName(bo.getDisplayName());
            po.setPrecision(String.valueOf(bo.getPrecision()));
            po.setPrivilege(String.valueOf(bo.getPrivilege()));
            po.setStatus(String.valueOf(bo.getStatus()));
            po.setMtime(String.valueOf(bo.getMtime()));
            po.setCtime(String.valueOf(bo.getCtime()));
            po.setSort(String.valueOf(bo.getSort()));
            po.setMinDepositVolume(DecimalUtil.trimZeroPlainString(bo.getMinDepositVolume()));
            po.setMinWithdrawVolume(DecimalUtil.trimZeroPlainString(bo.getMinWithdrawVolume()));
            po.setWithdrawFee(DecimalUtil.trimZeroPlainString(bo.getWithdrawFee()));
            po.setChainTransactionUrl(bo.getChainTransactionUrl());
            po.setDwType(String.valueOf(bo.getDwType()));
            pageList.add(po);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        return outputDto;
    }

    @Override
    public RealNameListOutPutDto getRealNameList(RealNameListInputDto inputDto) throws BizException {
        AssetListBizBo bizBo= iAssetBiz.queryRealNameList();
        RealNameListOutPutDto outPutDto=new RealNameListOutPutDto();
        outPutDto.setAssetList(bizBo);
        outPutDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outPutDto;
    }

    @Override
    public CheckHasAssetOutputDto checkHasAsset(CheckHasAssetInputDto inputDto) throws BizException {
        boolean hasAsset= iAssetBiz.checkHasAsset(inputDto.getRealName());
        CheckHasAssetOutputDto outputDto=new CheckHasAssetOutputDto();
        outputDto.setHasAsset(hasAsset);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteAsset(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            AssetPo po= iAssetDao.selectPoById(Long.parseLong(id));
            if(po!=null)
            {
                ExpDicPo dicPo= iExpDicDao.selectDicByKey("AssetRedisKey");
                if(iAssetDao.deleteById(po.getId())>0)
                {
                    if(dicPo!=null)
                    {
                        redisUtilDb0.hdel(dicPo.getValue(),po.getRealName());
                    }
                }
            }
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Asset.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
