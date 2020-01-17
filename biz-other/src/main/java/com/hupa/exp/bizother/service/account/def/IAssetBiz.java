package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.AssetPageListBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface IAssetBiz {


    long createAsset(AssetBizBo expUserBo) throws BizException;

    long editAsset(AssetBizBo expUserBo) throws BizException;

    AssetPageListBizBo queryAssetList(String realName, long currentPage, long pageSize);

    AssetListBizBo queryRealNameList();

    AssetBizBo queryAssetById(long id);

    /**
     * 根据币的名称，查询是否存在
     * @param symbol
     * @return
     */
    boolean checkHasAsset(String symbol);
}
