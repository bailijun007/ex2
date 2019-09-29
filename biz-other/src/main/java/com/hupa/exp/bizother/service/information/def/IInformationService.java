package com.hupa.exp.bizother.service.information.def;

import com.hupa.exp.bizother.entity.ExpInformationBizBo;
import com.hupa.exp.bizother.entity.ExpInformationListBizBo;
import com.hupa.exp.bizother.entity.ExpInformationPageDataBizBo;

public interface IInformationService {
    ExpInformationListBizBo getInformationListByType(int type, int limit);
    ExpInformationPageDataBizBo getInformationPageData(Integer type,String title, int currentPage, int pageSize);
    ExpInformationBizBo getInformationById(long id);
    long createInformation(ExpInformationBizBo bizBo);
    int editInformation(ExpInformationBizBo bizBo);
    int deleteInformation(long id);
}
