package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.entity.account.MongoBo.PcAccountLogMongoBizBo;
import com.hupa.exp.bizother.entity.account.MongoBo.PcAccountLogMongoPageBizBo;
import com.hupa.exp.bizother.service.account.def.IPcAccountMongoBiz;
import com.hupa.exp.daomongo.dao.expv2.def.IPcAccountAssetMongoDao;
import com.hupa.exp.daomongo.entity.po.expv2mongo.MongoPage;
import com.hupa.exp.daomongo.entity.po.expv2mongo.PcAccountLogAssetMongoPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PcAccountMongoBizImpl implements IPcAccountMongoBiz {

    @Autowired
    private IPcAccountAssetMongoDao iPcAccountAssetMongoDao;
    @Override
    public PcAccountLogMongoPageBizBo selectPcAccountLogPageData(String symbol, long id, long currentPage, int pageSize) {
        MongoPage<PcAccountLogAssetMongoPo> pageData= iPcAccountAssetMongoDao.selectPcAccountLogPos(
                symbol, id, currentPage, pageSize);
        PcAccountLogMongoPageBizBo pageBizBo=new PcAccountLogMongoPageBizBo();
        pageBizBo.setTotal(pageData.getTotalCount());
        pageBizBo.setPageSize(pageSize);
        List<PcAccountLogMongoBizBo> bizBoList=new ArrayList<>();
        for(PcAccountLogAssetMongoPo po:pageData.getRows())
        {
            PcAccountLogMongoBizBo bo= ConventObjectUtil.conventObject(po,PcAccountLogMongoBizBo.class);
            bizBoList.add(bo);
        }
        pageBizBo.setRows(bizBoList);
        return pageBizBo;
    }
}
