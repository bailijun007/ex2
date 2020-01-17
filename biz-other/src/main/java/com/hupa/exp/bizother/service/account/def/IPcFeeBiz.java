package com.hupa.exp.bizother.service.account.def;

import com.hupa.exp.bizother.entity.account.PcFeeBizBo;
import com.hupa.exp.bizother.entity.account.PcFeeListBizBo;
import com.hupa.exp.bizother.entity.account.UserPcFee;

import java.util.List;

public interface IPcFeeBiz {
    PcFeeBizBo getPcFeeById(long id);

    PcFeeListBizBo getPcFeePageData(long currentPage, long pageSize);

    long createPcFee(PcFeeBizBo bo);

    long editPcFee(PcFeeBizBo bo);

    List<PcFeeBizBo> getAllPcFee();

}
