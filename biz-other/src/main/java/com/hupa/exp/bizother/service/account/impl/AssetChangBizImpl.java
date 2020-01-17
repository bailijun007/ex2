package com.hupa.exp.bizother.service.account.impl;

import com.hupa.exp.bizother.service.account.def.IAssetChangBiz;
import org.springframework.stereotype.Service;

/**
 *  资产变动
 */
@Service
public class AssetChangBizImpl implements IAssetChangBiz {

   /* private PcAssetChangeMongoPageBizBo getBizBo(MongoPage<PcAssetChangeAssetMongoPo> mongoPagePo ){
        PcAssetChangeMongoPageBizBo bizBo = new PcAssetChangeMongoPageBizBo();
        List<PcAssetChangeMongoBizBo> boList = new ArrayList<>();
        for(PcAssetChangeAssetMongoPo po :  mongoPagePo.getRows()){
            PcAssetChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
            boList.add(bo);
        }
        bizBo.setRows(boList);
        bizBo.setTotal(mongoPagePo.getTotalCount());
        bizBo.setPageSize(mongoPagePo.getPageSize());
        return bizBo;
    }

    public PcAssetChangeMongoPageBizBo getBizBo(List<PcAssetChangeAssetMongoPo> pos){
        PcAssetChangeMongoPageBizBo bizBo = new PcAssetChangeMongoPageBizBo();
        List<PcAssetChangeMongoBizBo> boList = new ArrayList<>();
        for(PcAssetChangeAssetMongoPo po :  pos){
            PcAssetChangeMongoBizBo bo=ConventObjectUtil.conventObject(po,PcAssetChangeMongoBizBo.class);
            boList.add(bo);
        }
        bizBo.setRows(boList);
        bizBo.setTotal(pos.size());
        bizBo.setPageSize(pos.size());
        return bizBo;

    }*/
}
