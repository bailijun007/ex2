package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.AssetBizBo;
import com.hupa.exp.bizother.entity.account.CoinPageListBizBo;
import com.hupa.exp.bizother.entity.account.AssetListBizBo;
import com.hupa.exp.common.exception.BizException;

public interface IAssetBiz {


    long createCoin(AssetBizBo expUserBo) throws BizException;

    long editCoin(AssetBizBo expUserBo) throws BizException;

    CoinPageListBizBo queryAssetList(String realNmae,long currentPage, long pageSize);

    AssetListBizBo queryRealNameList();

    AssetBizBo queryCoinById(long id);

    boolean checkHasCoin(String symbol);
}
