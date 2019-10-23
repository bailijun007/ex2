package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpCollectFeeSettingDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpCollectFeeSettingPo;
import com.hupa.exp.servermng.entity.collectfeesetting.*;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.service.def.IApiCollectFeeSettingControllerService;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiCollectFeeSettingControllerServiceImpl implements IApiCollectFeeSettingControllerService {

    @Autowired
    private IExpCollectFeeSettingDao iExpCollectFeeSettingDao;

    @Override
    public CollectFeeSettingOutputDto createCollectFeeSetting(CollectFeeSettingInputDto inputDto) throws BizException {
        if(inputDto.getCollectFeeNum()>4)
            throw new MngException(MngExceptionCode.COLLECT_FEE_NUM_ERROR);

        List<ExpCollectFeeSettingPo> pos=iExpCollectFeeSettingDao.selectSettingByStatus(1);
        for(ExpCollectFeeSettingPo po:pos)
        {
            po.setStatus(0);
            iExpCollectFeeSettingDao.updateById(po);
        }

        ExpCollectFeeSettingPo po= ConventObjectUtil.conventObject(inputDto,ExpCollectFeeSettingPo.class);
        po.setCtime(System.currentTimeMillis());
        po.setMtime(System.currentTimeMillis());
        po.setStatus(1);
        iExpCollectFeeSettingDao.insert(po);

        CollectFeeSettingOutputDto outputDto=new CollectFeeSettingOutputDto();
        return outputDto;
    }

    @Override
    public CollectFeeSettingOutputDto editCollectFeeSetting(CollectFeeSettingInputDto inputDto) throws BizException {

        ExpCollectFeeSettingPo oldPo=iExpCollectFeeSettingDao.selectPoById(inputDto.getId());
        oldPo.setStatus(0);
        oldPo.setMtime(System.currentTimeMillis());
        iExpCollectFeeSettingDao.updateById(oldPo);
        ExpCollectFeeSettingPo newPo=new ExpCollectFeeSettingPo();
        newPo.setStatus(1);
        newPo.setCtime(System.currentTimeMillis());
        newPo.setMtime(System.currentTimeMillis());
        newPo.setCollectFeeNum(inputDto.getCollectFeeNum());
        newPo.setCollectFeeTime(inputDto.getCollectFeeTime());
        iExpCollectFeeSettingDao.insert(newPo);
        CollectFeeSettingOutputDto outputDto=new CollectFeeSettingOutputDto();
        return outputDto;
    }

    @Override
    public CollectFeeSettingInfoOutputDto getCollectFeeSettingById(CollectFeeSettingInfoInputDto inputDto) throws BizException {
        ExpCollectFeeSettingPo po=iExpCollectFeeSettingDao.selectPoById(inputDto.getId());
        CollectFeeSettingInfoOutputDto outputDto=ConventObjectUtil.conventObject(po,CollectFeeSettingInfoOutputDto.class);
        return outputDto;
    }

    @Override
    public CollectFeeSettingListOutputDto getCollectFeeSettingList(CollectFeeSettingListInputDto inputDto) throws BizException {
        IPage<ExpCollectFeeSettingPo> pageData=iExpCollectFeeSettingDao.selectSettingPageData(1,inputDto.getCurrentPage(),inputDto.getPageSize());
        CollectFeeSettingListOutputDto outputDto=new CollectFeeSettingListOutputDto();
        outputDto.setTotal(pageData.getTotal());
        outputDto.setTotalCount(pageData.getTotal());
        List<CollectFeeSettingInfoOutputDto> rows=new ArrayList<>();
        for(ExpCollectFeeSettingPo po:pageData.getRecords())
        {
            CollectFeeSettingInfoOutputDto row=new CollectFeeSettingInfoOutputDto();
            row.setStatus(String.valueOf(po.getStatus()));
            row.setCtime(String.valueOf(po.getCtime()));
            row.setMtime(String.valueOf(po.getMtime()));
            row.setCollectFeeNum(String.valueOf(po.getCollectFeeNum()));
            row.setCollectFeeTime(String.valueOf(po.getCollectFeeTime()));
            row.setId(String.valueOf(po.getId()));
            rows.add(row);
        }
        outputDto.setRows(rows);
        return outputDto;
    }
}
