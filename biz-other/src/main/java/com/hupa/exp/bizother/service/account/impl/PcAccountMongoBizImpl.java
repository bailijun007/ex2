package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.PcAccountLogMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAccountLogMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IPcAccountMongoBiz;
import com.hupa.exp.daoex2.dao.expv2.def.IPcAccountMongoDao;
import com.hupa.exp.daoex2.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daoex2.entity.po.expv2mongo.PcAccountLogMongoPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PcAccountMongoBizImpl implements IPcAccountMongoBiz {

    @Autowired
    private IPcAccountMongoDao iPcAccountMongoDao;
    @Override
    public PcAccountLogMongoPageBizBo selectPcAccountLogPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<PcAccountLogMongoPo> pageData=iPcAccountMongoDao.selectPcAccountLogPos(
                symbol, id, currentPage, pageSize);
        PcAccountLogMongoPageBizBo pageBizBo=new PcAccountLogMongoPageBizBo();
        pageBizBo.setTotal(pageData.getTotalCount());
        pageBizBo.setPageSize(pageSize);
        List<PcAccountLogMongoBizBo> bizBoList=new ArrayList<>();
        for(PcAccountLogMongoPo po:pageData.getRows())
        {
            PcAccountLogMongoBizBo bo= ConventObjectUtil.conventObject(po,PcAccountLogMongoBizBo.class);
            bizBoList.add(bo);
        }
        pageBizBo.setRows(bizBoList);
        return pageBizBo;
    }
}
