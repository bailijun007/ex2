package com.hupa.exp.bizother.service.symbol.def;

import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.symbol.BbSymbolBizBo;
import com.hupa.exp.bizother.entity.symbol.BbSymbolListBizBo;

import java.util.List;

/**
 * Created by Administrator on 2020/2/9.
 */
public interface IBbSymbolBiz {

    /**
     * 根据交易对内部服务名获取对象
     * @param symbol
     * @return
     */
    BbSymbolBizBo get(String asset, String symbol);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    BbSymbolBizBo getBbSymbolById(long id);

    long createBbSymbol(BbSymbolBizBo po) throws ValidateException;

    long editBbSymbol(BbSymbolBizBo po) throws ValidateException;

    BbSymbolListBizBo selectPosPageByParam(String asset, String symbol, long currentPage, long pageSize);

    List<String> selectAllSymbol();

    BbSymbolBizBo checkHasBbSymbol(String asset,String symbol,String displayName);

}
