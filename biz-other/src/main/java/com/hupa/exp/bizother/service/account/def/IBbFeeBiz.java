package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.BbFeeBizBo;
import com.hupa.exp.bizother.entity.account.BbFeeListBizBo;

import java.util.List;

/**
 * Created by Administrator on 2020/2/6.
 */
public interface IBbFeeBiz {

    BbFeeBizBo getBbFeeById(long id);

    BbFeeListBizBo getBbFeePageData(long currentPage, long pageSize);

    long createBbFee(BbFeeBizBo bo);

    long editBbFee(BbFeeBizBo bo);

    List<BbFeeBizBo> getAllBbFee();


}
