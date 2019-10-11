package com.hupa.exp.bizother.service.contract.def;


import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.contract.PcContractBizBo;
import com.hupa.exp.bizother.entity.contract.PcContractListBizBo;

import java.util.List;

public interface IPcContractBiz {

 //   ConcurrentMap<String,PcContractBizBo> cache = new ConcurrentHashMap<>();

//    void saveToCache();




    /**
     * 根据pairName转换成displayName
     * @param pair
     * @return
     */
    String conventToDisplayName(String pair);


    /**
     * 根据displayName转换成pairName
     * @param displayName
     * @return
     */
    String conventToPairName(String displayName);


//    /**
//     * 此方法保证交易对存在
//     * @param pair
//     * @return
//     */
//    int getPrecisionByPair(String pair);


    /**
     * 判断交易对是否存在返回boolean
     * 不报错
     * @param pair
     * @return
     */
    boolean exist(String pair);

    /**
     * 判断交易所是否存在
     * @param displayName
     * @return
     */
    boolean existByDisplayName(String displayName);

    /**
     * 判断交易对是否存在,没有就报错
     * @param pair
     */
    void ifNoExistException(String pair) throws ValidateException;


    /**
     * 根据交易对内部服务名获取对象
     * @param pair
     * @return
     */
    PcContractBizBo get(String pair);

    /**
     * 根据展示名对象
     * @param displayName
     * @return
     */
    PcContractBizBo getByDisplayName(String displayName);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    PcContractBizBo getContractById(long id);

    long createPcContract(PcContractBizBo po) throws ValidateException;

    long editContract(PcContractBizBo po) throws ValidateException;


    PcContractListBizBo selectPosPageByParam(String pair, long currentPage, long pageSize);

    boolean checkHasContract(String pair);

    /**
     * 获取状态为有效的PcContractBizBo对象
     * @return
     */
    List<PcContractBizBo> queryActiveBo();
}
