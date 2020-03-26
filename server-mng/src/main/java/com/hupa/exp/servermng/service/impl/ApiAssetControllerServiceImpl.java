package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.bizother.entity.account.AssetPageListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IAssetBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 创建币种
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AssetOutputDto createAsset(AssetInputDto inputDto) throws BizException {
        //根据币的名称，查询是否存在
        if(iAssetBiz.checkHasAsset(inputDto.getRealName()))
            throw new MngException(MngExceptionCode.ASSET_EXIST_ERROR);

        AssetBizBo bo= ConventObjectUtil.conventObject(inputDto,AssetBizBo.class);
        bo.setId(inputDto.getId());
        bo.setChainAppointId(inputDto.getChainAppointId());
        bo.setChainName(inputDto.getChainNname());
        bo.setRealName(inputDto.getRealName());
        bo.setDisplayName(inputDto.getDisplaynName());
        bo.setPrecision(inputDto.getPrecision());
        bo.setPrivilege(inputDto.getPrivilege());
        bo.setStatus(inputDto.getStatus());
        bo.setSort(inputDto.getSort());
        bo.setIcon(inputDto.getIcon());
        bo.setIconImg(inputDto.getIconImg());
        bo.setMinDepositVolume(inputDto.getMinDepositVolume());
        bo.setMinWithdrawVolume(inputDto.getMinWithdrawVolume());
        bo.setWithdrawFee(inputDto.getWithdrawFee());
        bo.setC2cFee(inputDto.getC2cFee());
        bo.setMtime(System.currentTimeMillis());
        bo.setDwType(inputDto.getDwType());
        bo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        bo.setEnableFlagPcAccount(inputDto.getEnableFlagPcAccount());
        bo.setEnableFlagBbAccount(inputDto.getEnableFlagBbAccount());
        bo.setEnableFlagPcMarket(inputDto.getEnableFlagPcMarket());
        bo.setEnableFlagBbMarket(inputDto.getEnableFlagBbMarket());
        iAssetBiz.createAsset(bo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Insert.toString(), JsonUtil.toJsonString(bo),"");
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }


    /**
     * 修改币种
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AssetOutputDto editAsset(AssetInputDto inputDto) throws BizException {
        AssetBizBo beforeBo= iAssetBiz.queryAssetById(inputDto.getId());
        if(beforeBo!=null) {
            //修改后币种变了
            if(!beforeBo.getRealName().equals(inputDto.getRealName())) {
                //判断修改的币种是否存在  存在报错出去
                if(iAssetBiz.checkHasAsset(inputDto.getRealName()))
                    throw new MngException(MngExceptionCode.ASSET_EXIST_ERROR);
            }
        }
        AssetBizBo afterBo=new AssetBizBo();
        afterBo.setId(inputDto.getId());
        afterBo.setIcon(inputDto.getIcon());
        afterBo.setIconImg(inputDto.getIconImg());
        afterBo.setChainAppointId(inputDto.getChainAppointId());//beforeBo.getChainAppointId()
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
        afterBo.setC2cFee(inputDto.getC2cFee());
        afterBo.setChainTransactionUrl(inputDto.getChainTransactionUrl());
        afterBo.setEnableFlagPcAccount(inputDto.getEnableFlagPcAccount());
        afterBo.setEnableFlagBbAccount(inputDto.getEnableFlagBbAccount());
        afterBo.setEnableFlagPcMarket(inputDto.getEnableFlagPcMarket());
        afterBo.setEnableFlagBbMarket(inputDto.getEnableFlagBbMarket());
        afterBo.setMtime(System.currentTimeMillis());
        afterBo.setCtime(beforeBo.getCtime());
        iAssetBiz.editAsset(afterBo);
        //添加操作日志
        ExpUserBizBo user= sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(afterBo));
        AssetOutputDto outputDto=new AssetOutputDto();
        return outputDto;
    }

    /**
     * 根据ID查询币种
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public GetAssetOutputDto getAssetById(AssetInputDto inputDto) throws BizException {
        GetAssetOutputDto outputDto=new GetAssetOutputDto();
        AssetBizBo bo=  iAssetBiz.queryAssetById(inputDto.getId());
        if(bo!=null){
            outputDto.setId(String.valueOf(bo.getId()));
            outputDto.setChainAppointId(String.valueOf(bo.getChainAppointId()==null?"":bo.getChainAppointId()));
            outputDto.setIcon(bo.getIcon());
            outputDto.setIconImg(bo.getIconImg());
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
            outputDto.setC2cFee(DecimalUtil.trimZeroPlainString(bo.getC2cFee()));
            outputDto.setChainTransactionUrl(bo.getChainTransactionUrl());
            outputDto.setDwType(String.valueOf(bo.getDwType()));
            outputDto.setEnableFlagPcAccount(String.valueOf(bo.getEnableFlagPcAccount()));
            outputDto.setEnableFlagBbAccount(String.valueOf(bo.getEnableFlagBbAccount()));
            outputDto.setEnableFlagPcMarket(String.valueOf(bo.getEnableFlagPcMarket()));
            outputDto.setEnableFlagBbMarket(String.valueOf(bo.getEnableFlagBbMarket()));
        }
        return outputDto;
    }

    /**
     * 获取币种列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public AssetListOutputDto getAssetList(AssetListInputDto inputDto) throws BizException {
        AssetListOutputDto outputDto=new AssetListOutputDto();
        List<AssetListOutputPage> pageList=new ArrayList<>();
        AssetPageListBizBo listBizBo= iAssetBiz.queryAssetList(inputDto.getRealName(),inputDto.getCurrentPage(),inputDto.getPageSize());
        if(listBizBo!=null){
            for(AssetBizBo bo:listBizBo.getRows()) {
                AssetListOutputPage po=new AssetListOutputPage();
                po.setId(String.valueOf(bo.getId()));
                po.setIcon(bo.getIcon());
                po.setIconImg(bo.getIconImg());
                po.setChainAppointId(String.valueOf(bo.getChainAppointId()==null ? "" : bo.getChainAppointId()));
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
                po.setC2cFee(DecimalUtil.trimZeroPlainString(bo.getC2cFee()));
                po.setChainTransactionUrl(bo.getChainTransactionUrl());
                po.setDwType(String.valueOf(bo.getDwType()));
                po.setEnableFlagPcAccount(String.valueOf(bo.getEnableFlagPcAccount()));
                po.setEnableFlagBbAccount(String.valueOf(bo.getEnableFlagBbAccount()));
                po.setEnableFlagPcMarket(String.valueOf(bo.getEnableFlagPcMarket()));
                po.setEnableFlagBbMarket(String.valueOf(bo.getEnableFlagBbMarket()));
                pageList.add(po);
            }
            outputDto.setTotal(listBizBo.getTotal());
        }
        outputDto.setRows(pageList);
        return outputDto;
    }

    /**
     *  根据币种名称，查询币种
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public RealNameListOutPutDto getRealNameList(AssetInputDto inputDto) throws BizException {
        AssetListBizBo bizBo= iAssetBiz.queryRealNameList();
        RealNameListOutPutDto outPutDto=new RealNameListOutPutDto();
        outPutDto.setAssetList(bizBo);
        outPutDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outPutDto;
    }

    /**
     * 根据币种名称，查询是否存在
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public CheckHasAssetOutputDto checkHasAsset(CheckHasAssetInputDto inputDto) throws BizException {
        CheckHasAssetOutputDto outputDto=new CheckHasAssetOutputDto();
        boolean hasAsset = iAssetBiz.checkHasAsset(inputDto.getRealName());
        outputDto.setHasAsset(hasAsset);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     *  删除币种，可批量删除
     *  1、同时删除redis缓存种币种
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public DeleteOutputDto deleteAsset(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids) {
            AssetPo po = iAssetDao.selectPoById(Long.parseLong(id));
            if(po!=null) {
                ExpDicPo dicPo= iExpDicDao.selectDicByKey("AssetRedisKey");
                if(iAssetDao.deleteById(po.getId())>0) {
                    if(dicPo!=null) {
                        redisUtilDb0.hdel(dicPo.getValue(),po.getRealName());
                    }
                }
            }
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(), OperationModule.Asset.toString(),
                OperationType.Delete.toString(), inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
