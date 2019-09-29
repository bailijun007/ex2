package com.hupa.exp.bizother.service.operationlog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.bizother.entity.ExpOperationLogBizBo;
import com.hupa.exp.bizother.entity.ExpOperationLogListBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.daoex2.dao.expv2.def.IExpOperationLogDao;
import com.hupa.exp.daoex2.entity.po.expv2.ExpOperationLogPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.modelmbean.ModelMBean;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpOperationLogServiceImpl implements IExpOperationLogService {
    @Autowired
    private IExpOperationLogDao iExpOperationLogDao;

    @Override
    public long createOperationLog(long accountId,String userName,String operationModule,String operationType,
                                   String before,String after)
    {
        ExpOperationLogPo po= new ExpOperationLogPo();
        po.setAfter(after);
        po.setBefore(before);
        po.setAccountId(accountId);
        po.setUserName(userName);
        po.setOperationModule(operationModule);
        po.setOperationType(operationType);
        po.setOperationTime(System.currentTimeMillis());
        po.setCtime(System.currentTimeMillis());
        po.setMtime(System.currentTimeMillis());
        return iExpOperationLogDao.insert(po);
    }

    @Override
    public ExpOperationLogListBizBo getExpOperationLogList(String operationModule, String operationType, long currentPage, long pageSize) {
        IPage<ExpOperationLogPo> poList=iExpOperationLogDao.selectPagePos(currentPage,pageSize,
                                                                                operationModule,operationType);

        ExpOperationLogListBizBo pageBo=new ExpOperationLogListBizBo();
        List<ExpOperationLogBizBo> boList=new ArrayList<>();
        for(ExpOperationLogPo po:poList.getRecords())
        {
            ExpOperationLogBizBo bo=ConventObjectUtil.conventObject(po,ExpOperationLogBizBo.class);
            boList.add(bo);
        }
        pageBo.setRows(boList);
        pageBo.setTotal(poList.getTotal());
        return pageBo;
    }
}
