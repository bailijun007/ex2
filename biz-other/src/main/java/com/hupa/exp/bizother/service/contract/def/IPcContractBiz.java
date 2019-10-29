package com.hupa.exp.bizother.service.contract.def;


import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.contract.PcContractBizBo;
import com.hupa.exp.bizother.entity.contract.PcContractListBizBo;

import java.util.List;

public interface IPcContractBiz {



    /**
     * 根据交易对内部服务名获取对象
     * @param symbol
     * @return
     */
    PcContractBizBo get(String asset,String symbol);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    PcContractBizBo getContractById(long id);

    long createPcContract(PcContractBizBo po) throws ValidateException;

    long editContract(PcContractBizBo po) throws ValidateException;


    PcContractListBizBo selectPosPageByParam(String asset,String symbol, long currentPage, long pageSize);
    List<String> selectAllSymbol();


    PcContractBizBo checkHasContract(String asset,String symbol,String displayName);

}
