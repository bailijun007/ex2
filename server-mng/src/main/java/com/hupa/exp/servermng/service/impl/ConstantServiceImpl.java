package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IExpConstantDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpConstantPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.constant.*;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConstantServiceImpl implements IConstantService {

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpConstantDao iExpConstantDao;

    @Override
    public ConstantOutputDto createOrEditConstant(ConstantInputDto inputDto) throws BizException {
        ExpConstantPo po=new ExpConstantPo();
        String value = "";
        try {
            value = URLDecoder.decode(inputDto.getValue(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String symbol = "";
        try {
            symbol = URLDecoder.decode(inputDto.getSplitSymbol(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        po.setKey(inputDto.getKey());
        po.setSplitSymbol(symbol);
        String[] contents= value.split("[|]");
        Map<String,String> map=new HashMap<>();
        for(String str:contents)
        {
            String[] keyValue=str.split(",");
            map.put(keyValue[0],keyValue.length>1?keyValue[1]:"");
        }
        po.setValue(JSON.toJSONString(map));
        po.setMtime(System.currentTimeMillis());
        if(inputDto.getId()>0)
        {
            ExpConstantPo beforePo=iExpConstantDao.selectPoById(inputDto.getId());
            if(!inputDto.getKey().equals(beforePo.getKey())&&iExpConstantDao.selectOnePo(inputDto.getKey())!=null)
                throw new MngException(MngExceptionCode.CONSTANT_EXIST_ERROR);
            po.setId(inputDto.getId());
            iExpConstantDao.updateById(po);
        }
        else
        {
            if(iExpConstantDao.selectOnePo(inputDto.getKey())!=null)
                throw new MngException(MngExceptionCode.CONSTANT_EXIST_ERROR);
            po.setCtime(System.currentTimeMillis());
            iExpConstantDao.insert(po);
        }

        ConstantOutputDto outputDto=new ConstantOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ConstantInfoOutputDto getConstant(ConstantInfoInputDto inputDto) throws BizException {
        ExpConstantPo po=iExpConstantDao.selectPoById(inputDto.getId());
        ConstantInfoOutputDto outputDto=new ConstantInfoOutputDto();
        if(po!=null)
        {
            outputDto.setId(String.valueOf(po.getId()));
            outputDto.setCtime(String.valueOf(po.getCtime()));
            outputDto.setMtime(String.valueOf(po.getMtime()));
            outputDto.setKey(po.getKey());
            outputDto.setValue(po.getValue());
            outputDto.setSplitSymbol(po.getSplitSymbol());
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public ConstantListOutputDto getConstantPage(ConstantListInputDto inputDto) throws BizException {
        IPage<ExpConstantPo> pageData=iExpConstantDao.selectPagePos(inputDto.getKey(),
                inputDto.getCurrentPage(),inputDto.getPageSize());
        List<ConstantInfoOutputDto> rows=new ArrayList<>();
        pageData.getRecords().forEach(po->{
            ConstantInfoOutputDto info=new ConstantInfoOutputDto();
            info.setId(String.valueOf(po.getId()));
            info.setCtime(String.valueOf(po.getCtime()));
            info.setMtime(String.valueOf(po.getMtime()));
            info.setKey(po.getKey());
            info.setValue(po.getValue());
            info.setSplitSymbol(po.getSplitSymbol());
            rows.add(info);
        });
        ConstantListOutputDto outputDto=new ConstantListOutputDto();
        outputDto.setRows(rows);
        outputDto.setTotal(pageData.getTotal());
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteConstant(DeleteInputDto inputDto) throws BizException {

        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpConstantDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.Constant.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
