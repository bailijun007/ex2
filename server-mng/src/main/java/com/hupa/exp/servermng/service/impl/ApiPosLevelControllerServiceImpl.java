package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.account.PcPosLevelBizBo;
import com.hupa.exp.bizother.entity.account.PosLevelPageListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.account.def.IPosLevelBiz;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.DecimalUtil;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcPosLevelDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcPosLevelPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.poslevel.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiPosLevelControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ApiPosLevelControllerServiceImpl implements IApiPosLevelControllerService {
    @Autowired
    private IPosLevelBiz iPosLevelBiz;

    @Autowired
    private IPcPosLevelDao iPcPosLevelDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpDicDao dicDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public PosLevelOutputDto createOrEditPosLevel(PosLevelInputDto inputDto) throws BizException {
        PcPosLevelBizBo bo = ConventObjectUtil.conventObject(inputDto, PcPosLevelBizBo.class);
        if (inputDto.getId() > 0) {
            PcPosLevelBizBo beforeBo = iPosLevelBiz.queryPosLevelById(bo.getId());
            bo.setMtime(System.currentTimeMillis());
            iPosLevelBiz.editPosLevel(bo);
            ExpUserBizBo user = sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(), user.getUserName(),
                    OperationModule.PcFee.toString(), OperationType.Insert.toString(),
                    JsonUtil.toJsonString(beforeBo), JsonUtil.toJsonString(bo));
        } else {
            bo.setMtime(System.currentTimeMillis());
            bo.setCtime(System.currentTimeMillis());
            iPosLevelBiz.createPosLevel(bo);
        }

        PosLevelOutputDto outputDto = new PosLevelOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PosLevelInfoOutputDto getPosLevel(PosLevelInfoInputDto inputDto) throws BizException {
        PcPosLevelBizBo bo = iPosLevelBiz.queryPosLevelById(inputDto.getId());
        PosLevelInfoOutputDto outputDto = new PosLevelInfoOutputDto();
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setAsset(bo.getAsset());
        outputDto.setSymbol(bo.getSymbol());
        outputDto.setGear(String.valueOf(bo.getGear()));
        outputDto.setMinAmt(String.valueOf(bo.getMinAmt()));
        outputDto.setMaxAmt(String.valueOf(bo.getMaxAmt()));
        outputDto.setMaxLeverage(DecimalUtil.toTrimLiteral(bo.getMaxLeverage()));
        outputDto.setPosHoldMarginRatio(DecimalUtil.toTrimLiteral(bo.getPosHoldMarginRatio()));
        outputDto.setMinHoldMarginRatio(DecimalUtil.toTrimLiteral(bo.getMinHoldMarginRatio()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PosLevelListOutputDto getPosLevelList(PosLevelListInputDto inputDto) throws BizException {
        PosLevelListOutputDto outputDto = new PosLevelListOutputDto();
        PosLevelPageListBizBo listBizBo = iPosLevelBiz.queryPosLevelList(inputDto.getAsset(), inputDto.getSymbol(),
                inputDto.getCurrentPage(), inputDto.getPageSize());
        List<PosLevelInfoOutputDto> outputDtoList = new ArrayList<>();
        for (PcPosLevelBizBo bo : listBizBo.getRows()) {
            PosLevelInfoOutputDto info = new PosLevelInfoOutputDto();
            info.setId(String.valueOf(bo.getId()));
            info.setAsset(bo.getAsset());
            info.setSymbol(bo.getSymbol());
            info.setGear(String.valueOf(bo.getGear()));
            info.setMinAmt(String.valueOf(bo.getMinAmt()));
            info.setMaxAmt(String.valueOf(bo.getMaxAmt()));
            info.setMaxLeverage(DecimalUtil.toTrimLiteral(bo.getMaxLeverage()));
            info.setPosHoldMarginRatio(DecimalUtil.toTrimLiteral(bo.getPosHoldMarginRatio()));
            info.setMinHoldMarginRatio(DecimalUtil.toTrimLiteral(bo.getMinHoldMarginRatio()));
            outputDtoList.add(info);
        }
        outputDto.setRows(outputDtoList);
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTotal(listBizBo.getTotal());
        return outputDto;
    }

    @Override
    public DeleteOutputDto deletePosLevel(DeleteInputDto inputDto) throws BizException {
        String[] ids = inputDto.getIds().split(",");
        for (String id : ids) {
            iPcPosLevelDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(), user.getUserName(),
                OperationModule.PosLevel.toString(), OperationType.Delete.toString(),
                inputDto.getIds(), "");
        DeleteOutputDto outputDto = new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public PosLevelOutputDto batchUpdate(PosLevelInputDto inputDto) throws BizException {
        List<PcPosLevelPo> pos = iPcPosLevelDao.selectAllPosLevelList(inputDto.getAsset(), inputDto.getSymbol());//Long.MAX_VALUE
        List<PcPosLevelPo> boList = new ArrayList<>();
        for (PcPosLevelPo po : pos) {
            PcPosLevelPo updateData = new PcPosLevelPo();
            BeanUtils.copyProperties(po, updateData);
            updateData.setMinHoldMarginRatio(new BigDecimal(1).divide(po.getMaxLeverage(), 4, BigDecimal.ROUND_HALF_DOWN));
            updateData.setPosHoldMarginRatio(po.getMinHoldMarginRatio());
            updateData.setMtime(System.currentTimeMillis());
            iPcPosLevelDao.updateById(updateData);
            boList.add(updateData);
        }
        ExpDicPo dicPo = dicDao.selectDicByKey("PosLevel");
        if (dicPo != null) {
            redisUtilDb0.hset(dicPo.getValue(), inputDto.getAsset() + "__" + inputDto.getSymbol(), JsonUtil.toJsonString(boList));
        }
         PosLevelOutputDto outputDto = new PosLevelOutputDto();
        return outputDto;
    }
}
